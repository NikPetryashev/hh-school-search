package homework.search.service;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class StopWordsAnalysisTest {
    StopWordsAnalysis stopWords = new StopWordsAnalysis();

    @Test
    public void testExecute() {
        List<String> test = Arrays.asList("в", "списке", "удалить", "предлоги", "и", "союзы", "между", "словами", "на");
        List<String> tokens = stopWords.execute(test);
        assertEquals(tokens, Arrays.asList("списке", "удалить", "предлоги", "союзы", "между", "словами"));
    }
}