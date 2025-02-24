package view;

import domain.LottoConstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InputView
{
    private static final Scanner sc = new Scanner(System.in);

    // 총 구입 금액 입력처리
    public static int getTotalCost()
    {
        System.out.println("구입금액을 입력해 주세요.");
        int buyCost = Integer.parseInt(sc.nextLine());

        if (buyCost < 0)
            throw new IllegalArgumentException("구입금액은 양수여야 합니다.");

        return buyCost;
    }

    // 수동 구입 로또 수 입력처리
    public static int getManualBuyCount()
    {
        System.out.println("\n수동으로 구매할 로또 수를 입력해 주세요.");
        int manualBuyCount = Integer.parseInt(sc.nextLine());

        if (manualBuyCount < 0)
            throw new IllegalArgumentException("구입금액은 양수여야 합니다.");

        return manualBuyCount;
    }

    // 수동 구입 로또 번호 입력처리
    public static List<List<Integer>> getManualLottoNumber(int manualBuyCount)
    {
        System.out.println("\n수동으로 구매할 번호를 입력해 주세요.");
        List<List<Integer>> manualLottos = new ArrayList<>();

        for (int i=0; i<manualBuyCount; i++)
        {
            String lastString = sc.nextLine();
            manualLottos.add(
                    Pattern.compile(",\\s*")
                    .splitAsStream(lastString)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList()));
        }
        return manualLottos;
    }

    // 지난주 당첨 번호를 입력처리 및 List<Integer>로 반환
    public static List<Integer> getLastweekGoalNumber()
    {
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
        String lastString = sc.nextLine();

         List<Integer> goalNumber = Pattern.compile(",\\s*")
                 .splitAsStream(lastString)
                 .map(Integer::parseInt)
                 .collect(Collectors.toList());

         if (goalNumber.size() != 6)
             throw new IllegalArgumentException("입력된 지난주의 당첨 번호가 6개가 아닙니다.");

         return goalNumber;
    }

    public static int getLastweekBonusGoalNumber()
    {
        System.out.println("\n보너스 볼을 입력해 주세요.");
        int bonusNumber = Integer.parseInt(sc.nextLine());

        if (bonusNumber < 1 || bonusNumber > 45)
            throw new IllegalArgumentException("보너스 볼은 1~45 사이의 값이어야 합니다.");

        return bonusNumber;
    }
}
