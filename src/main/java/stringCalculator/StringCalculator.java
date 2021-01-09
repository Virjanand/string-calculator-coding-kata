package stringCalculator;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Arrays.asList;

public class StringCalculator {
    public String add(String numbers) {
        if (numbers.isEmpty()) {
            return "0";
        }
        String[] addends = numbers.split(buildSeparatorRegex(asList(",", "\n")));

        if (isNumberMissingInInput(addends)) {
            return createInputValidationMessage(numbers);
        }

        double sum = Arrays.stream(addends)
                .mapToDouble(Double::parseDouble)
                .sum();
        return String.format(Locale.ENGLISH,"%.1f", sum);
    }

    private String createInputValidationMessage(String numbers) {
        Pattern p = Pattern.compile(",(\n)");
        Matcher m = p.matcher(numbers);
        if (m.find()) {
            return "Number expected but '\\n' found at position " + (m.start() + 1) +".";
        }
        return "";
    }

    private boolean isNumberMissingInInput(String[] addends) {
        return Arrays.stream(addends).anyMatch(String::isEmpty);
    }

    private String buildSeparatorRegex(final List<String> separators) {
        return String.join("|", separators);
    }
}
