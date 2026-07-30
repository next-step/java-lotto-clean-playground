package controller;
import java.util.List;
import java.util.ArrayList;
import model.Lotto;

public class Controller {
    private int ticketAmount;
    private List<Lotto> lottos=new ArrayList<>();

    public Controller(int ticketAmount){
        this.ticketAmount=ticketAmount;
    }

    public List<Lotto> genLotto(){
        for(int i=0;i<ticketAmount;i++){
            lottos.add(new Lotto());
        }
        return lottos;
    }
}
