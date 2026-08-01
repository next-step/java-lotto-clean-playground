package view;

public final class OutputView {
    //로또 몇장 사는지를 받아서 "~~개를 구매했습니다." 출력
    private OutputView() {

    }

    public static void printLottoCount(int lottoCount) {
        System.out.println(lottoCount + "개를 구매했습니다");
    }

}
