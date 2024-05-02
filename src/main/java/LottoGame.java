public class LottoGame {

    private final InputView inputView;

    public LottoGame(InputView inputView) {
        this.inputView = inputView;
    }

    public void run() {
        LottoPrice price = LottoPrice.valueOf(inputView.readLottoPrice());
    }
}
