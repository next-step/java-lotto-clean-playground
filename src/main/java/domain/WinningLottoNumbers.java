package domain;

import java.util.*;
import java.util.stream.Collectors;

public class WinningLottoNumbers {

    private static final int REQUIRED_NUMBERS = 6;

    private List<LottoNumber> numbers;

    public WinningLottoNumbers(String input) {
        this.numbers = parseAndValidate(input);
    }

    private List<LottoNumber> parseAndValidate(String input) {
        String[] numbersArray = parseInput(input);
        validateNumberCount(numbersArray);
        return convertToLottoNumber(numbersArray);
    }

    // 입력 값 가공: 공백 제거, 쉼표로 분리
    private String[] parseInput(String input) {
        return input.trim().split("\\s*,\\s*");
    }

    // 번호 개수(6개) 검증
    private void validateNumberCount(String[] numbersArray) {
        if (numbersArray.length != REQUIRED_NUMBERS) {
            throw new IllegalArgumentException("당첨 번호는 6개여야 합니다.");
        }
    }

    // 숫자 변환
    private List<LottoNumber> convertToLottoNumber(String[] numbersArray) {
        Set<Integer> uniqueNumbers = new HashSet<>();
        return Arrays.stream(numbersArray)
                .map(String::trim)
                .map(Integer::parseInt)  // 숫자로 변환
                .map(LottoNumber::new)   // LottoNumber 객체 생성 (여기서 1~45 검증됨)
                .peek(num -> validateNoDuplicates(num, uniqueNumbers))
                .collect(Collectors.toList());
    }

    // 중복 검사
    private void validateNoDuplicates(LottoNumber num, Set<Integer> uniqueNumbers) {
        if (!uniqueNumbers.add(num.number())) {
            throw new IllegalArgumentException("중복된 번호가 있습니다.");
        }
    }

    public List<LottoNumber> getNumbers() {
        return numbers;
    }
}
