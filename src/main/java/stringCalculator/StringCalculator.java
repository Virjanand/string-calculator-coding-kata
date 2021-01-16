package stringCalculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class StringCalculator {

    public final List<String> SEPARATORS;

    public StringCalculator() {
        SEPARATORS = populateSeparators();
    }

    private List<String> populateSeparators() {

        List<String> separators = new ArrayList<>();
        separators.add("\n");
        separators.add(",");

        return separators;
    }

    public String add(String numbers) {
        numbers = parseCustomSeparator(numbers);

        String validationMessage = validateNumbers(numbers);
        if (validationMessage != null) {
            return validationMessage;
        }

        String[] addends = numbers.split(buildSeparatorRegex());

        double sum = Arrays.stream(addends)
                .mapToDouble(Double::parseDouble)
                .sum();
        return String.format(Locale.ENGLISH, "%.1f", sum);
    }

    private String parseCustomSeparator(String numbers) {
        if (numbers.startsWith("//")) {
            SEPARATORS.remove(",");
            SEPARATORS.add(String.valueOf(numbers.charAt(2)));
            numbers = numbers.substring(4);
        }
        return numbers;
    }

    private String validateNumbers(String numbers) {
        if (numbers.isEmpty()) {
            return "0";
        }
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
        return String.join("|", SEPARATORS);
    }
}
