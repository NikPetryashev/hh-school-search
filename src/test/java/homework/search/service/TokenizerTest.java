package homework.search.service;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TokenizerTest {
    private Tokenizer tokenizer = new Tokenizer();

    @Test
    public void testGetTerms() {
        List<String> tokens = tokenizer.getTerms("Строка разбита на слова");
        assertEquals(tokens, Arrays.asList("строка", "разбита", "на", "слова"));
    }

    @Test
    public void testGetTermsEmpty() {
        List<String> tokens = tokenizer.getTerms("");
        assertTrue(tokens.isEmpty());
    }

    @Test
    public void testGetTermsNull() {
        List<String> tokens = tokenizer.getTerms(null);
        assertTrue(tokens.isEmpty());
    }
}