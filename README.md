# 로또 로또

## 기능 명세

### 로또 구매 [`LottoPurchase`](src/main/java/lotto/domain/LottoPurchase.java)

로또 판매 가맹점 등에서 로또를 구입하는 과정에서 금액이 오고가는 것, 로또 발급시스템을 이용해 번호가 쓰인 로또 용지를 뽑는 것을 나타낸다.

* 로또 1장의 가격은 1000원이다.
* 로또 구입 금액을 입력하면 구입 금액에 해당하는 로또를 발급해야 한다.
* 구매한 후 남는 돈 (1000원 미만)은 거스름돈으로 돌려준다.
* 일부 또는 전체 로또 번호를 수동으로 입력할 수 있다.

### 로또 발급시스템 [`LottoMaker`](src/main/java/lotto/domain/LottoMaker.java)

로또(6개의 숫자의 조합)을 만든다.

* 임의의 6개의 로또 숫자를 뽑아서 로또를 만든다.
* 로또들끼리는(숫자 6개 조합끼리는) 중복되어도 된다.

### 로또 용지 [`LottoReceipt`](src/main/java/lotto/domain/LottoReceipt.java)

한번 로또 n개를 구입하면 받는 것이다. 이름에는 용지가 들어가지만 꼭 종이라는 법은 없다.

* 목록에 로또 n개가 적혀있다.
* 총 구입금액의 정보도 가지고 있다.

### 로또 [`Lotto`](src/main/java/lotto/domain/Lotto.java)

* 로또 숫자 목록이 있다.

### 로또 숫자 목록 [`LottoNumbers`](src/main/java/lotto/domain/LottoNumbers.java)

* 총 6개의 로또 숫자가 있다.
* 6개의 숫자가 겹치지 않는지 검증한다.
* 6개의 숫자는 오름차순으로 정렬되어 있다.

### 로또 숫자 [`LottoNumber`](src/main/java/lotto/domain/LottoNumber.java)

* 숫자는 1~45 사이의 값이다.

### 우승 로또 [`WinningLotto`](src/main/java/lotto/domain/WinningLotto.java)

* 로또 숫자 목록과, 보너스 숫자가 있다.

### 로또 영수증 결과 [`LottoReceiptResult`](src/main/java/lotto/domain/LottoReceiptResult.java)

특정 로또 영수증에서 당첨된 개수가 어떻게 되는지에 대한 결과이다.

* 주어진 당첨 번호와 일치한 번호 수에 따라 결정한다.
* 받을 수 있는 상금별로 몇 개가 당첨되었는지 표시한다.
  ```
  3개 일치 (5000원) - n개
  4개 일치 (50000원) - n개
  5개 일치 (1500000원) - n개
  6개 일치, 보너스 볼 일치 (30000000원) - n개
  6개 일치 (2000000000원) - n개
  ```
* 수익률이 얼마인지 보여준다.
