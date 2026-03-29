package view;

import constants.ScriptConstants;
import dto.LottoDto;

import java.util.List;

public class OutputView {
    public void printPurchaseResult(List<LottoDto> lottoDtoList) {
        System.out.println();
        System.out.printf(ScriptConstants.OUTPUT_PURCHASE_SCRIPT, lottoDtoList.size());
        System.out.println();
        for (LottoDto lottoDto: lottoDtoList) {
            System.out.println(lottoDto.numbers().toString());
        }
    }
}
