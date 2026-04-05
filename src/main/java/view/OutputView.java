package view;

import constants.ScriptConstants;
import dto.LottoDto;
import dto.LottoResultDto;
import model.LottoResult;

import java.util.List;
import java.util.Map;

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

    public void printStats(LottoResultDto lottoResultDto) {
        System.out.println(ScriptConstants.OUTPUT_STAT_HEADER_SCRIPT);

        for (Map.Entry<LottoResult, Integer> result : lottoResultDto.lottoResults().entrySet()) {
            LottoResult currentResult = result.getKey();
            Integer resultCount= result.getValue();
            System.out.printf(ScriptConstants.OUTPUT_STAT_SCRIPT, currentResult.getMatchCount(), currentResult.getReward(), resultCount);
            System.out.println();
        }
        System.out.println();
    }

    public void printReturnRatio(double returnRatio) {
        System.out.printf(ScriptConstants.OUTPUT_RETURN_RATIO_SCRIPT, returnRatio);
    }
}
