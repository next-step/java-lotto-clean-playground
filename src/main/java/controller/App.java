package controller;

import java.util.*;
import view.Input;
import view.Output;

public class App {
    public static void main(String[] args) {

        // 모든 로또들을 넣을 리스트
        List<List<String>> LottoList = new ArrayList<>();

        // 구입 금액 입력한다
        int total = Input.moneyInput();

        // 수동으로 구매할 로또 수를 입력한다
        int manual = Input.manualNumInput();

        // 자동으로 구매할 로또 수를 계산한다.
        int auto = total - manual;

        // 수동 로또 번호를 입력한다
        for(int i = 0; i < manual; i++) {
            List<String> manualLotto = Input.manualLottoInput(i);
            LottoList.add(manualLotto);
        }

        // 수동과 자동 구매 개수를 출력한다
        Output.ManualAndAutoNumOutput(manual, auto);

        // 자동 로또 번호를 생성 및 출력한다
        for (int i = 0; i < auto; i++) {
            List<String> autoLotto = Input.AutoLottoInput();
            Collections.sort(autoLotto);
            LottoList.add(autoLotto);
            Output.LottoNumOutput(autoLotto);
        }

        // 지난 주 당첨 번호를 입력한다
        List<String> LastWeekLotto = Input.LastWeekLottoNum();

        // 보너스 볼을 입력한다
        String BonusBall = Input.BonusBall();

        // 당첨 통계를 출력한다
        Output.ResultOfLotto(LottoList, LastWeekLotto, BonusBall, total*1000);
        Input.closeScanner();
    }
}
