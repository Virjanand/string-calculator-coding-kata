package stringCalculator;

import java.util.Arrays;

public class StringCalculator {
    public String add(String numbers) {
        if (numbers.isEmpty()) {
            return "0";
        }
        String[] addends = numbers.split(",");
        int sum = Arrays.stream(addends)
                .mapToInt(Integer::parseInt)
                .sum();
        return Integer.toString(sum);
    }
}
