package model;

import java.util.ArrayList;
import java.util.List;

public class Lotto {
    private final int balance;
    private int manualCount;
    private int autoCount;
    private List<LottoNumber> lottoNumberList = new ArrayList<>();

    public Lotto(int balance) {
        this.balance = balance;
    }

    public Lotto(int balance, List<LottoNumber> lottoNumberList) {
        this.balance = balance;
        this.lottoNumberList = lottoNumberList;
    }

    public void calcLottoNumbersCount(int manualCount){
        this.manualCount = manualCount;
        this.autoCount = balance/1000 - manualCount;

        if(manualCount < 0){
            throw new IllegalArgumentException("수동 복권은 0개부터 구매가 가능합니다.");
        }
        if(autoCount < 0){
            throw new IllegalArgumentException("자동 복권은 0개부터 구매가 가능합니다.");
        }
    }

    public void generateRandomLottoNumbers() {
        for(int i=0;i<autoCount;i++){
            LottoNumber lottoNumber = new LottoNumber(RandomNumGenerator.randomNumGenerate());
            lottoNumberList.add(lottoNumber);
        }
    }

    private List<Integer> stringToIntegerList(String manualLottoNumberStr){
        String[] manualLottoNumberArr = manualLottoNumberStr.split(", ");
        List<Integer> manualLottoNumber = new ArrayList<>();
        for (String s : manualLottoNumberArr) {
            manualLottoNumber.add(Integer.parseInt(s));
        }
        return manualLottoNumber;
    }

    public void addManualLottoNumbers(List<String> manualLottoNumbersStrArr){
        for (String manualLottoNumberStr : manualLottoNumbersStrArr) {
            List<Integer> manualLottoNumber = stringToIntegerList(manualLottoNumberStr);
            LottoNumber lottoNumber = new LottoNumber(manualLottoNumber);
            lottoNumberList.add(lottoNumber);
        }
    }

    public int getBalance() {
        return balance;
    }

    public int getManualCount() {
        return manualCount;
    }

    public int getAutoCount() {
        return autoCount;
    }

    public List<LottoNumber> getLottoNumberList() {
        return lottoNumberList;
    }
}
