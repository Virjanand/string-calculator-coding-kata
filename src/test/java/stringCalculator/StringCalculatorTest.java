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
            "'1.1,2.2', 3.3, 2 doubles -> sum"
    })
    void canAdd(String addends, String sum, String description) {
        assertThat(stringCalculator.add(addends)).isEqualTo(sum);
    }
}
