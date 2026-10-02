# 💸 Java 로또 1~2 단계

## 기능 요구사항
1. 로또 구입 금액을 입력하면 구입 금액에 해당하는 로또를 발급해야 한다.
   1. 로또 구입 금액을 입력한다.
   2. 로또 구입 금액만큼의 개수를 반환한다.
   3. 개수 만큼의 랜덤 로또 번호를 생성한다.
2. 로또 1장의 가격은 1000원이다.
   1. 음수인지 확인한다.
   2. 음수라면 예외 처리한다.
   3. 1000원 단위인지 확인한다.
   4. 1000원 단위가 아니라면 예외 처리한다.
   5문자가 입력됐는지 확인한다.
   6문자가 입력되면 예외처리한다.
3. 로또 당첨 번호를 받아 일치한 번호 수에 따라 당첨 결과를 보여준다.
   1. 로또 당첨 번호를 입력받는다.
   2. 각각의 로또 번호에 당첨 번호가 몇 개 포함되었는지 확인한다.
   3. 당첨 번호 갯수 만큼 당첨 통계를 출력한다.
   4. 결과를 출력한다.

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
    │     │ ├── NumberGenerator.java
    │     │ ├── PurchasePrice.java
    │     │ ├── RandomNumberGenerator.java
    │     │ └── Rank.java
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
                ├── PurchasePriceTest.java
                └── RankTest.java
```

## 객체 역할
#### 시작점
- `Application`: 로또 생성에 필요한 객체들을 생성하고 controller를 실행한다.

#### domain
- `Lotto`: 로또 하나를 담당하는 객체로, 번호 개수와 중복 여부를 검증한다.
- `LottoFactory`: 로또 구입 금액을 담당하는 객체로, 구입 금액만큼 로또들을 생성한다.
- `LottoNumber`: 로또 번호 하나를 담당하는 객체로, 로또 번호 하나에 대하여 범위값을 검증한다.
- `LottoResult`: 로또와 당첨 로또를 비교해서 일치률을 계산하고 수익률을 계산한다.
- `Lottos`: 여러 장의 로또를 관리하는 객체로, 각 로또를 당첨 로또와 비교한다.
- `NumberGenerator`: 로또 생성 방식을 추상화한 인터페이스이다.
- `PurchasePrice`: 로또 구입 금액을 담당하는 객체로, 금액을 검증하고 구입 금액만큼의 로또 개수를 계산한다.
- `RandomNumberGenerator`: `NumberGenerator`의 실제 구현체로, 실제 프로그램 실행 시 중복되지 않는 로또 번호 6개를 생성한다.
- `Rank`: 로또의 당첨 등급과 상금에 대한 규칙을 관리하는 enum이다.

#### dto
- `ResultDto`: `LottoResult`의 결과 중 화면 출력에 필요한 데이터를 전달하는 DTO이다.

#### view
- `InputView`: 사용자로부터 로또 구입 금액과 당첨 로또를 입력받는다.
- `OutputView`: 생성된 로또들과 로또 결과를 출력한다.

## 테스트 내용

### LottoNumberTest

- 로또 번호가 1부터 45 사이이면 정상적으로 생성되는지 테스트
- 로또 번호가 1보다 작으면 예외가 발생하는지 테스트
- 로또 번호가 45보다 크면 예외가 발생하는지 테스트
- 같은 값을 가진 `LottoNumber`가 동일한 값으로 판단되는지 테스트

### PurchasePriceTest

- 구입 금액을 입력하지 않으면 예외가 발생하는지 테스트
- 구입 금액이 숫자가 아니면 예외가 발생하는지 테스트
- 구입 금액이 1000원 미만이면 예외가 발생하는지 테스트
- 최소 구입 금액인 1000원으로 로또 1장을 구매할 수 있는지 테스트
- 구입 금액으로 구매 가능한 로또 개수를 올바르게 계산하는지 테스트

### LottoTest

- 로또 번호가 6개가 아니면 예외가 발생하는지 테스트
- 로또 번호가 중복되면 예외가 발생하는지 테스트
- 당첨 로또와 비교하여 일치하는 번호 개수를 올바르게 계산하는지 테스트

### RankTest

- 3개 일치 시 `THREE` 등급을 반환하는지 테스트
- 4개 일치 시 `FOUR` 등급을 반환하는지 테스트
- 5개 일치 시 `FIVE` 등급을 반환하는지 테스트
- 6개 일치 시 `SIX` 등급을 반환하는지 테스트
- 0개, 1개, 2개 일치 시 당첨 등급이 존재하지 않는지 테스트
- 각 `Rank`가 올바른 당첨 금액을 반환하는지 테스트

### LottoResultTest

- 각 당첨 등급별 당첨 개수를 올바르게 집계하는지 테스트
- 0개, 1개, 2개 일치는 당첨 결과에 포함되지 않는지 테스트
- 당첨 금액과 구입 금액을 이용하여 수익률을 올바르게 계산하는지 테스트
- 수익률이 소수점 둘째 자리까지 처리되는지 테스트

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