package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

	private static Scanner scanner = new Scanner(System.in);

	public static int inputPurchaseAmount() {
		System.out.println("구입금액을 입력해 주세요.");
		return scanner.nextInt();
	}

	public static List<Integer> inputWinningNumber() {
		scanner.nextLine();
		System.out.println("지난 주 당첨 번호를 입력해 주세요.");
		return Arrays.stream(scanner.nextLine().split(", "))
			.filter(item -> !item.isBlank())
			.map((number) -> Integer.parseInt(number))
			.collect(Collectors.toList());
	}
}
