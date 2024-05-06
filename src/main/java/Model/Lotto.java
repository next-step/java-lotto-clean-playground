package Model;
import Creator.RandomNumberCreator;

import java.util.ArrayList;

public class Lotto {
    private int price;
    private LottoCreator lottoCreator;
    private LottoValidater lottoValidater = new LottoValidater();
    private ArrayList<LottoNumber> lottoNumbers = new ArrayList<>();

    // 자동 로또
    public Lotto(LottoPrice price, LottoCreator lottoCreator) {
        this.price = price.getPrice();
        this.lottoCreator = lottoCreator;
        lottoNumbers = this.lottoCreator.getRandomNumbers(lottoNumbers,lottoValidater);
    }

    // 수동 로또
    public Lotto(LottoPrice price,ArrayList<LottoNumber> lottoNumbers){
        this.price = price.getPrice();
        lottoValidater.validateLottos(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    public int getPrice() {
        return price;
    }

    public ArrayList<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }
}
