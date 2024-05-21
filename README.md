# 로또

## 구현 명세

- `Application` : 로또 실행을 담당하는 진입점 클래스

## model

- `Price` : 구매 가격 VO

### lotto

- `LottoNumber` : 로또 번호 VO
- `Lotto` : 로또 객체
- `Lottos` : 여러 개의 Lotto 객체를 관리하는 일급 컬렉션
- `LottoResult` : 로또 결과 VO

## view

- `InputView` : 입력을 담당하는 클래스
- `OutputView` : 출력을 담당하는 클래스

## controller

- `LottoController` : 로또 전체 흐름을 제어하는 클래스

<br/>

## 기능 요구사항

### 1단계 - 로또 자동 구매

- 로또 구입 금액을 입력하면 구입 금액에 해당하는 로또를 발급해야 한다.
- 로또 1장의 가격은 1000원이다.

- 실행 결과

> 위 요구사항에 따라 14000원 어치 로또를 구매하였을 경우 프로그램을 실행한 결과는 다음과 같다.

```java
구입금액을 입력해 주세요.
        14000

        14개를 구매했습니다.
        [8,21,23,41,42,43]
        [3,5,11,16,32,38]
        [7,11,16,35,36,44]
        [1,8,11,31,41,42]
        [13,14,16,38,42,45]
        [7,11,30,40,42,43]
        [2,13,22,32,38,45]
        [23,25,33,36,39,41]
        [1,3,5,14,22,45]
        [5,9,38,41,43,44]
        [2,8,9,18,19,21]
        [13,14,18,21,23,35]
        [17,21,29,37,42,45]
        [3,8,27,30,35,44]
```

### 새로운 프로그래밍 요구사항

- 배열 대신 컬렉션을 사용한다.
- 줄여 쓰지 않는다(축약 금지).
- 함수(또는 메서드)의 길이가 10라인을 넘어가지 않도록 구현한다.
    - 함수(또는 메서드)가 한 가지 일만 하도록 최대한 작게 만들어라.

### 기존 프로그래밍 요구사항

- 자바 코드 컨벤션을 지키면서 프로그래밍한다.
    - 기본적으로 Java Style Guide을 원칙으로 한다.
- indent(인덴트, 들여쓰기) depth를 2를 넘지 않도록 구현한다. 1까지만 허용한다.
    - 예를 들어 while문 안에 if문이 있으면 들여쓰기는 2이다.
    - 힌트: indent(인덴트, 들여쓰기) depth를 줄이는 좋은 방법은 함수(또는 메서드)를 분리하면 된다.
- 3항 연산자를 쓰지 않는다.
- else 예약어를 쓰지 않는다.
    - else 예약어를 쓰지 말라고 하니 switch/case로 구현하는 경우가 있는데 switch/case도 허용하지 않는다.
    - 힌트: if문에서 값을 반환하는 방식으로 구현하면 else 예약어를 사용하지 않아도 된다.

### 2단계 - 로또 당첨

- 로또 당첨 번호를 받아 일치한 번호 수에 따라 당첨 결과를 보여준다.
  
- 실행 결과

```java
구입금액을 입력해 주세요.
14000

14개를 구매했습니다.
[8,21,23,41,42,43]
[3,5,11,16,32,38]
[7,11,16,35,36,44]
[1,8,11,31,41,42]
[13,14,16,38,42,45]
[7,11,30,40,42,43]
[2,13,22,32,38,45]
[23,25,33,36,39,41]
[1,3,5,14,22,45]
[5,9,38,41,43,44]
[2,8,9,18,19,21]
[13,14,18,21,23,35]
[17,21,29,37,42,45]
[3,8,27,30,35,44]

지난 주 당첨 번호를 입력해 주세요.
1,2,3,4,5,6

당첨 통계
---------
3개 일치(5000원)-1개
4개 일치(50000원)-0개
5개 일치(1500000원)-0개
6개 일치(2000000000원)-0개
총 수익률은0.35입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)

```

### 새로운 프로그래밍 요구사항

- 모든 원시 값과 문자열을 포장한다.
- 일급 컬렉션을 쓴다
