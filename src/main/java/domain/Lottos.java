package domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {

  private final List<Lotto> lottos;
  private final int ticketCount;
  private final int manualTicketCount;
  private final LottoPrice purchaseMoney;

  public Lottos(List<Lotto> lottos, int ticketCount, int manualTicketCount, LottoPrice purchaseMoney) {
    this.lottos = new ArrayList<>(lottos);
    this.ticketCount = ticketCount;
    this.manualTicketCount = manualTicketCount;
    this.purchaseMoney = purchaseMoney;
    validateManualCount();
  }

  public static Lottos from(LottoPrice lottoPrice, List<Lotto> manualLottos, int manualTicketCount,
      LottoNumberGenerator lottoNumberGenerator) {
    int ticketCount = lottoPrice.calculateTicketCount();

    int autoTicketCount = ticketCount - manualTicketCount;
    List<Lotto> lottoList = generateLottoList(manualLottos, autoTicketCount, lottoNumberGenerator);

    return new Lottos(lottoList, ticketCount, manualTicketCount, lottoPrice);
  }

  private static List<Lotto> generateLottoList(List<Lotto> manualLottos, int autoTicketCount,
      LottoNumberGenerator lottoNumberGenerator) {
    List<Lotto> lottoList = new ArrayList<>(manualLottos);
    for (int i = 0; i < autoTicketCount; i++) {
      lottoList.add(Lotto.createAuto(lottoNumberGenerator));
    }
    return lottoList;
  }

  private void validateManualCount() {
    validateManualCountExcess();
    validateNegativeManualCount();
  }

  private void validateManualCountExcess() {
    if (manualTicketCount > ticketCount) {
      throw new IllegalArgumentException("수동 구매 수는 전체 티켓 수를 초과할 수 없습니다.");
    }
  }

  private void validateNegativeManualCount() {
    if (manualTicketCount < 0) {
      throw new IllegalArgumentException("수동 구매 수는 음수일 수 없습니다.");
    }
  }

  public List<Lotto> getLottos() {
    return new ArrayList<>(lottos);
  }

  public int getTicketCount() {
    return ticketCount;
  }

  public int getManualTicketCount() {
    return manualTicketCount;
  }

  public int getAutoTicketCount() {
    return ticketCount - manualTicketCount;
  }

  public LottoPrice getPurchaseMoney() {
    return purchaseMoney;
  }
}
