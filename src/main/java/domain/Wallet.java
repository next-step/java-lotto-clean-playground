package domain;

import java.util.ArrayList;
import java.util.List;

public class Wallet {
    final long PRICE_OF_LOTTO = 1000L;
    private final Money money;
    private final LottoStats lottoStats;
    private final ArrayList<Lotto> lottoCollection = new ArrayList<>();

    public Wallet(Money money) {
        this.money = money;
        this.lottoStats = new LottoStats();
    }

    //getter
    public ArrayList<Lotto> getLottoCollection() {
        return lottoCollection;
    }
    public double getIncomeRate(){
        return (double) lottoStats.getIncome().getMoney() / money.getMoney();
    }
    public LottoStats getLottoStats() {
        return this.lottoStats;
    }

    //step1 : 로또 자동 구매
    public long buyAutomatedLotto(){
        //살 수 있는 로또 개수 구하기
        //개수만큼 생성하기
        for(long i=0; i<this.calculateMaximumNumberOfLottos(); i++){
            lottoCollection.add(new Lotto());
        }
        return this.calculateMaximumNumberOfLottos();
    }

    //step4 : 로또 수동 구매
    public void buyManualLotto(List<Integer> lottoNumbers){
        if(this.money.getMoney() < PRICE_OF_LOTTO){
            throw new IllegalArgumentException("금액이 부족하여 로또를 구매할 수 없습니다.");
        }
        lottoCollection.add(new Lotto(lottoNumbers));
        this.money.useMoney(PRICE_OF_LOTTO);
    }

    //step2 : 로또 당첨 확인
    public void runRankChecks(WinningNumbers winningNumbers){
        for(Lotto lotto : lottoCollection){
            applyLottoStats(lotto, winningNumbers);
        }
    }

    private void applyLottoStats(Lotto lotto, WinningNumbers winningNumbers){
        this.lottoStats.addIncome(Rank.of(lotto.matchCount(winningNumbers), lotto.checkHasBonus(winningNumbers.getBonusNumber())).getPrizeAmount());
        if(Rank.of(lotto.matchCount(winningNumbers), lotto.checkHasBonus(winningNumbers.getBonusNumber())) == Rank.FIRST){
            lottoStats.addCountFirst();
        }
        if(Rank.of(lotto.matchCount(winningNumbers), lotto.checkHasBonus(winningNumbers.getBonusNumber())) == Rank.SECOND){
            lottoStats.addCountSecond();
        }
        if(Rank.of(lotto.matchCount(winningNumbers), lotto.checkHasBonus(winningNumbers.getBonusNumber())) == Rank.THIRD){
            lottoStats.addCountThird();
        }
        if(Rank.of(lotto.matchCount(winningNumbers), lotto.checkHasBonus(winningNumbers.getBonusNumber())) == Rank.FOURTH){
            lottoStats.addCountFourth();
        }
        if(Rank.of(lotto.matchCount(winningNumbers), lotto.checkHasBonus(winningNumbers.getBonusNumber())) == Rank.FIFTH){
            lottoStats.addCountFifth();
        }
    }

    private long calculateMaximumNumberOfLottos(){
        return this.money.getMoney() / PRICE_OF_LOTTO;
    }

}

