package domain;

import java.util.ArrayList;
import java.util.TreeSet;

public class LottoTickets {
    //로또 타입의 객체를 특정 수량만큼 초기화할수있는, 모아둘수있는 일급컬렉션
    ArrayList<Lotto> lottoArrayList = new ArrayList<>();

    public LottoTickets() { //생성자

    }

    //정적 팩토리 패턴
    public ArrayList<Lotto> makeLottos(int lottoTotalCount){
        for (int i = 0; i < lottoTotalCount; i++) {
            lottoArrayList.add(new Lotto());
        }
        return lottoArrayList;
    }

    public TreeSet<Integer> getLottoTreeSet(int lottoTicketNumber){
        return new TreeSet<>(lottoArrayList.get(lottoTicketNumber).getRandomNumberSet());
    }


    public int getSize() {
        return lottoArrayList.size();
    }
}
