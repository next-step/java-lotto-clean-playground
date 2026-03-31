import controller.Lotto;

public class Application {
    private static final Lotto lotto = new Lotto();
    public static void main(String[] args) {
        lotto.run();
    }
}
