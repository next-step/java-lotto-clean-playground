package util;

import java.util.ArrayList;
import java.util.List;

public class ConvertToIntegerList {
    public static List<Integer> removeCommasAndSpaces(String inputStringLotto) {
        // 쉼표와 공백을 기준으로 문자열을 분할하여 문자열 배열을 생성
        String[] tokens = inputStringLotto.split("[,\\s]+");

        List<Integer> numbers = new ArrayList<>();

        // 분할된 각 문자열을 정수로 변환하여 리스트에 추가
        for (String token : tokens) {
            try {
                int number = Integer.parseInt(token);
                numbers.add(number);
            } catch (NumberFormatException e) {
                // 정수로 변환할 수 없는 문자열은 무시
            }
        }

        return numbers;
    }
}