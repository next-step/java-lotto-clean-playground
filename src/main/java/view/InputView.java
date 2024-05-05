package view;

import java.util.Scanner;

public class InputView {

	private static Scanner scanner = new Scanner(System.in);

	public static int inputPurchaseAmount() {
		System.out.println("구입금액을 입력해 주세요.");
		int purchaseAmount = scanner.nextInt();
		if (purchaseAmount < 1000) {
			throw new IllegalArgumentException("로또 1장의 가격은 1000원 입니다.");
		}
		return purchaseAmount;
	}
}
