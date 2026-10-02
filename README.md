# 로또

## 기능 요구사항
1. 로또 구입 금액을 입력하면 구입 금액에 해당하는 로또를 발급해야 한다.
   1. 로또 구입 금액을 입력한다.
       1. 음수인지 확인한다.
       2. 음수라면 예외 처리한다.
       3. 1000원 단위인지 확인한다.
       4. 1000원 단위가 아니면 예외 처리한다.
       5. 문자가 입력됐는지 확인한다.
       6. 문자가 입력되면 예외처리한다.
   2. 로또 구매 금액을 1000원으로 나누어 로또 구매 개수를 구한다.
   3. 수동으로 구매할 로또 개수를 입력받는다.
      1. 수동으로 구매할 로또 번호를 입력받는다.
   4. 로또 구매 개수에서 수동 입력 개수를 뺀 값인 로또 자동입력 개수를 구한다.
   5. 로또 자동입력 개수 만큼의 랜덤 로또 번호를 출력한다.

  
2. 로또 당첨 번호를 받아 일치한 번호 수에 따라 당첨 결과를 보여준다.
   1. 로또 당첨 번호를 입력받는다.
   2. 로또 보너스 볼을 입력받는다.
   3. 각각의 로또 번호에 당첨 번호가 몇 개 포함되었는지 확인한다.
   4. 각 일치 번호 개수와 등수 별 상금을 곱해 당첨 일치 개수별 상금을 계산한다.
      1. 5개 일치와 보너스볼 일치할 떄의 상금을 계산한다.
   5. 당첨 번호 갯수와 그에 맞는 당첨 금액의 통계를 출력한다.
   6. 당첨 금액의 총합을 구한다.
   7. 당첨 금액의 총합을 로또 구입 금액으로 나눈다.
   8. 소수점 셋째 자리에서 버림하여 수익률을 출력한다.


## 프로젝트 구조
```
java
|   Application.java
|   
+---controller
|       LottoController.java
|       
+---domain
|   |   NumberGenerator.java
|   |   RandomNumberGenerator.java
|   |   
|   +---purchase
|   |       Lotto.java
|   |       LottoNumber.java
|   |       Lottos.java
|   |       PurchasePrice.java
|   |       
|   \---winning
|           LottoRank.java
|           LottoResult.java
|           RateOfReturn.java
|           WinningLotto.java
|           
\---view
        InputView.java
        OutputView.java
```
