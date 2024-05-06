package model;

public enum WinningType {
    FIFTH(3, 5000, "3개 일치 (5000원)- "),
    FOURTH(4, 50000, "4개 일치 (50000원)- "),
    THIRD(5, 150000, "5개 일치 (1500000원)- "),
    SECOND(5, 30000000, "5개 일치, 보너스 볼 일치(30000000원)- "),
    FIRTH(6, 2000000000, "6개 일치 (2000000000원)- ");

    private static final int WINNING_MIN_COUNT = 3;
    private static final String ERROR_MESSAGE = "[ERROR]";

    private String message;

    WinningType(int countOfCorrect, int reward, String message) {
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

}
