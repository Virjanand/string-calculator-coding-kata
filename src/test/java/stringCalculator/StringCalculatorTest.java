package stringCalculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;


public class StringCalculatorTest {

    StringCalculator stringCalculator;

    @BeforeEach
    void createStringCalculator() {
        stringCalculator = new StringCalculator();
    }

    @ParameterizedTest(name = "{2}")
    @CsvSource({
            "'',0, empty string -> 0",
            "1, 1.0, integer -> integer",
            "'1,2', 3.0, 2 integers -> sum",
            "'1.1,2.2', 3.3, 2 doubles -> sum",
            "'1,2,3', 6.0, unknown number of arguments",
            "'1\n2,3', 6.0, newline as separator",
    })
    void canAdd(String addends, String sum, String description) {
        assertThat(stringCalculator.add(addends)).isEqualTo(sum);
    }

    @ParameterizedTest(name = "{1} after another separator.")
    @CsvSource({
            "'175.2,\n35', '\\n'",
            "'175.2\n,35', ','",
    })
    void returnErrorMessageForTwoSequentialSeparatorsWhenAdding(String addends, final String separator) {
        assertThat(stringCalculator.add(addends)).isEqualTo("Number expected but '" + separator + "' found at position 6.");
    }

    @ParameterizedTest(name = "{1}")
    @CsvSource({
            "'1,3,', 'End in ,'",
            "'1\n3\n', 'End in \\n'",
    })
    void returnErrorMessageForTrailingSeparatorsWhenAdding(String numbers, String description) {
        assertThat(stringCalculator.add(numbers)).isEqualTo("Number expected but EOF found");
    }

    @ParameterizedTest(name = "{2}")
    @CsvSource({
            "'//;\n1;2',3.0, semicolon as separator",
            "'//|\n1|2|3',6.0, pipe as separator",
    })
    void replaceCommaWithCustomSeparator(String addends, String sum, String description) {
        assertThat(stringCalculator.add(addends)).isEqualTo(sum);
    }
}
