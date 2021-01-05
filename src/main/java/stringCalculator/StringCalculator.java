package stringCalculator;

import java.util.Arrays;
import java.util.Locale;

public class StringCalculator {
    public String add(String numbers) {
        if (numbers.isEmpty()) {
            return "0";
        }
        String[] addends = numbers.split(",");
        double sum = Arrays.stream(addends)
                .mapToDouble(Double::parseDouble)
                .sum();
        return String.format(Locale.ENGLISH,"%.1f", sum);
    }
}
