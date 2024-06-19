package view;

import domain.LottoPaper;
import java.util.ArrayList;

public class OutputView {

    public void printRowNumber(int rowNum){
        System.out.println(rowNum + "개를 구매했습니다.");
    }

    public void printPaper(LottoPaper lottoPaper){
        ArrayList<ArrayList> rows = lottoPaper.getRows();
        for(int i = 0; i< lottoPaper.getRowNum(); i++){
            System.out.println(rows.get(i).toString());
        }
    }
}
