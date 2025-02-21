import config.LottoConfiguration;

public class LottoApplication {

    private static final LottoConfiguration lottoConfiguration = new LottoConfiguration();

    public static void main(String[] args) {
        try {
            lottoConfiguration.lottoController().run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
