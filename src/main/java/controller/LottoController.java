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
        displayLottoTickets(price);
        Lotto winningNumbers = getWinningNumbers();
        int[] matchCounts = calculateMatches(lottoList, winningNumbers);
        double winRate = calculateWinningRate(matchCounts, price);
        outputView.printResults(matchCounts, winRate);
    }

    public int receiveMoneyInput(){
        return convertPriceToInt(inputView.inputPrice());
    }
    public int convertPriceToInt(String price){
        return Integer.parseInt(price);
    }
    public void displayLottoTickets(int price){
        int count = price/1000;
        outputView.printTicketCount(count);
        lottoList = generateLottoList(count);
    }
    public LottoList generateLottoList(int count){
        lottoList = new LottoList();
        for(int i=0; i<count; i++){
            lottoList.setLottoList(createLotto());
        }
        return lottoList;
    }

    public Lotto createLotto(){
        List<Integer> numbers = new ArrayList<>();
        AutoLotto autoLotto = new AutoLotto();
        numbers = autoLotto.getAutoLotto();
        outputView.printLottoNumbers(numbers);
        return new Lotto(numbers);
    }

    public Lotto getWinningNumbers(){
        String winnerNumbersStr = inputView.inputWinnerNumber();
        Lotto winningLotto = new Lotto();
        return winningLotto.convertToList(winnerNumbersStr);
    }

    public int [] calculateMatches(LottoList ticketList, Lotto winningNumbers){
        int [] matchCounts = new int[4];
        for(Lotto ticket : ticketList.getLottoList()) {
            int countMatches = ticket.calculateMatches(winningNumbers);
            if(countMatches == 3){
                matchCounts[0] ++;
            }
            else if (countMatches == 4){
                matchCounts[1] ++;
            }
            else if(countMatches == 5){
                matchCounts[2] ++;
            }
            else if(countMatches == 6){
                matchCounts[3] ++;
            }
        }
        return matchCounts;
    }
    public double calculateWinningRate(int [] matchCounts, int price){
        int totalWinnings = matchCounts[0] * 5000 + matchCounts[1] * 50000 + matchCounts[2] * 1500000 + matchCounts[3] * 2000000000;
        double winningRate = (double) totalWinnings / (double) price;
        return winningRate;
    }
}
