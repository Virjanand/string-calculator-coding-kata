package stringCalculator;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static java.util.Arrays.asList;

public class StringCalculator {

    public static final List<String> SEPARATORS = asList(",", "\n");

    public String add(String numbers) {
        if (numbers.isEmpty()) {
            return "0";
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
        if (numbersEndsWithSeparator(numbers)) {
            return "Number expected but EOF found";
        }
        for (String separator : SEPARATORS) {
            int indexAfterSeparator = 0;
            int separatorIndex = numbers.indexOf(separator, indexAfterSeparator);
            while (separatorIndex != -1) {
                indexAfterSeparator = separatorIndex + 1;
                String characterAfterSeparator = String.valueOf(numbers.charAt(indexAfterSeparator));
                if (SEPARATORS.contains(characterAfterSeparator)) {
                    return "Number expected but '"
                            + characterAfterSeparator.replace("\n", "\\n")
                            + "' found at position "
                            + (indexAfterSeparator)
                            + ".";
                }
                separatorIndex = numbers.indexOf(separator, indexAfterSeparator);
            }
        }
        return null;
    }

    private boolean numbersEndsWithSeparator(String numbers) {
        return SEPARATORS.stream().anyMatch(numbers::endsWith);
    }

    private String buildSeparatorRegex() {
        return String.join("|", StringCalculator.SEPARATORS);
    }
}
