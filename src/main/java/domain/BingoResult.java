package domain;

public class BingoResult {
    public enum Result {
        BINGO3(Bingo.getBingo3().getCorrectLottoNum(), "3개 일치 (5000원) - "),
        BINGO4(Bingo.getBingo4().getCorrectLottoNum(), "4개 일치 (50000원) - "),
        BINGO5(Bingo.getBingo5().getCorrectLottoNum(), "5개 일치 (1500000원) - "),
        BINGO5WB(Bingo.getBingo5wB().getCorrectLottoNum(), "5개 일치, 보너스 볼 일치 (30000000원) - "),
        BINGO6(Bingo.getBingo6().getCorrectLottoNum(), "6개 일치 (2000000000원) - ");

        final private int matchNum;

        final private String message;

        Result(int matchNum, final String message) {
            this.matchNum = matchNum;
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public int getMatchNum() {
            return matchNum;
        }
    }
}
