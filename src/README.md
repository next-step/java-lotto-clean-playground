# 로또 미션

## 클래스 구성

### controller

- **RaceLotto**  
  로또 게임 전체 실행 흐름 관리 (금액 입력 → 로또 번호 생성 → 당첨 번호 입력 → 통계 출력)
- **RaceLottoMethod**  
  구입 금액 검증·로또 번호 생성·당첨 번호 입력·통계 산출 등 비즈니스 로직 수행

### inputView (View)

- **InputView**  
  구입 금액과 지난 주 당첨 번호 입력 받기
- **OutputView**  
  금액 안내, 구매 수량, 로또 번호, 당첨 통계 출력
- **Price**  
  구입 금액을 값 객체로 포장 (0원 이상 검증, 로또 구매 개수 계산)

### model (Domain)

- **LottoNumber**  
  1~45 사이 번호 하나를 원시값 포장 (범위 검증)
- **LottoNumbers**  
  LottoNumber 6개를 모은 일급 컬렉션 (6개 검증, 정렬, 불변 리스트)
- **LottoNumbersRepository**  
  여러 장의 LottoNumbers를 저장·조회하는 저장소 (읽기 전용 리스트 반환)

### util (Utility / Strategy)

- **LottoNumberGenerator (interface)**  
  로또 번호 생성 전략 인터페이스
- **RandomLottoNumberGenerator (implementation)**  
  1~45 번호를 섞어 6개를 랜덤으로 생성하는 기본 구현체

### Main

- **Main**  
  프로그램 엔트리 포인트

### test (JUnit5 단위 테스트)

- **PriceTest** — 값 객체 생성·예외·로또 개수 계산 테스트
- **LottoNumberTest** — 원시값 포장(범위 검증) 테스트
- **LottoNumbersTest** — 일급 컬렉션 생성·예외·불변 리스트·정렬 기능 테스트
- **LottoNumbersRepositoryTest** — 저장소 추가·조회·불변 리스트 테스트
- **RandomLottoNumberGeneratorTest** — 랜덤 생성기가 6개 번호를 반환하는지 테스트

## 기능 요구사항

- 로또 당첨 번호를 받아 일치한 번호 수에 따라 당첨 결과를 보여준다.

## 프로그래밍 요구사항

- 모든 원시 값과 문자열을 포장한다.
- 일급 컬렉션을 쓴다.

