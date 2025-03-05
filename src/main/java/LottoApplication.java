import controller.LottoController;

public class LottoApplication {

    public static void main(String[] args) {
        try {
            LottoController lottoController = LottoController.getInstance();
            lottoController.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
