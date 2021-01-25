package stringCalculator;

import java.util.Arrays;
import java.util.Locale;

public class StringCalculator {


    private final NumberExtractor numberExtractor;

    public StringCalculator(NumberExtractor numberExtractor) {
        this.numberExtractor = numberExtractor;
    }

    public String add(String numbers) {

        String validationMessage = numberExtractor.validateNumbers(numbers);
        if (validationMessage != null) {
            return validationMessage;
        }

        String[] addends = numberExtractor.extract(numbers);

        double sum = Arrays.stream(addends)
                .mapToDouble(Double::parseDouble)
                .sum();
        return String.format(Locale.ENGLISH, "%.1f", sum);
    }

}
