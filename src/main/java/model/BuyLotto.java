package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BuyLotto {
    private static final int LOTTO_PRICE = 1000;
    private int totalPrice;
    private int lottoCount;

    private List<Integer> lottoNumberList;

    public BuyLotto(int totalPrice) {
        lottoNumberList = new ArrayList<Integer>();

        for (int i=1; i<=45; i++)
        {
            lottoNumberList.add(Integer.valueOf(i));
        }
        this.totalPrice = totalPrice;
        this.lottoCount = totalPrice/LOTTO_PRICE;
    }



    public List<Lotto> generateLotto(){
        List<Lotto> lottos = new ArrayList<>();
        for(int i=0; i<lottoCount; i++)
        {
            Lotto lotto = new Lotto(generateRandomNumbers());
            lottos.add(lotto);
        }

        return lottos;
    }

    private List<Integer> generateRandomNumbers(){
        List<Integer> numbers = this.lottoNumberList;
        Collections.shuffle(numbers);
        List<Integer> values = numbers.subList(0, 6);
        Collections.sort(values);


        return values;
    }






}
