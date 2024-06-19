package domain;

import java.util.ArrayList;

public class LottoPaper {
    private int rowNum;
    private ArrayList<ArrayList> rows;

    public LottoPaper(int rowNum) {
        validateRowNum(rowNum);
        this.rowNum = rowNum;
        this.rows = new ArrayList<>();
    }

    public void writeRow(ArrayList<Integer> row){
        rows.add(row);
    }

    public int getRowNum() {
        return rowNum;
    }

    public ArrayList<ArrayList> getRows() {
        return rows;
    }

    private void validateRowNum(int rowNum) {
        if(rowNum < 1) {
            throw new IllegalArgumentException("rowNum must be greater than 0");
        }
    }
}
