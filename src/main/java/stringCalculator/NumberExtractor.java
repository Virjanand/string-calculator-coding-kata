package stringCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NumberExtractor {

    private final List<String> SEPARATORS;

    public NumberExtractor() {
        SEPARATORS = initialSeparators();
    }

    private List<String> initialSeparators() {

        List<String> separators = new ArrayList<>();
        separators.add("\n");
        separators.add(",");

        return separators;
    }

    String parseCustomSeparator(String numbers) {
        if (numbers.startsWith("//")) {
            SEPARATORS.remove(",");
            int i = numbers.indexOf("\n");
            SEPARATORS.add(numbers.substring(2, i));
            numbers = numbers.substring(i + 1);
        }
        return numbers;
    }

    String validateNumbers(String numbers) {

        if (numbers.isEmpty()) {
            return "0";
        }
        if (numbersEndWithSeparator(numbers)) {
            return "Number expected but EOF found";
        }
        return validateNumbersDoNotContain2ConsecutiveSeparators(numbers);
    }

    String validateNumbersDoNotContain2ConsecutiveSeparators(String numbers) {
        numbers = parseCustomSeparator(numbers);

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
                            + indexAfterSeparator
                            + ".";
                }
                indexOfSeparator = numbers.indexOf(separator, indexAfterSeparator);
            }
        }
        return null;
    }

    boolean numbersEndWithSeparator(String numbers) {
        return SEPARATORS.stream().anyMatch(numbers::endsWith);
    }

    String buildSeparatorRegex() {
        return SEPARATORS.stream()
                .map(s -> s.replaceFirst("\\|", "\\\\|"))
                .collect(Collectors.joining("|"));
    }

    public String[] extract(String numbers) {
        numbers = parseCustomSeparator(numbers);

        return numbers.split(buildSeparatorRegex());
    }

}
