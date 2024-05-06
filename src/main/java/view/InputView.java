package view;

import Constant;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {

    public static int getPrice() {
        System.out.println(Constant.PRICE_QUERY);
        return readInt();
    }

    public static int getManualCount() {
        System.out.println(Constant.MANUAL_COUNT_QUERY);
        return readInt();
    }

    public static List<String> getManualLottos(int manualCount) {
        System.out.println(Constant.MANUAL_LOTTO_QUERY);
        List<String> manualLottos = new ArrayList<String>();
        for (int i = 0; i < manualCount; i++) {
            manualLottos.add(readString());
        }
        return manualLottos;
    }

    public static String getWinLotto() {
        System.out.println(Constant.WIN_LOTTO_QUERY);
        return readString();
    }

    public static String getBonusBall() {
        System.out.println(Constant.BONUS_QUERY);
        return readString();
    }

    private static int readInt() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private static String readString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }


}
