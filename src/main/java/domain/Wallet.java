package domain;

import java.util.ArrayList;

public class Wallet {
    private final Money money;
    private final Money income;
    private final ArrayList<Lotto> lottoCollection = new ArrayList<>();

    public Wallet(Money money) {
        this.money = money;
        this.income = new Money(0);

    }

    //getter
    public ArrayList<Lotto> getLottoCollection() {
        return lottoCollection;
    }
    public double getIncomeRate(){
        return (double) income.getMoney() / money.getMoney();
    }

    //step1 : 로또 자동 구매
    public void buyAutomatedLotto(){
        //살 수 있는 로또 개수 구하기
        //개수만큼 생성하기
        for(long i=0; i<this.calculateMaximumNumberOfLottos(); i++){
            lottoCollection.add(new Lotto());
        }
    }

    //step2 : 로또 당첨 확인
    //TODO : 이 메서드 덩어리는 보기 싫을 뿐 아니라 이미 검사한 로또에 대한 중복 검사를 포함합니다. 더 나은 방법을 찾아야합니다!!
    //TODO : count라는 이름과 맞지 않게, 수익률 총합에 대한 로직이 포함되어 있습니다.
    private static long countNumberOfMatches(WinningNumbers winningNumbers, Lotto lotto, long result, long numberOfMatches) {
        if(lotto.matchCount(winningNumbers) == numberOfMatches) {
            result++;
        }
        return result;
    }
    public long countThirdPlace(WinningNumbers winningNumbers){
        long result = 0;
        for(Lotto lotto: lottoCollection){
            result = countNumberOfMatches(winningNumbers, lotto, result, 3);
        }
        this.income.addMoney(result * 5000);
        return result;
    }
    public long countForthPlace(WinningNumbers winningNumbers){
        long result = 0;
        for(Lotto lotto: lottoCollection){
            result = countNumberOfMatches(winningNumbers, lotto, result, 4);
        }
        this.income.addMoney(result * 50000);
        return result;
    }
    public long countFifthPlace(WinningNumbers winningNumbers){
        long result = 0;
        for(Lotto lotto: lottoCollection){
            result = countNumberOfMatches(winningNumbers, lotto, result, 5);
        }
        this.income.addMoney(result * 1500000);
        return result;
    }
    public long countSixthPlace(WinningNumbers winningNumbers){
        long result = 0;
        for(Lotto lotto: lottoCollection){
            result = countNumberOfMatches(winningNumbers, lotto, result, 6);
        }
        this.income.addMoney(result * 2000000000L);
        return result;
    }




    private long calculateMaximumNumberOfLottos(){
        return this.money.getMoney() / 1000;
    }

}

