# 💸 Java 로또

## 1~2 단계 기능 요구사항
1. 로또 구입 금액을 입력하면 구입 금액에 해당하는 로또를 발급해야 한다.
   1. 로또 구입 금액을 입력한다.
   2. 로또 구입 금액만큼의 개수를 반환한다.
   3. 개수 만큼의 랜덤 로또 번호를 생성한다.
2. 로또 1장의 가격은 1000원이다.
   1. 구입 금액이 1000원 이상인지 검증한다.
   2. 1000원 미만이면 예외 처리한다.
   3. 구입 금액이 1000원 단위인지 확인한다.
   4. 구입 금액이 1000원 단위가 아니라면 예외 처리한다.
   5. 입력값이 숫자인지 확인한다.
   6. 숫자가 아닌 값이 입력되면 예외 처리한다.
3. 로또 당첨 번호를 받아 일치한 번호 수에 따라 당첨 결과를 보여준다.
   1. 로또 당첨 번호를 입력받는다.
   2. 각각의 로또 번호에 당첨 번호가 몇 개 포함되었는지 확인한다.
   3. 당첨 번호 갯수 만큼 당첨 통계를 출력한다.
   4. 결과를 출력한다.

## 3-5 단계 기능 요구사항
1. 보너스 볼을 입력받는다.
2. 주 번호 5개 + 보너스 볼이 일치하는 경우 2등이 된다.
3. 일치 검사를 할 때 보너스 볼이 포함되는지 검사하여서 등수를 결정한다.
   - 주 번호 6개 일치: 1등
   - 주 번호 5개 + 보너스 볼 일치: 2등
   - 주 번호 5개 일치: 3등
   - 주 번호 4개 일치: 4등
   - 주 번호 3개 일치: 5등
4. 로또 번호 수동 생성기를 만든다.
5. 수동으로 생성할 로또 개수를 입력 받는다.
6. 사용자 입력을 받아서 수동 로또를 만들고, 나머지는 자동 생성으로 만든다.

## 패키지 구조
```aiignore
src
    ├── main
    │ └── java
    │     ├── Application.java
    │     ├── controller
    │     │ └── LottoController.java
    │     ├── domain
    │     │ ├── Lotto.java
    │     │ ├── LottoFactory.java
    │     │ ├── LottoNumber.java
    │     │ ├── LottoResult.java
    │     │ ├── Lottos.java
    │     │ ├── MatchResult.java
    │     │ ├── NumberGenerator.java
    │     │ ├── PurchaseCount.java
    │     │ ├── PurchasePrice.java
    │     │ ├── RandomNumberGenerator.java
    │     │ ├── Rank.java
    │     │ └── WinningLotto.java
    │     ├── dto
    │     │ └── ResultDto.java
    │     └── view
    │         ├── InputView.java
    │         └── OutputView.java
    └── test
        └── java
            └── domain
                ├── FixedNumberGenerator.java
                ├── LottoFactoryTest.java
                ├── LottoNumberTest.java
                ├── LottoResultTest.java
                ├── LottoTest.java
                ├── LottosTest.java
                ├── PurchaseCountTest.java
                ├── PurchasePriceTest.java
                ├── RankTest.java
                └── WinningLottoTest.java
```

## 객체 역할
#### 시작점
- `Application`: 로또 생성에 필요한 객체들을 생성하고 controller를 실행한다.

#### domain
- `Lotto`: 로또 하나를 담당하는 객체로, 번호 개수와 중복 여부를 검증한다.
- `LottoFactory`: `NumberGenerator`를 이용해 요청한 개수만큼 자동 로또를 생성한다.
- `LottoNumber`: 로또 번호 하나를 담당하는 객체로, 로또 번호 하나에 대하여 범위값을 검증한다.
- `LottoResult`: 각 로또의 당첨 결과를 등수별로 집계하고, 총 당첨 금액을 이용해 수익률을 계산한다.
- `Lottos`: 여러 장의 로또를 관리하는 객체로, 각 로또를 당첨 로또와 비교한다.
- `MatchResult`: 로또의 일치 개수와 보너스 볼 일치 여부를 담당하는 객체이다.
- `NumberGenerator`: 로또 생성 방식을 추상화한 인터페이스이다.
- `PurchaseCount`: 총 로또 개수로부터 수동 개수, 자동 개수를 관리한다. 
- `PurchasePrice`: 로또 구입 금액을 담당하는 객체로, 금액을 검증하고 구입 금액만큼의 로또 개수를 계산한다.
- `RandomNumberGenerator`: `NumberGenerator`의 실제 구현체로, 실제 프로그램 실행 시 중복되지 않는 로또 번호 6개를 생성한다.
- `Rank`: 로또의 당첨 등급과 상금에 대한 규칙을 관리하는 enum이다.
- `WinningLotto`: 당첨 로또를 담당하는 객체로, 주 번호와 보너스볼이 중복되는지 검증하고, 로또의 일치 개수 결과를 생성한다.

#### dto
- `ResultDto`: `LottoResult`의 결과 중 화면 출력에 필요한 데이터를 전달하는 DTO이다.

#### view
- `InputView`: 사용자로부터 로또 구입 금액, 수동 로또 구매 개수, 수동 로또 번호, 당첨 번호와 보너스 볼을 입력받는다.
- `OutputView`: 생성된 로또들과 로또 결과를 출력한다.

## 테스트 내용

### LottoNumberTest

- 로또 번호가 1부터 45 사이이면 정상적으로 생성되는지 테스트
- 로또 번호가 1보다 작으면 예외가 발생하는지 테스트
- 로또 번호가 45보다 크면 예외가 발생하는지 테스트
- 같은 값을 가진 `LottoNumber`가 동일한 값으로 판단되는지 테스트

### PurchasePriceTest

- 구입 금액이 1000원 미만이면 예외가 발생하는지 테스트
- 구입 금액이 1000원 단위가 아니면 예외가 발생하는지 테스트
- 최소 구입 금액인 1000원으로 로또 1장을 구매할 수 있는지 테스트
- 구입 금액으로 구매 가능한 로또 개수를 올바르게 계산하는지 테스트

### LottoTest

- 로또 번호가 6개가 아니면 예외가 발생하는지 테스트
- 로또 번호가 중복되면 예외가 발생하는지 테스트
- 당첨 로또와 비교하여 일치하는 번호 개수를 올바르게 계산하는지 테스트

### RankTest

- 주 번호 6개 일치 시 `FIRST`를 반환하는지 테스트
- 주 번호 5개와 보너스 볼 일치 시 `SECOND`를 반환하는지 테스트
- 주 번호 5개 일치, 보너스 볼 불일치 시 `THIRD`를 반환하는지 테스트
- 주 번호 4개 일치 시 `FOURTH`를 반환하는지 테스트
- 주 번호 3개 일치 시 `FIFTH`를 반환하는지 테스트
- 당첨 조건을 만족하지 않으면 `MISS`를 반환하는지 테스트
- 각 `Rank`가 올바른 당첨 금액을 가지는지 테스트

### LottoResultTest

- 각 당첨 등급별 당첨 개수를 올바르게 집계하는지 테스트
- 0개, 1개, 2개 일치는 당첨 결과에 포함되지 않는지 테스트
- 당첨 금액과 구입 금액을 이용하여 수익률을 올바르게 계산하는지 테스트

### LottosTest

- 여러 장의 로또를 당첨 로또와 비교하는지 테스트
- 각 로또의 일치 개수를 올바르게 계산하는지 테스트
- 여러 로또의 결과를 `LottoResult`로 올바르게 집계하는지 테스트

### RandomNumberGeneratorTest

- 로또 번호가 6개 생성되는지 테스트
- 생성된 번호에 중복이 없는지 테스트
- 생성된 번호가 유효한 로또 번호 범위에 속하는지 테스트

### FixedNumberGenerator

- 테스트 시 항상 정해진 로또 번호를 반환하도록 구성
- 랜덤 번호 생성에 의존하지 않고 결과를 예측할 수 있도록 사용
- `LottoFactory` 등 번호 생성기에 의존하는 객체의 테스트에 사용

### LottoFactoryTest

- 고정된 번호 생성기를 이용해 로또를 생성하는지 테스트
- 요청한 개수만큼 로또를 생성하는지 테스트
- 생성된 로또를 이용한 당첨 결과가 예상한 값과 일치하는지 테스트

### WinningLottoTest

- 당첨 로또 번호와 보너스볼이 중복되는지 테스트
- 로또와 당첨 로또 번호의 일치 개수를 테스트
- 로또가 보너스볼을 포함하였을때와 포함하지 않았을때 보너스 일치/불일치 테스트

### LottoCountTest

- 수동 로또 구매 개수가 0 미만인지 테스트
- 수동 로또 구매 개수가 총 로또 개수를 초과하는지 테스트