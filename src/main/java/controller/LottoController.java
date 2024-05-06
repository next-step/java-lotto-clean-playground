package controller;

import model.AutoLotto;
import model.Lotto;
import model.LottoList;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoController {
    private InputView inputView = new InputView();
    private OutputView outputView = new OutputView();
    private LottoList lottoList;

    public LottoController(){
        startLottoGame();
    }
    public void startLottoGame() {
        int price = receiveMoneyInput();
        int manualLottoCount = manualLottoCount();
        displayLottoTickets(price, manualLottoNumbers(manualLottoCount));
        Lotto winningNumbers = getWinningNumbers();
        int bonusNum = receiveBonusNumberInput();

        int[] matchCounts = calculateMatches(lottoList, winningNumbers, bonusNum);
        double winRate = calculateWinningRate(matchCounts, price);
        outputView.printResults(matchCounts, Math.floor(winRate*100)/100 );
    }
    private int manualLottoCount(){
        return convertStringToInt(inputView.inputManualLottoCount());
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

    private int receiveBonusNumberInput() {
        return convertStringToInt(inputView.inputBonusNumber());
    }

    private int receiveMoneyInput(){
        return convertStringToInt(inputView.inputPrice());
    }
    private int convertStringToInt(String price){
        return Integer.parseInt(price);
    }
    private void displayLottoTickets(int price, List<Lotto> lottos){
        int autoCount = price/1000 - lottos.size();
        int manualCount = lottos.size();
        outputView.printTicketCount(autoCount, manualCount);
        lottoList = generateLottoList(autoCount, lottos);
        outputView.printLottoNumbers(lottoList);
    }
    private LottoList generateLottoList(int count, List<Lotto> lottos){
        lottoList = new LottoList();

        for (Lotto lotto : lottos) {
            lottoList.setLottoList(lotto);
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

    private int [] calculateMatches(LottoList ticketList, Lotto winningNumbers, int bonusNum){
        int [] matchCounts = new int[5];
        for(Lotto ticket : ticketList.getLottoList()) {
            int countMatches = ticket.calculateMatches(winningNumbers);
            if(countMatches == 3){
                matchCounts[0] ++;
            }
            else if (countMatches == 4){
                matchCounts[1] ++;
            }
            else if(countMatches == 5){
                if(ticket.bonusMatches(bonusNum)){
                    matchCounts[3] ++;
                }
                else matchCounts[2] ++;
            }
            else if(countMatches == 6){
                matchCounts[4] ++;
            }
        }
        return matchCounts;
    }
    private double calculateWinningRate(int [] matchCounts, int price){
        int totalWinnings = matchCounts[0] * 5000 + matchCounts[1] * 50000 + matchCounts[2] * 1500000 + matchCounts[3] * 30000000 + matchCounts[4] * 2000000000;
        double winningRate = (double) totalWinnings / (double) price;
        return winningRate;
    }
}
