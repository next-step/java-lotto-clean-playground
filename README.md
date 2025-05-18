## 1단계
- 배열 대신 컬렉션
- 축약 금지
- 함수 최대한 작게 만들기
- xml 세팅하기

### 요구 사항을 큰 책임으로 나누며 클래스 단위로 분리합니다.
- 입력/출력 : view.LottoView
- 로또 : model.Lotto
- 로또 구매 : model.LottoPurchase
- 로또 생성자 : model.LottoGenerator
- 메인 : application.LottoApplication

---

### 검증
지난 미션에서, View에서 사용자 입력을 검증해주지만, 도메인 객체 자체가 항상 올바른 상태가 유지 되도록 생성자 내부에서 2차 검증을 해줄 수 있다는 것도 알게 되어 이를 적용해보았습니다.

---
### 알게 된 것
System.out.println() 에서 객체를 출력할때 내부적으로 toString( )호출하므로 오버라이딩 해줘야 한다.

---
## 2단계
- 모든 원시 값과 문자열을 포장 -> 값 객체
- 일급 컬렉션 사용

### controller
mvc 패턴으로 리팩터링 해보려고 했습니다. 웹과 소통하는게 아닌, 콘솔 어플리케이션에서 별도의 controller 가 없어도 된다고 생각해서 application.LottoApplication( 진입 )-> view -> model 로 크게 구조를 잡았습니다.

---
### 일급 컬렉션이란?
일급 컬렉션은 멤버 변수로 컬렉션만 갖고 있는 클래스,요소 타입이 값 객체면 좋다.
생성자에서 컬렉션에 대한 검증을 수행한다. 

#### - 이점 ?
1. Collection의 불변을 보장한다.
2. 모든 행위를 관리할 수 있다는 장점 ( 생성자에서 로직 검증하니까 )

지난 미션에서 생성자 검증 로직을 하면 좋겠다는 피드백을 받아, 1단계에서 반영했습니다. 이때 숫자가 6개여야하는 부분만 검증할 수 있었습니다.
>  public Lotto(List<Integer> numbers) { 
if (numbers.size() != 6) {
throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
}
this.numbers = numbers;
}

하지만, 요소 타입을 Integer 대신 LottoNumber 값 객체를 사용하여 1-45 범위 검증을 LottoNumber 생성자에서 검증할 수 있게 수정하였습니다.

1단계에서 LottoGenerator 에서 1-45 범위에 있는지 검증해주는 로직을 구현해두지 않았지만, 만약 구현했다면 그 검증을 값 객체(LottoNumber) 내부로 위임했을 것입니다.

#### 당첨 번호를 입력받는 LottoView
이미 Lotto, LottoNumber 에서 검증을 하고 있지만 사용자 입력에 대한 예외 처리를 해주었습니다.

### 알게 된 것
LottoNumber 라는 값 객체를 만들어줬는데, 객체 비교에 쓰이는 equals()는 기본적으로 주솟값을 비교하기 때문에 오버라이딩 해줘야 합니다.
Hash 기반 컬렉션에선 hashCode()를 먼저 비교한 뒤 equals()를 비교하기 때문에 haseCode()도 오버라이딩 해줘야 합니다.
### 개인적 목표
1. mvc 패턴 구현할 때, controller 에 비즈니스 로직을 넣지 말기
2. 생성자에서도 null 체크, 유효성 검사하기
3. 생성자에도 접근 제어자를 명확히 지정하기
4. 큰 책임 단위로 나눈 뒤, 필요할 때 인터페이스 사용하기
5. stream 사용하기
6. 역할을 세분화 했으면 각각에 대한 테스트 코드 작성하기
7. 유지보수하기 좋은 코드를 만들기 위한 클린 코드는 어떻게 작성하는지 공부하기