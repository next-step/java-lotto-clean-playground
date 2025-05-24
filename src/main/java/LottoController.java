public class LottoController {

    public static void main(String[] args) {
        LottoInputView inputView = new LottoInputView();
        LottoOutputView outputView = new LottoOutputView();

        LottoService service = new LottoService(inputView, outputView);

        service.start();
    }
}
