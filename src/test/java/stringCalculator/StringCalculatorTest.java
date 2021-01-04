package stringCalculator;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class StringCalculatorTest {

    @Test
    void canAddEmptyString() {
        StringCalculator stringCalculator = new StringCalculator();

        assertThat(stringCalculator.add("")).isEqualTo("0");
    }
}
