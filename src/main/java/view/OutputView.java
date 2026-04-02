package view;

import constants.ScriptConstants;
import dto.LottoDto;
import model.LottoResult;

import java.util.List;

public class OutputView {

    public void printPurchaseResult(List<LottoDto> lottoDtoList) {
        System.out.println();
        System.out.printf(ScriptConstants.OUTPUT_PURCHASE_SCRIPT, lottoDtoList.size());
        System.out.println();
        for (LottoDto lottoDto: lottoDtoList) {
            System.out.println(lottoDto.numbers().toString());
        }
        System.out.println();
    }

    public void printStats(List<LottoResult> matchCountPerLotto) {
        List<LottoResult> WINNING_RESULT = List.of(LottoResult.THREE, LottoResult.FOUR, LottoResult.FIVE, LottoResult.SIX);

        System.out.println(ScriptConstants.OUTPUT_STAT_HEADER_SCRIPT);

        for (LottoResult currentResult : WINNING_RESULT) {
            int resultCount = (int) matchCountPerLotto.stream().filter(result -> currentResult == result).count();

            System.out.printf(ScriptConstants.OUTPUT_STAT_SCRIPT, currentResult.matchCount, currentResult.reward, resultCount);
            System.out.println();
        }
        System.out.println();
    }

    public void printReturnRatio(double returnRatio) {
        System.out.printf(ScriptConstants.OUTPUT_RETURN_RATIO_SCRIPT, returnRatio);
    }
}
