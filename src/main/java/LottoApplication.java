import domain.LottoMachine;
import domain.RandomNumberGenerator;
import view.InputView;
import view.OutputView;

public class LottoApplication {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        LottoMachine lottoMachine = new LottoMachine(new RandomNumberGenerator());

        int price = inputView.getPrice();
        lottoMachine.calculateRowNum(price);
        outputView.printRowNumber(lottoMachine.getWorkingPaperRowNum());
        lottoMachine.generatePaper();
        lottoMachine.generateRows();
        outputView.printPaper(lottoMachine.getWorkingPaper());
    }
}
