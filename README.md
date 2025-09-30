# 로또

## 📖 개요

사용자는 구입 금액을 입력하고, 그에 따라 로또가 발급됩니다. 마지막으로 당첨 번호를 입력하여 당첨 통계와 수익률을 확인할 수 있습니다.

## 📂 프로젝트 구조

```
.
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── Application.java      # 애플리케이션 시작점
│   │   │   ├── Controller
│   │   │   │   └── LottoController.java  # 게임 로직 제어
│   │   │   ├── Model
│   │   │   │   ├── Jackpot.java      # 당첨 번호
│   │   │   │   ├── Lotto.java        # 로또 1장
│   │   │   │   ├── Lottos.java       # 발급된 전체 로또
│   │   │   │   └── LottoMatcher.java # 로또 당첨 확인
│   │   │   └── View
│   │   │       ├── InputView.java    # 사용자 입력
│   │   │       └── OutView.java      # 결과 출력
│   │   └── resources
│   └── test
│       └── java
└── build.gradle
```

## ✨ 주요 기능 및 동작 원리

1.  **애플리케이션 시작 (`Application.java`)**
    -   `main` 메소드에서 `LottoController`를 생성하고 `startLotto()` 메소드를 호출하여 로또 게임을 시작합니다.

2.  **입력 (`InputView.java`)**
    -   사용자로부터 로또 구입 금액을 입력받습니다.
    -   사용자로부터 당첨 번호를 쉼표(`,`)로 구분하여 입력받습니다.

3.  **로직 처리 (`Controller` & `Model`)**
    -   **`LottoController`**:
        -   `InputView`를 통해 구입 금액을 받아, 금액만큼의 `Lotto` 객체를 생성합니다. (`Lottos`)
        -   `InputView`를 통해 당첨 번호를 받아 `Jackpot` 객체를 생성합니다.
        -   `LottoMatcher`를 통해 발급된 로또와 당첨 번호를 비교하여 당첨 통계를 계산합니다.
        -   계산된 결과를 `OutView`를 통해 출력합니다.
    -   **`Lotto`**: 1부터 45 사이의 중복되지 않는 6개의 숫자로 이루어진 로또 한 장을 의미합니다.
    -   **`Lottos`**: 사용자가 구입한 금액만큼의 `Lotto` 객체들을 리스트로 관리합니다.
    -   **`Jackpot`**: 당첨 번호 6개를 저장합니다.
    -   **`LottoMatcher`**: `Lottos`와 `Jackpot`을 비교하여, 일치하는 번호의 개수를 세고, 당첨 통계와 수익률을 계산합니다.

4.  **출력 (`OutView.java`)**
    -   발급된 로또 번호들을 출력합니다.
    -   최종 당첨 통계 (3개 일치 ~ 6개 일치)와 총수익률을 계산하여 출력합니다.

