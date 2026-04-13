package lotto;

public class Application {
    public static void main(String[] args) {
        LottoGame lottoGame = new LottoGame();
        lottoGame.run(); // static이 아닌 메서드를 호출!
    }
}
