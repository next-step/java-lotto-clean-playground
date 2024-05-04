## 클래스 명세서
1. Model
   - 숫자열 객체
      - 로또
      - 로또 당첨번호
   - 로또 생성기
   - 로또 당첨 결과를 저장하는 객체(Map)
2. View
   - 입력기
   - 출력기
3. Controller
   - 로또 실행 컨트롤러
4. Config
   - 당첨결과 상수(Enum)
5. Service
   - 로또 도메인의 조합으로 정의된 비즈니스 로직 수행 객체

# 1,2단계 기능명세서

## 추가된 요구사항
- [x] 모든 원시 값과 문자열을 포장한다.
- [x] 일급 컬렉션을 쓴다.

## 기능 요구사항
1. [x] 로또 구입 금액을 입력하면 구입 금액에 해당하는 로또를 발급해야 한다.
2. [x] 로또 1장의 가격은 1000원이다.
3. [x] 로또 당첨번호를 입력받은 뒤, 생성된 로또와 비교한다.
- [x] 로또 당첨번호 내부에서 로또와 비교하여 당첨결과를 전달한다.
4. [x] 비교 결과(당첨번호와 일치하는 개수) 및 수익률을 View에 전달한다.
5. [x] View는 당첨통계 및 수익률을 출력한다.
    - [x] 총 당첨금액은 `LottoStat`에서 관리한다.


# 3, 4단계 기능명세서
## 추가된 요구사항
- 2등을 위한 보너스볼을 추첨한다.
- 당첨 통계에 2등을 추가한다.
  - 2등 당첨 조건은 당첨 번호 5개 일치 + 보너스 볼 일치다.

## 기능 요구사항
- [x] `WinngingLotto`에 보너스볼 속성 값을 추가한다.
- [x] `ResultType`에 2등 타입을 추가한다.
  - [x] 보너스볼의 여부를 판단할 수 있는 `boolean` 속성을 추가한다.
- [x] 번호가 5개 일치할 경우, 보너스 볼과 비교한다.

## 테스트 코드 작성 목록
1. model
    - Lotto
    - LottoGenerator

## 제출 전 체크
- [ ] 클래스 별 코드 위아래 공백 확인
- [ ] 기존 프로그래밍 요구사항 준수하는지 확인

## 과제를 수행하면서 배운 점

## 추가로 학습할 것
