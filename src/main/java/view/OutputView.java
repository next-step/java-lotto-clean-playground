package view;

import constants.ScriptConstants;
import dto.LottoDto;
import dto.LottoResultDto;
import model.LottoResult;

import java.util.List;
import java.util.Map;

public class OutputView {

    public void printPurchaseResult(List<LottoDto> lottoDtoList, int manuallyPurchasedCount) {
        System.out.println();
        System.out.printf(ScriptConstants.OUTPUT_PURCHASE_SCRIPT, manuallyPurchasedCount, lottoDtoList.size() - manuallyPurchasedCount);
        System.out.println();
        for (LottoDto lottoDto: lottoDtoList) {
            System.out.println(lottoDto.numbers().toString());
        }
        System.out.println();
    }

    public void printAllStats(LottoResultDto lottoResultDto) {
        System.out.println(ScriptConstants.OUTPUT_STAT_HEADER_SCRIPT);

        for (Map.Entry<LottoResult, Integer> result : lottoResultDto.lottoResults().entrySet()) {
            LottoResult currentResult = result.getKey();
            Integer resultCount= result.getValue();
            printSingleStats(currentResult, resultCount);
        }
        System.out.println();
    }

    private void printSingleStats (LottoResult lottoResult, int resultCount) {
        if (lottoResult == LottoResult.FIVE_WITH_BONUS ) {
            System.out.printf(ScriptConstants.OUTPUT_SECOND_PLACE_STAT_SCRIPT, lottoResult.getMatchCount(), lottoResult.getReward(), resultCount);
            System.out.println();
            return;
        }
        System.out.printf(ScriptConstants.OUTPUT_BASIC_LOTTO_RESULT_SCRIPT, lottoResult.getMatchCount(), lottoResult.getReward(), resultCount);
        System.out.println();
    }

    public void printReturnRatio(double returnRatio) {
        System.out.printf(ScriptConstants.OUTPUT_RETURN_RATIO_SCRIPT, returnRatio);
    }
}
