import controller.LottoController;

public class Application {
    public static void main(String[] args) {
        runWithRetry();
    }

    private static void runWithRetry() {
        try {
            LottoController.run();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            runWithRetry();
        }
    }
}
