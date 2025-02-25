import controller.LottoController;

public class LottoApplication {

    public static void main(String[] args) {
        try {
            new LottoController().run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
