# 로또 미션

## 폴더 구조

```
src/main/java/lotto
├── Application.java              # JVM 실행 진입점, LottoController 실행
│
├── controller
│   └── LottoController.java       # 입력→로또 생성→당첨 결과 출력 흐름 관리
│
├── domain
│   ├── Lotto.java                 # 로또 한 장 관리, 당첨 번호 및 보너스 번호 일치 여부 확인
│   ├── LottoNumber.java           # 로또 번호 원시값 포장(record), 1~45 범위 검증
│   ├── LottoNumbers.java          # 로또 번호 6개 관리, 개수 및 중복 검증
│   ├── LottoNumberGenerator.java  # 1~45 중 중복 없는 6개 번호 생성
│   ├── Lottos.java                # 로또 일급 컬렉션, 수동·자동 생성 및 목록 결합
│   ├── Money.java                 # 구입 금액 관리 및 구매 가능 개수 계산
│   ├── LottoCount.java            # 구매 개수 원시값 포장, 개수 검증 및 차감
│   ├── WinningNumbers.java        # 당첨 번호 6개와 보너스 번호 관리, 중복 검증
│   ├── Rank.java                  # 일치 개수와 보너스 번호 여부에 따른 등수 및 상금 관리
│   └── WinningStatistics.java     # 등수별 당첨 개수 및 총 수익률 계산
│
└── view
    ├── InputView.java             # 구입 금액, 수동 구매 개수·번호, 당첨 번호 및 보너스 번호 입력
    └── OutputView.java            # 구매 로또 목록, 수동·자동 구매 개수 및 당첨 통계 출력


src/test/java/lotto/domain
├── LottoCountTest.java
├── LottoNumberGeneratorTest.java
├── LottoNumbersTest.java
├── LottoNumberTest.java
├── LottoTest.java
├── LottosTest.java
├── Money.java
├── RankTest.java
├── WinningNumbersTest.java
└── WinningStatisticsTest.java
```
