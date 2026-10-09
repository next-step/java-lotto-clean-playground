# 로또 미션

## 폴더 구조

```
src/main/java/lotto
├── Application.java              # JVM 실행 진입점(main 메서드), LottoController 실행만 함
│
├── controller
│   └── LottoController.java       # purchaseLottos()(구매) / checkWinning()(당첨 확인) 두 단계로 흐름 조립
│
├── domain
│   ├── Money.java                 # 구입 금액 값객체, 1000원 미만이면 예외, 구매 가능 개수 계산, 수동 개수 초과 검증
│   ├── LottoNumber.java           # 로또 번호 하나(1~45) 값객체, 범위 검증 + equals/hashCode
│   ├── LottoNumberGenerator.java  # 1~45 중 중복 없는 6개 번호 생성
│   ├── Lotto.java                 # 번호 6개(List<LottoNumber>)를 담는 로또 한 장, contains()로 포함 여부 확인
│   ├── Lottos.java                # 구매한 로또 여러 장을 관리하는 일급 컬렉션, generate()(자동)/generateManual()(수동+자동)로 생성
│   ├── WinningNumbers.java        # 당첨 번호(Lotto를 합성) + 보너스 볼(LottoNumber), 보너스 중복/일치 여부 확인
│   ├── Rank.java                  # 등수(enum) — 일치 개수(+5개 일치 시 보너스 여부)로 등수/상금 판별
│   └── WinningStatistics.java     # List<Rank>를 담는 일급 컬렉션, 등수별 개수/수익률 계산
│
└── view
    ├── InputView.java             # 구입 금액, 수동 구매 개수/번호, 당첨 번호, 보너스 볼 입력받기
    └── OutputView.java            # 구매 로또 목록(수동/자동 구분), 당첨 통계(2등 보너스 포맷 포함) 출력

src/test/java/lotto/domain
├── MoneyTest.java
├── LottoNumberTest.java
├── LottoNumberGeneratorTest.java
├── LottosTest.java
├── WinningNumbersTest.java
└── WinningStatisticsTest.java
```


