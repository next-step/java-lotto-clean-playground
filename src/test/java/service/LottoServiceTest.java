package service;

import domain.Lotto;
import domain.LottoNumber;
import dto.LottoPurchaseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import view.ConsoleOutputView;
import view.InputView;
import view.OutputView;
import view.StubInputView;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class LottoServiceTest {

    private LottoService lottoService;
    private InputView inputView;
    private OutputView outputView;

    @BeforeEach
    void setUp() {
        List<String> manualLottoNumbers = List.of("1,2,3,4,5,6", "7,8,9,10,11,12");
        // given
        inputView = new StubInputView(manualLottoNumbers);
        outputView = new ConsoleOutputView();
        lottoService = new LottoService(new LottoGenerator(new LottoNumberGenerator()));
    }

    @Test
    @DisplayName("입력에 따라 총 금액, 수동/자동 로또 수를 포함한 DTO가 정확히 생성된다")
    void preparePurchase_createsCorrectDtoBasedOnInput() {
        // When
        LottoPurchaseDto purchaseRequest = lottoService.preparePurchase(inputView, outputView);

        // Then
        assertEquals(10000, purchaseRequest.totalAmount(), "총 구매 금액이 일치해야 합니다.");
        assertEquals(2, purchaseRequest.manualLottoCount(), "수동 로또 수가 일치해야 합니다.");
        assertEquals(8, purchaseRequest.autoLottoCount(), "자동 로또 수가 일치해야 합니다.");
    }

    @Test
    @DisplayName("로또 구매 요청을 기반으로 자동 로또와 수동 로또가 합쳐진 로또 목록을 생성한다")
    void generateLottos_ShouldGenerateCorrectLottos() {
        // given
        Lotto lotto1 = new Lotto(List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)));
        Lotto lotto2 = new Lotto(List.of(new LottoNumber(7), new LottoNumber(8), new LottoNumber(9),
                new LottoNumber(10), new LottoNumber(11), new LottoNumber(12)));
        LottoPurchaseDto purchaseRequest = new LottoPurchaseDto(10000, 2, List.of(lotto1, lotto2), 8);

        // when
        List<Lotto> generatedLottos = lottoService.generateLottos(purchaseRequest);

        // then
        assertNotNull(generatedLottos, "생성된 로또 목록은 null이 아니어야 합니다.");
        assertEquals(10, generatedLottos.size(), "총 로또 수는 수동 + 자동 개수와 일치해야 합니다.");
    }
}
