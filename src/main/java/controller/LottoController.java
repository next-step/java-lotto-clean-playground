package controller;

import model.*;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class LottoController {
    private InputView inputView;
    private OutputView outputView;
    private LottoList lottoList;

    public LottoController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        startLottoGame();
    }
    public void startLottoGame() {
        UserCount userMoney = receiveMoneyInput();
        ManualCount manualCount = manualLottoCount();
        if(manualCount.getManualCount() == 0){
            displayLottoTickets(userMoney, null);
        }
        else{
            displayLottoTickets(userMoney, manualLottoNumbers(manualCount.getManualCount()));
        }
        Lotto winningNumbers = getWinningNumbers();
        BonusNumber bonusNum = receiveBonusNumberInput();
        validateDuplicate(winningNumbers, bonusNum);

        List<Integer> matchCounts = calculateMatches(lottoList, winningNumbers, bonusNum);
        double winRate = calculateWinningRate(matchCounts, userMoney);
        outputView.printResults(matchCounts, Math.floor(winRate*100)/100 );
    }
    private ManualCount manualLottoCount(){
        return new ManualCount(inputView.inputManualLottoCount());
    }
    private List<Lotto> manualLottoNumbers(int count){
        List<String> manualLottoNums = inputView.manualLottos(count);
        List<Lotto> manualLottos = new ArrayList<>();

        for (String manualLotto : manualLottoNums) {
            Lotto lotto = new Lotto();
            manualLottos.add(lotto.convertToList(manualLotto));
        }
        return manualLottos;
    }

    private BonusNumber receiveBonusNumberInput() {
        return new BonusNumber(inputView.inputBonusNumber());
    }

    private void validateDuplicate(Lotto winningLotto, BonusNumber bonusNumber){
        winningLotto.validateDuplicateWithBonus(bonusNumber.getBonusNumber());
    }

    private UserCount receiveMoneyInput() {
        return new UserCount(inputView.inputPrice());
    }

    private void displayLottoTickets(UserCount count, List<Lotto> lottos){
        int autoCount = 0;
        int manualCount = 0;
        if(lottos == null){
            autoCount = count.getCount();
            manualCount = 0;
        }
        else{
            autoCount = count.getCount() - lottos.size();
            manualCount = lottos.size();
        }
        outputView.printTicketCount(autoCount, manualCount);
        lottoList = generateLottoList(autoCount, lottos);
        outputView.printLottoNumbers(lottoList);
    }
    private LottoList generateLottoList(int count, List<Lotto> lottos){
        lottoList = new LottoList();

        if(lottos != null){
            for (Lotto lotto : lottos) {
                lottoList.setLottoList(lotto);
            }
        }
        for(int i=0; i<count; i++){
            lottoList.setLottoList(generateLotto());
        }
        return lottoList;
    }
    private Lotto generateLotto(){
        List<Integer> numbers = new ArrayList<>();
        AutoLotto autoLotto = new AutoLotto();
        numbers = autoLotto.getAutoLotto();
        return new Lotto(numbers);
    }

    private Lotto getWinningNumbers(){
        String winnerNumbersStr = inputView.inputWinnerNumber();
        Lotto winningLotto = new Lotto();
        return winningLotto.convertToList(winnerNumbersStr);
    }


    private List<Integer> calculateMatches(LottoList ticketList, Lotto winningNumbers, BonusNumber bonusNum) {
        List<Integer> matchCounts = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0));

        for (Lotto ticket : ticketList.getLottoList()) {
            int countMatches = ticket.calculateMatches(winningNumbers);
            updateMatchCounts(matchCounts, countMatches, bonusNum);
        }

        return matchCounts;
    }


    private void updateMatchCounts(List<Integer> matchCounts, int countMatches, BonusNumber bonusNum) {
        Predicate<Integer> isBonusMatch = num -> num == 5 && bonusNum.getBonusNumber() > 0;

        if (countMatches == 3) {
            matchCounts.set(0, matchCounts.get(0) + 1);
        } else if (countMatches == 4) {
            matchCounts.set(1, matchCounts.get(1) + 1);
        } else if (countMatches == 5) {
            if (isBonusMatch.test(countMatches)) {
                matchCounts.set(3, matchCounts.get(3) + 1);
            } else {
                matchCounts.set(2, matchCounts.get(2) + 1);
            }
        } else if (countMatches == 6) {
            matchCounts.set(4, matchCounts.get(4) + 1);
        }

    }
    private double calculateWinningRate(List<Integer> matchCounts, UserCount count) {
        int totalWinnings = matchCounts.get(0) * 5000 +
                matchCounts.get(1) * 50000 +
                matchCounts.get(2) * 1500000 +
                matchCounts.get(3) * 30000000 +
                matchCounts.get(4) * 2000000000;

        double winningRate = (double) totalWinnings / (double) count.getPrice();
        return winningRate;
    }

}
