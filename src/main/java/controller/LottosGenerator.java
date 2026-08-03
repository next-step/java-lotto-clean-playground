package controller;

import model.Lotto;

import java.util.ArrayList;
import java.util.List;

public class LottosGenerator {
    private int autoAmount;
    private List<Lotto> lottos=new ArrayList<>();

    public LottosGenerator(int autoAmount, List<Lotto> lottos){
        this.autoAmount=autoAmount;
        this.lottos=lottos;
    }

    public List<Lotto> genLotto(){
        for(int i=0;i<autoAmount;i++){
            lottos.add(new Lotto());
        }

        return lottos;
    }
}
