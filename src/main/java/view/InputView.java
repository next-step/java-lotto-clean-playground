package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

	private static Scanner scanner = new Scanner(System.in);

	public static int inputPurchaseAmount() {
		System.out.println("구입금액을 입력해 주세요.");
		return Integer.parseInt(scanner.nextLine());
	}

	public static List<Integer> inputWinningNumber() {
		System.out.println("지난 주 당첨 번호를 입력해 주세요.");
		return Arrays
			.stream(scanner.nextLine().replace(" ", "").split(","))
			.map((number) -> Integer.parseInt(number))
			.collect(Collectors.toList());
	}

	public static int inputBonusNumber() {
		System.out.println("보너스 볼을 입력해 주세요.");
		return Integer.parseInt(scanner.nextLine());
	}

	public static int inputManualLottoCount() {
		System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
		return Integer.parseInt(scanner.nextLine());
	}

	public static List<List<Integer>> inputManualLottoNumbers(int count) {
		List<List<Integer>> numbers = new ArrayList<>();
		if (count > 0) {
			System.out.println("수동으로 구매할 번호를 입력해 주세요.");
			for (int i = 0; i < count; i++) {
				numbers.add(Arrays.stream(scanner.nextLine().replace(" ", "").split(","))
					.map(Integer::parseInt)
					.collect(Collectors.toList()));
			}
		}
		return numbers;
	}
}
