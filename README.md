<기능 요구사항>

// 1단계
-로또 구입 금액을 입력하면 구입 금액에 해당하는 로또를 발급해야 한다.
-로또 1장의 가격은 1000원이다.

구입금액을 입력해 주세요. // -> Input View

14000 // -> Input View(Scanner)


14개를 구매했습니다. // -> Output View

[8, 21, 23, 41, 42, 43] // -> Output View, collection-List? ArrayList, sort, no duplicate!!


// 2단계
지난 주 당첨 번호를 입력해 주세요. // -> Input View
1, 2, 3, 4, 5, 6 // -> Input View(Scanner)

당첨 통계 // -> Output View
---------
3개 일치 (5000원)- 1개
4개 일치 (50000원)- 0개
5개 일치 (1500000원)- 0개
6개 일치 (2000000000원)- 0개
총 수익률은 0.35입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임) // ArrayList.contains() -> SAME_NUM_COUNT++;


// 3단계
보너스 볼을 입력해 주세요. // -> Input View
7 // -> Input View(Scanner)

