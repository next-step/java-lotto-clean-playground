package controller;

import dto.LottoNumberResponseDto;
import model.Lotto;
import view.InputView;
import view.ResultView;

import java.util.ArrayList;
import java.util.List;

public class LottoController {

    private static final int TICKET_PRICE = 1000;

    public void lottoRun() {
        int purchaseAmount = getInputAndValidateTypically();
        validateInputMathematically(purchaseAmount);

        int lottoCount = purchaseAmount / TICKET_PRICE;

        List<Lotto> lottoTickets = generateLottoTickets(lottoCount);

        List<LottoNumberResponseDto> lottoResponseDtos = convertLottoToResponseDto(lottoTickets);

        ResultView.printLottoResult(lottoResponseDtos);

        InputView.closeScanner();
    }

    private int getInputAndValidateTypically() {
        try {
            return InputView.purchaseAmountTitle();
        } catch (Exception e) {
            throw new NumberFormatException("입력값은 숫자여야 합니다.");
        }
    }

    private void validateInputMathematically(int inputPurchaseAmount) {
        if (inputPurchaseAmount < 0) {
            throw new IllegalArgumentException("음수는 입력할 수 없습니다");
        }
        if (inputPurchaseAmount % TICKET_PRICE != 0) {
            throw new IllegalArgumentException("구매 금액은 1000원 단위로 입력할 수 있습니다.");
        }
    }

    private List<Lotto> generateLottoTickets(int lottoCount) {
        List<Lotto> lottoTickets = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            lottoTickets.add(new Lotto());
        }

        return List.copyOf(lottoTickets);
    }

    private List<LottoNumberResponseDto> convertLottoToResponseDto(List<Lotto> lottoTickets) {
        List<LottoNumberResponseDto> responseDtos = new ArrayList<>();
        for (Lotto lottoTicket : lottoTickets) {
            responseDtos.add(new LottoNumberResponseDto(lottoTicket));
        }

        return List.copyOf(responseDtos);
    }

}
