package view;

import domain.WinningNumbers;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static Scanner scanner = new Scanner(System.in);

    public static int readLottoPrice() {
        int lottoPrice = scanner.nextInt();
        scanner.nextLine();
        if (lottoPrice < 1000) {
            throw new IllegalArgumentException("로또 구입금액은 1000원이상만 가능합니다.");
        }
        return lottoPrice;
    }

    public static int inputBonusBall() {
        System.out.println("보너스 볼을 입력해 주세요.");
        int bonusBall = scanner.nextInt();
        scanner.nextLine(); // 개행문자 처리
        return bonusBall;
    }
    public static List<Integer> inputWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String input = scanner.nextLine().trim(); // 입력값 양 끝 공백 제거
        if (input.isEmpty()) { // 입력이 없으면 빈 리스트 반환
            return List.of();
        }
        return parseNumbers(input);
    }

    private static List<Integer> parseNumbers(String input) {
        return Arrays.stream(input.split(","))
                .map(String::trim) // 각 문자열 양 끝 공백 제거
                .map(Integer::parseInt) // 문자열을 정수로 변환
                .collect(Collectors.toList());
    }

}
