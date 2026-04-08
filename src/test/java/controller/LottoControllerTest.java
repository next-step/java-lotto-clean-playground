package controller;

import domain.LottoNumber;
import domain.strategy.NumbersGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import view.InputView;
import view.OutputView;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LottoControllerTest {
    @Mock
    private InputView inputView;

    @Mock
    private OutputView outputView;

    @Mock
    private NumbersGenerator<LottoNumber> numbersGenerator;

    @Mock
    private Validator validator;

    @InjectMocks
    private LottoController lottoController;

    @Test
    public void 정상적으로_흐름이_진행된다() {
        //given
        when(inputView.readInput())
                .thenReturn("10000")
                .thenReturn("1")
                .thenReturn("1, 2, 3, 4, 5, 6")
                .thenReturn("10, 11, 12, 13, 14, 15")
                .thenReturn("45");

        List<LottoNumber> mockNumbers = List.of(
                LottoNumber.valueOf(1), LottoNumber.valueOf(2), LottoNumber.valueOf(3),
                LottoNumber.valueOf(4), LottoNumber.valueOf(5), LottoNumber.valueOf(6)
        );

        when(numbersGenerator.generate()).thenReturn(mockNumbers);

        when(validator.validatePriceInput(anyString())).thenReturn(10);
        when(validator.validateLottoInput(anyString())).thenReturn(mockNumbers);
        when(validator.validateManualInput(anyString(), anyInt())).thenReturn(1);
        when(validator.validateBonusNumberInput(any(), anyString())).thenReturn(LottoNumber.valueOf(45));

        //when
        lottoController.run();

        //then
        verify(outputView).printQuantity(1, 9);
        verify(outputView).printStatisticHeader();
    }
}
