package stringCalculator;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Arrays.asList;

public class StringCalculator {

    public static final List<String> SEPARATORS = asList(",", "\n");

    public String add(String numbers) {
        if (numbers.isEmpty()) {
            return "0";
        }

        String[] addends = numbers.split(buildSeparatorRegex(SEPARATORS));

        String x = validateNumbers(numbers);
        if (x != null) return x;

        double sum = Arrays.stream(addends)
                .mapToDouble(Double::parseDouble)
                .sum();
        return String.format(Locale.ENGLISH, "%.1f", sum);
    }

    private String validateNumbers(String numbers) {
        if (nubmersEndsWithSeparator(numbers)) {
            return "Number expected but EOF found";
        }
        Pattern p = Pattern.compile(",\n");
        Matcher m = p.matcher(numbers);
        if (m.find()) {
            return "Number expected but '\\n' found at position " + (m.start() + 1) + ".";
        }
        return null;
    }

    private boolean nubmersEndsWithSeparator(String numbers) {
        return SEPARATORS.stream().anyMatch(numbers::endsWith);
    }

    private String buildSeparatorRegex(final List<String> separators) {
        return String.join("|", separators);
    }
}
