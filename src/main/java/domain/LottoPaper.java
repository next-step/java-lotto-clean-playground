package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoPaper {
    private int rowNum;
    private List<Row> rows;

    public LottoPaper(int rowNum) {
        validateRowNum(rowNum);
        this.rowNum = rowNum;
        this.rows = new ArrayList<>();
    }

    public void writeRow(Row row) {
        rows.add(row);
    }

    public int getRowNum() {
        return rowNum;
    }

    public List<Row> getRows() {
        return rows;
    }

    private void validateRowNum(int rowNum) {
        if (rowNum < 1) {
            throw new IllegalArgumentException("rowNum must be greater than 0");
        }
    }
}
