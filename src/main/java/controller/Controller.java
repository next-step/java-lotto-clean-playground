package controller;

import random.ConvertToString;
import random.ReturnAutoLotto;
import util.CheckWinningResult;
import util.ConvertToIntegerList;
import util.Input;
import util.TotalLotto;
import view.OutputView;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    public static void main(String[] args) {
        OutputView.inputMessage();
        int inputMoney = Input.inputMoney();

        OutputView.inputManualMessage();
        int manualCount = Input.inputManualCount();

        OutputView.printInputinfo();

        List<List<Integer>> resultArray = new ArrayList<>();
        resultArray = TotalLotto.createResultArray();


        for (int i = 0; i < manualCount; i++) {
            String manualNumbers = Input.inputManualNumbers();

            List<Integer> manulLottoList = new ArrayList<>();
            manulLottoList = ConvertToIntegerList.removeCommasAndSpaces(manualNumbers);
            TotalLotto.createAddLotto(manulLottoList, resultArray);

        }

        OutputView.printLottoCountInfo(manualCount, Input.inputAutoCount(inputMoney / 1000, manualCount));


        for (int i = 0; i < Input.inputAutoCount(inputMoney / 1000, manualCount); i++) {
            List<Integer> autoLottoList = new ArrayList<>();
            autoLottoList = ReturnAutoLotto.randomLotto();
            TotalLotto.createAddLotto(autoLottoList, resultArray);
            //OutputView.printAutoNumbers(ConvertToString.convertToString(autoLottoList));
        }

        OutputView.printTotalNumbers(resultArray);

        OutputView.inputAnswerMessage();
        List<Integer> answerNumbers = new ArrayList<>();
        answerNumbers = ConvertToIntegerList.removeCommasAndSpaces(Input.answerNumber());

        OutputView.inputBonusMessage();
        int bonusNumber = Input.bonusNumber();

        List<Integer> resultList = CheckWinningResult.resultCount(resultArray, answerNumbers, bonusNumber);


        OutputView.resultsMessage();
        List<Integer> winnerCount = List.of(0, 6, 5, 5, 4, 3);
        List<Integer> winnerPrice = List.of(0, 2000000000, 30000000, 1500000, 50000, 5000);
        long sum = 0;
        for (int i = 5; i >= 1; i--) {
            sum += ((long) winnerPrice.get(i) * resultList.get(i));

            if (i == 2) {
                System.out.println(winnerCount.get(i) + "개 일치, 보너스 볼 일치(" + winnerPrice.get(i) + "원)- " + resultList.get(i) + "개");
                continue;
            }
            System.out.println(winnerCount.get(i) + "개 일치 (" + winnerPrice.get(i) + "원)- " + resultList.get(i) + "개");
        }

        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println("총 수익률은 " + df.format((double) sum / (double) inputMoney) + "입니다.");
    }
}


