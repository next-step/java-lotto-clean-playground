package controller;


public class Main {
    public static void main(String[] args) {

        LottoApplication lottoApplication = new LottoApplication();

        lottoApplication.InputMoneyAndDirectCountApp();
        lottoApplication.InputDirectNumbersApp();

        lottoApplication.MakeAutoNumbersAndLottosInfoApp();

        lottoApplication.InputCollectNumberApp();

        lottoApplication.InfoStatisticsApp();
    }
}
