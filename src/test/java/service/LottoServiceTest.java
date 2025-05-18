package service;

import domain.Lotto;
import domain.LottoNumber;
import domain.Lottos;
import dto.LottoPurchaseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import view.ConsoleOutputView;
import view.FakeInputView;
import view.InputView;
import view.OutputView;

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
        inputView = new FakeInputView(manualLottoNumbers);
        outputView = new ConsoleOutputView();
        lottoService = new LottoService(new LottoGenerator(new LottoNumberGenerator()));
    }

    @Test
    @DisplayName("구매 금액 10000원, 수동 로또 2개를 입력받아 로또 구매 요청을 제대로 생성한다")
    void createLottoPurchaseRequest_ShouldCreateLottoPurchaseDto() {
        // Given
        int totalAmount = 10000;
        int manualLottoCount = 2;
        int autoLottoCount = 8;

        // When
        LottoPurchaseDto purchaseRequest = lottoService.createLottoPurchaseRequest(inputView, outputView);

        // Then
        assertEquals(totalAmount, purchaseRequest.totalAmount());
        assertEquals(manualLottoCount, purchaseRequest.manualLottoCount());
        assertEquals(autoLottoCount, purchaseRequest.autoLottoCount());
    }

    @Test
    @DisplayName("로또 구매 요청을 기반으로 자동 로또와 수동 로또가 합쳐진 로또 목록을 생성한다")
    void generateLottos_ShouldGenerateCorrectLottos() {
        // Given
        Lotto lotto1 = new Lotto(List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)));
        Lotto lotto2 = new Lotto(List.of(new LottoNumber(7), new LottoNumber(8), new LottoNumber(9), new LottoNumber(10), new LottoNumber(11), new LottoNumber(12)));

        LottoPurchaseDto purchaseRequest = new LottoPurchaseDto(10000, 2, new Lottos(List.of(lotto1, lotto2)), 8);

        // When
        Lottos generatedLottos = lottoService.generateLottosFromRequest(purchaseRequest);

        // Then
        assertNotNull(generatedLottos);
        assertEquals(10, generatedLottos.count());
    }
}
