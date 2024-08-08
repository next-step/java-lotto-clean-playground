package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoPaper {
    private List<Row> rows;

    public LottoPaper() {
        this.rows = new ArrayList<>();
    }

    public void writeRow(Row row) {
        rows.add(row);
    }

    public int getRowNum() {
        return rows.size();
    }

    public List<Row> getRows() {
        return rows;
    }
}
