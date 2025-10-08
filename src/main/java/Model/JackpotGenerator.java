package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class JackpotGenerator {
    private final List<Integer> number;

    private JackpotGenerator(List<Integer> numbers) {
        this.number = numbers;
    }

    public static JackpotGenerator from(String[] splits) {
        Set<Integer> numbers = parseAndValidateNumbers(splits);
        return new JackpotGenerator(Collections.unmodifiableList(new ArrayList<>(numbers)));
    }

    private static Set<Integer> parseAndValidateNumbers(String[] splits) {
        if (splits.length != 6) {
            throw new IllegalArgumentException("당첨 번호는 6개를 입력해야 합니다.");
        }
        Set<Integer> numbers = new TreeSet<>();
        for (String s : splits) {
            int number;
            try {
                number = Integer.parseInt(s.trim());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("당첨 번호는 숫자여야 합니다.");
            }
            validateNumberRange(number);
            if (!numbers.add(number)) {
                throw new IllegalArgumentException("당첨 번호는 중복될 수 없습니다.");
            }
        }
        return numbers;
    }

    private static void validateNumberRange(int number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException("당첨 번호는 1과 45 사이의 숫자여야 합니다.");
        }
    }

    public List<Integer> getNumber() {
        return number;
    }
}
