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
            "1, 1, number -> number",
            "'1,2', 3, 2 numbers -> sum"
    })
    void canAdd(String addends, String sum, String description) {
        assertThat(stringCalculator.add(addends)).isEqualTo(sum);
    }
}
