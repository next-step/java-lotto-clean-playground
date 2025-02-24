package view;

import dto.LottoDto;

public class LottoOutputView implements LottoView {

    public void printLotto(LottoDto lottoDto) {
        System.out.println(lottoDto);
    }

    public void printLottoAmount(int lottoAmount) {
        System.out.println(lottoAmount + "개를 구매했습니다.");
    }

}
