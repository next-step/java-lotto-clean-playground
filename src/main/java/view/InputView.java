package view;

import domain.Lotto;
import domain.LottoNumber;
import domain.LottoNumbers;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public int getLottoPurchasePrice() {
        int purchasePrice = scanner.nextInt();
        scanner.nextLine();
        return purchasePrice;
    }

    public Lotto getWinningLottoNumbers() {
        String input = scanner.nextLine();
        return new Lotto(new LottoNumbers(Arrays.stream(input.split(","))
                .map(s -> new LottoNumber(Integer.parseInt(s.trim())))
                .collect(Collectors.toList())));
    }
}
