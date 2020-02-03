package homework.search.service;

import homework.search.object.Document;
import homework.search.object.TermPosition;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IndexBuilder {

    private static File fileIndex;

    public IndexBuilder(final String filePath) {
        fileIndex = new File(filePath);
    }

    public static void writeIndex(final List<Document> documents) {
        createDir();
        Map<String, List<TermPosition>> index = getInvertIndexFromDocs(documents);
        try (FileWriter fWr = new FileWriter(fileIndex, StandardCharsets.UTF_8, false)) { //false перезапись файла
            for (Map.Entry<String, List<TermPosition>> entry : index.entrySet()) {
                StringJoiner strJoin = new StringJoiner(";");
                entry.getValue().forEach(numDoc -> strJoin.add(String.valueOf(numDoc)));
                fWr.write(entry.getKey() + ":" + strJoin + "\n");
            }
            fWr.flush();
            System.out.println("Файл индекса создан!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static Map<String, List<TermPosition>> getInvertIndexFromDocs(final List<Document> documents) {
        Map<String, List<TermPosition>> invertIndex = new HashMap<>();
        for (Document doc:documents) {
            buildInvertIndex(invertIndex, doc);
        }
        return invertIndex;
    }

    private static void buildInvertIndex(final Map<String, List<TermPosition>> invertIndex, final Document doc) {
        for (Map.Entry<String, List<Integer>> entry : getTermsFromDoc(doc).entrySet()) {
            if (!invertIndex.containsKey(entry.getKey())) {
                invertIndex.put(entry.getKey(), new ArrayList<>());
            }
            invertIndex.get(entry.getKey()).add(new TermPosition(doc.getDocId(), entry.getValue()));
        }
    }


    public static Map<String, List<TermPosition>> readIndex() {
        try (Stream<String> lineStream = Files.newBufferedReader(Paths.get(fileIndex.getAbsolutePath()), StandardCharsets.UTF_8).lines()) {
            return lineStream
                    .map(s -> s.split("[:;]"))
                    .collect(Collectors.toMap(line -> line[0], line -> new ArrayList<>(getListDocIdsWithTermPosition(line))));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return Collections.EMPTY_MAP;
        }
    }

    private static List<TermPosition> getListDocIdsWithTermPosition(final String[] wordsLine) {
        return Arrays.stream(wordsLine)
                .skip(1)
                .map(line -> line.split("[,\\[\\]\\s]+"))
                .map(docIdTerms -> new TermPosition(Integer.valueOf(docIdTerms[0]), new ArrayList<>(getTermPosition(docIdTerms))))
                .collect(Collectors.toList());
    }

    private static List<Integer> getTermPosition(final String[] docIdTerms) {
        return Arrays.stream(docIdTerms)
                .skip(1)
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }

    private static Map<String, List<Integer>> getTermsFromDoc(final Document doc) {
        Map<String, List<Integer>> termPositionInDoc = new HashMap<>();
        for (int idx = 0; idx < doc.getTerms().size(); idx++) {
            if (!termPositionInDoc.containsKey(doc.getTerms().get(idx))) {
                termPositionInDoc.put(doc.getTerms().get(idx), new ArrayList<>());
            }
            termPositionInDoc.get(doc.getTerms().get(idx)).add(idx);
        }
        return termPositionInDoc;
    }
    //создаем директорию для индекса, если ее не существует
    private static void createDir() {
        if (fileIndex.exists())
            return;
        if (fileIndex.getParentFile() != null) {
            if (!fileIndex.getParentFile().mkdir()) {
                System.out.println("Файл индекса не создан!");
            }
        }
    }
}

