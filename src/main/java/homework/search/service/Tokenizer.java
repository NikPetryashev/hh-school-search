package homework.search.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Разбиваем строку на отдельные слова
public class Tokenizer {

    public List<String> getTerms(final String text) {
        if (text == null) {
            return new ArrayList<>();
        }
        return Arrays.asList(text.toLowerCase().split("[\\p{Punct}\\s]+"));
    }
}
