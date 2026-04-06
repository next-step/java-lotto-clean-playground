<details>
  <summary>4주차 미션 내용</summary>

  # 1단계 - 로또 자동 구매
  ## 기능 요구사항
  - 로또 구입 금액을 입력하면 구입 금액에 해당하는 로또를 발급해야 한다.
  - 로또 1장의 가격은 1000원이다.

  ## 구현 목록
  ### Model
  - `Lotto`: 6개의 숫자로 이루어진 개별 로또 객체입니다.
  - `LottoBatch`: 구매한 여러 개의 로또들을 관리하는 일급 컬렉션입니다.
  - `LottoFactory`: 설정된 규칙에 따라 유효한 숫자를 가진 `Lotto` 객체를 생성합니다.

  ### View
  - `InputView`: 사용자로부터 구입 금액 등의 입력을 받는 역할을 담당합니다.
  - `OutputView`: 로또 구매 결과 및 번호 목록을 사용자에게 출력합니다.

  ### Controller
  - `LottoPurchaseController`: 로또 구매 프로세스의 전체적인 흐름을 제어하고 모델과 뷰를 연결합니다.

  ### Common
  - `NumberGenerator`: 숫자 생성을 위한 범용 인터페이스입니다.
  - `LottoNumberGenerator`: 설정된 범위 내에서 임의의 숫자를 생성해 주는 로직을 구현한 클래스입니다.

  ### Constants
  - `LottoSettingsConstants`: 로또 관련 설정값들을 관리합니다.
  - `ScriptConstants`: 사용자에게 보여줄 메시지를 관리합니다.

  ### DTO
  - `LottoDto`: 뷰와 컨트롤러 사이에서 로또 정보를 전달하기 위한 데이터 전송 객체입니다.

  # 2단계 - 로또 당첨
  ## 기능 요구사항
  - 로또 당첨 번호를 받아 일치한 번호 수에 따라 당첨 결과를 보여준다.

</details >

# 3단계 - 로또 2등 당첨
## 기능 요구사항
- 2등을 위한 보너스볼을 추첨한다.
- 당첨 통계에 2등을 추가한다.
## 전제
- 보너스볼의 번호는 로또 번호 범위 내에 있는 번호다
- 보너스볼의 번호는 기존 당첨 번호에 포함되어 있지 않은 번호다.
## 구현 목록
- [x] WinCondition Record 클래스 만들기.
- [x] LottoResult ENUM 수정
  - [x] 2등 추가
  - [x] 보너스볼 일치에 대한 필드 추가
- [x] InputView 추가
  - [x] 보너스볼 입력 기능 추가
- [x] OutputView 추가
  - [x] 출력 내용 수정
- [x] test 작성
  - [x] bonusball로 인해 생긴 오류 수정
  - [x] bonusball 관련 test (범위)
  - [x] ENUM 관련 반환값 test 수정
# 4단계 - 로또 수동 구매
## 기능 요구사항
- 현재 로또 생성기는 자동 생성 기능만 제공한다. 사용자가 수동으로 추첨 번호를 입력할 수 있도록 해야 한다.
- 입력한 금액, 자동 생성 숫자, 수동 생성 번호를 입력하도록 해야 한다.
## 구현 목록
- [x] InputView 수정
  - [x] 사용자에게 수동 구매 갯수 입력 받기 구현
  - [x] 사용자에게 로또 입력 받기 구현
- [x] LottoFactory 수정
  - [x] List<Integer>로 로또 생성 구현
- [x] LottoPurchaseController 수정
- [x] OutputView 수정
  - [x] `printPurchaseResult` 문구 수정
# 5단계 - 리팩터링
- 기존 프로그래밍 요구사항을 다시 한번 확인하고, 학습 테스트를 통해 학습한 내용을 반영한다.
