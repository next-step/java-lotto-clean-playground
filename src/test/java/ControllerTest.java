import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

class ControllerTest {
    private LottoMarket market;
    private Statics statics;
    private InputView inputView;
    private OutputView outputView;
    private Controller controller;

    @BeforeEach
    void setUp() {
        market = new LottoMarket();
        statics = mock(Statics.class);
        inputView = mock(InputView.class);
        outputView = mock(OutputView.class);
        controller = new Controller(market, statics, inputView, outputView);
    }

    @Test
    void 로또_생성_테스트() {
        when(inputView.inputLottoAmount()).thenReturn(3000);
        when(inputView.manualLottoAmount(3)).thenReturn(1);

        when(inputView.inputManualLottoNums()).thenReturn(Arrays.asList(1, 2, 3, 4, 5, 6));
        when(inputView.intputWinningNums()).thenReturn(Arrays.asList(1, 2, 3, 4, 5, 6));
        when(inputView.inputBonusBall()).thenReturn(7);

        controller.run();

        verify(outputView).printLottos(market.getAllLottos(), 1, 2);
    }
}
