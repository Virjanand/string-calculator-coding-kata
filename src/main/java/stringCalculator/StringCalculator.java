package stringCalculator;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static java.util.Arrays.asList;

public class StringCalculator {
    public String add(String numbers) {
        if (numbers.isEmpty()) {
            return "0";
        }
        String[] addends = numbers.split(buildSeparatorRegex(asList(",", "\n")));
        double sum = Arrays.stream(addends)
                .mapToDouble(Double::parseDouble)
                .sum();
        return String.format(Locale.ENGLISH,"%.1f", sum);
    }

    private String buildSeparatorRegex(final List<String> separators) {
        return String.join("|", separators);
    }
}
