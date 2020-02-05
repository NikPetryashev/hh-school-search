package homework.search.service;

import homework.search.object.Document;
import homework.search.object.TermPosition;
import org.junit.*;

import java.io.File;
import java.util.*;

import static org.junit.Assert.*;

public class IndexBuilderTest {
    private static String filePath = "src/test/java/homework/search/indexTest.txt";
//    private String filePath = "indexTest.txt";
    IndexBuilder indexBuilder = new IndexBuilder(filePath);
    private static List<Document> test;
    private static Map<String, List<TermPosition>> testMap;

    @BeforeClass
    public static void setUp() throws Exception {
        test = new ArrayList<>();
        test.add(new Document(1, Arrays.asList("java", "python")));
        test.add(new Document(2, Arrays.asList("kotlin", "sql")));
        testMap = new HashMap<>();
        testMap.put("java", Arrays.asList(new TermPosition(1, List.of(0))));
        testMap.put("python", Arrays.asList(new TermPosition(1, List.of(1))));
        testMap.put("kotlin", Arrays.asList(new TermPosition(2, List.of(0))));
        testMap.put("sql", Arrays.asList(new TermPosition(2, List.of(1))));
    }

    @Test
    public void testWriteIndex() {
        indexBuilder.writeIndex(test);
        File fileTest = new File(filePath);
        assertTrue(fileTest.exists());
    }

    @Test
    public void testReadIndex() {
        Map<String, List<TermPosition>> readMapIndex = IndexBuilder.readIndex();
        assertEquals(readMapIndex, testMap);
    }

    @AfterClass
    public static void delete(){
        File fileTest = new File(filePath);
        fileTest.delete();
    }
}