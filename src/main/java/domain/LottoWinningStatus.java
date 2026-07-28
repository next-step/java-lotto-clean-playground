package domain;
import java.util.List;

public class LottoWinningStatus {
  private final WinningNumbers winningNumbers;
  private final Lottos lottos;
  private final LottoMatchResults lottoMatchResults;

  // 이 클래스가 하는 역할은 정답번호로 들어온 번호와 구매한 로또들을 비교한 결과를 다시 새로운 객체로 담아서 관리하는 역할
  public LottoWinningStatus(List<Integer> winningNumbers, Lottos lottos, Integer purchaseAmount) {
    this.winningNumbers = new WinningNumbers(winningNumbers);
    this.lottos = lottos;
    this.lottoMatchResults = new LottoMatchResults(createLottoMatchResult(), purchaseAmount);
  }

  private List<LottoMatchResult> createLottoMatchResult(){
   return lottos.getLottos().stream().map(lotto -> new LottoMatchResult(lotto, winningNumbers)).toList();
  }

  public Double profitRate(){
    return lottoMatchResults.profitRate();
  }

  public LottoRankTable rankTable(){
    return lottoMatchResults.rankTable();
  }
}
