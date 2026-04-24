package lotto;

public class Main {
    public static void main(String[] args) {
        LottoInput lottoInput = new LottoInput();
        LottoDisplay lottoDisplay = new LottoDisplay();

        LottoPlay lottoPlay = new LottoPlay();

        LottoController lottoController = new LottoController(lottoInput, lottoDisplay, lottoPlay);

        lottoController.playLotto();
    }
}
