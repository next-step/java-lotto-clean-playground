package view;

import domain.Lotto;
import domain.Lottos;

public class OutputView {

    public static void printLottos(Lottos lottos){
        for(Lotto lotto : lottos.getLottos()){
            System.out.println(lotto);
        }
    }
}
