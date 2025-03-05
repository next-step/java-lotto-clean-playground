package view;

import domain.Lotto;
import domain.LottoNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public int getPurchaseAmount() {
        while (true) {
            System.out.println("구입금액을 입력해 주세요.");
            String input = scanner.nextLine();
            try {
                int amount = Integer.parseInt(input);
                if (amount > 0 && amount % Lotto.LOTTO_PRICE == 0) {
                    return amount;
                } else {
                    throw new IllegalArgumentException("로또는 " + Lotto.LOTTO_PRICE+"원 단위로 구매해야합니다.");
                }
            } catch (NumberFormatException e) {
                System.out.println("1000원 단위 유효한 숫자를 입력해주세요. 다시 시도해주세요.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + "다시 시도해주세요.");
            }
        }
    }

    public int getNumberOfManualTickets(int totalTickets) {
        while (true) {
            System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
            String input = scanner.nextLine();
            try {
                int manualTickets = Integer.parseInt(input);
                if (manualTickets >= 0 && manualTickets <= totalTickets) {
                    return manualTickets;
                }
            } catch (NumberFormatException e) {
                System.out.println("유효한 로또 수를 입력해주세요.");
            }
        }
    }

    public List<List<LottoNumber>> getManualNumbers(int manualTicketsCount) {
        List<List<LottoNumber>> manualTickets = new ArrayList<>();
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");

        for (int i = 0; i < manualTicketsCount; i++) {
            while (true) {
                String input = scanner.nextLine();
                try {
                    String[] tokens = input.split(",");
                    if (tokens.length != Lotto.LOTTO_NUMBER_COUNT) {
                        throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
                    }

                    List<LottoNumber> manualNumbers = new ArrayList<>();
                    for (String token : tokens) {
                        int num = Integer.parseInt(token.trim());
                        manualNumbers.add(new LottoNumber(num));
                    }


                    long distinctCount = manualNumbers.stream().distinct().count();
                    if (distinctCount != Lotto.LOTTO_NUMBER_COUNT) {
                        throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
                    }

                    manualTickets.add(manualNumbers);
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("유효한 번호를 입력해주세요. 예: 8,21,23,41,42,43");
                }
            }
        }

        return manualTickets;
    }

    public List<LottoNumber> getWinningNumbers() {
        while (true) {
            System.out.println("지난 주 당첨 번호를 입력해 주세요.");
            String input = scanner.nextLine();
            try {
                String[] tokens = input.split(",");
                if (tokens.length != Lotto.LOTTO_NUMBER_COUNT) {
                    throw new IllegalArgumentException("당첨 번호는 6개여야 합니다.");
                }

                List<LottoNumber> winningNumbers = new ArrayList<>();
                for (String token : tokens) {
                    int num = Integer.parseInt(token.trim());
                    winningNumbers.add(new LottoNumber(num));
                }

                long distinctCount = winningNumbers.stream().distinct().count();
                if (distinctCount != Lotto.LOTTO_NUMBER_COUNT) {
                    throw new IllegalArgumentException("당첨 번호는 중복될 수 없습니다.");
                }

                return winningNumbers;
            } catch (IllegalArgumentException e) {
                System.out.println("유효한 당첨 번호를 입력해주세요. 예: 1,2,3,4,5,6");
            }
        }
    }

    public LottoNumber getBonusNumber(List<LottoNumber> winningNumbers) {
        while (true) {
            System.out.println("보너스 볼을 입력해 주세요.");
            String input = scanner.nextLine();

            try {
                int bonus = Integer.parseInt(input.trim());
                LottoNumber bonusNumber = new LottoNumber(bonus);
                if (winningNumbers.contains(bonusNumber)) {
                    throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
                }

                return bonusNumber;
            } catch (IllegalArgumentException e) {
                System.out.print(e.getMessage());
                System.out.println("유효한 숫자를 입력해주세요.");
            }
        }
    }
}
