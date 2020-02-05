package homework.search.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//Разбиваем строку на отдельные слова
public class Tokenizer {

    public List<String> getTerms(final String text) {
        if ("".equals(text) || text == null) {
            return Collections.EMPTY_LIST;
        }
        return Arrays.asList(text.toLowerCase().split("[\\p{Punct}\\s]+"));
    }
}
