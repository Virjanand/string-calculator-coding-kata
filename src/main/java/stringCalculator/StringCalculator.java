package stringCalculator;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static java.util.Arrays.asList;

public class StringCalculator {

    public static final List<String> SEPARATORS = asList(";", ",", "\n");

    public String add(String numbers) {
        if (numbers.isEmpty()) {
            return "0";
        }

        if (numbers.startsWith("//")) {
            numbers = numbers.substring(4);
        }

        String[] addends = numbers.split(buildSeparatorRegex());

        String validationMessage = validateNumbers(numbers);
        if (validationMessage != null) {
            return validationMessage;
        }


        double sum = Arrays.stream(addends)
                .mapToDouble(Double::parseDouble)
                .sum();
        return String.format(Locale.ENGLISH, "%.1f", sum);
    }

    private String validateNumbers(String numbers) {
        if (numbersEndWithSeparator(numbers)) {
            return "Number expected but EOF found";
        }
        return validateNumbersDoNotContain2ConsecutiveSeparators(numbers);
    }

    private String validateNumbersDoNotContain2ConsecutiveSeparators(String numbers) {
        for (String separator : SEPARATORS) {
            int indexAfterSeparator = 0;
            int indexOfSeparator = numbers.indexOf(separator, indexAfterSeparator);
            while (indexOfSeparator != -1) {
                indexAfterSeparator = indexOfSeparator + 1;
                String characterAfterSeparator = String.valueOf(numbers.charAt(indexAfterSeparator));
                if (SEPARATORS.contains(characterAfterSeparator)) {
                    return "Number expected but '"
                            + characterAfterSeparator.replace("\n", "\\n")
                            + "' found at position "
                            + (indexAfterSeparator)
                            + ".";
                }
                indexOfSeparator = numbers.indexOf(separator, indexAfterSeparator);
            }
        }
        return null;
    }

    private boolean numbersEndWithSeparator(String numbers) {
        return SEPARATORS.stream().anyMatch(numbers::endsWith);
    }

    private String buildSeparatorRegex() {
        return String.join("|", StringCalculator.SEPARATORS);
    }
}
