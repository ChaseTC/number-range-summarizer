package numberrangesummarizer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class NumberRangeSummarizerTest {
    private final NumberRangeSummarizer summarizer = new NumberRangeSummarizer();

    @Test
    void collect() {
        ArrayList<Integer> actual = summarizer.collect("1,2,8,3,6,9,4,2,6");
        List<Integer> expected = Arrays.asList(1, 2, 8, 3, 6, 9, 4, 2, 6);

        assertEquals(actual, expected);
    }

    @ParameterizedTest
    @MethodSource
    void summarizeCollection(Collection<Integer> input, String expected) {
        String actual = summarizer.summarizeCollection(input);

        assertEquals(actual, expected);
    }

    static Stream<Arguments> summarizeCollection() {
        return Stream.of(
                Arguments.of(Arrays.asList(1, 3, 6, 7, 8, 12, 13, 14, 15, 21, 22, 23, 24, 31), "1, 3, 6-8, 12-15, 21-24, 31"),
                Arguments.of(List.of(1), "1"),
                Arguments.of(Arrays.asList(1, 4, 3), "1, 4, 3"),
                Arguments.of(Arrays.asList(1, 2, 3), "1-3"),
                Arguments.of(Arrays.asList(1, 2, 2, 3), "1-2, 2-3"),
                Arguments.of(Arrays.asList(4, -1, 0, 3), "4, -1-0, 3")
        );
    }
}