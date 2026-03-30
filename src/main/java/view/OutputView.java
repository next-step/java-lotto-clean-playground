package view;

import constants.LottoSettingsConstants;
import constants.ScriptConstants;
import dto.LottoDto;
import model.Lotto;

import java.util.List;

public class OutputView {
    private final List<Integer> WINNING_COUNT = List.of(3,4,5,6);
    private final List<Integer> WINNING_PRICE = List.of(
            LottoSettingsConstants.THREE_MATCH_PRICE,
            LottoSettingsConstants.FOUR_MATCH_PRICE,
            LottoSettingsConstants.FIVE_MATCH_PRICE,
            LottoSettingsConstants.SIX_MATCH_PRICE
            );

    public void printPurchaseResult(List<LottoDto> lottoDtoList) {
        System.out.println();
        System.out.printf(ScriptConstants.OUTPUT_PURCHASE_SCRIPT, lottoDtoList.size());
        System.out.println();
        for (LottoDto lottoDto: lottoDtoList) {
            System.out.println(lottoDto.numbers().toString());
        }
        System.out.println();
    }

    public void printStats(List<Integer> matchCountPerLotto) {
        System.out.println(ScriptConstants.OUTPUT_STAT_HEADER_SCRIPT);

        for (int i = 0; i < WINNING_COUNT.size(); i++){
            int currentCount = WINNING_COUNT.get(i);
            int currentPrice = WINNING_PRICE.get(i);
            int totalCount = matchCountPerLotto.stream()
                    .filter(matchCount -> currentCount == matchCount).toList().size();

            System.out.printf(ScriptConstants.OUTPUT_STAT_SCRIPT, currentCount, currentPrice, totalCount);
            System.out.println();
        }
        System.out.println();
    }

    public void printReturnRatio(double returnRatio) {
        System.out.printf(ScriptConstants.OUTPUT_RETURN_RATIO_SCRIPT, returnRatio);
    }
}
