# 로또 미션

## 폴더 구조

```
src/main/java/lotto
├── Application.java              # JVM 실행 진입점(main 메서드), LottoController 실행만 함
│
├── controller
│   └── LottoController.java       # 입력→계산→생성→출력 흐름 조립
│
├── domain
│   ├── Lotto.java                 # 번호 6개를 담는 로또 한 장 (값객체), countMatch()로 당첨번호와 일치 개수 계산
│   ├── Lottos.java                # 구매한 로또 여러 장을 관리, generate()로 개수만큼 한 번에 생성
│   ├── LottoNumberGenerator.java  # 1~45 중 중복 없는 6개 번호 생성
│   ├── PurchaseAmount.java        # 구입 금액을 구매 가능 개수로 변환 (1000원 미만이면 예외)
│   ├── WinningNumbers.java        # 지난 주 당첨 번호 (값객체), 6개/중복 검증
│   ├── Rank.java                  # 등수(enum) — 일치 개수와 상금 매핑
│   └── WinningStatistics.java     # List<Rank>를 담는 일급 컬렉션, 등수별 개수/수익률 계산
│
└── view
    ├── InputView.java             # 구입 금액, 당첨 번호 입력받기
    └── OutputView.java            # 구매 로또 목록, 당첨 통계 출력

src/test/java/lotto/domain
├── PurchaseAmountTest.java
├── LottoNumberGeneratorTest.java
├── LottosTest.java
├── WinningNumbersTest.java
└── WinningStatisticsTest.java
```
