import controller.LottoController;

public class Application {

    public static void main(String[] args) {
        try {
            new LottoController().run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
