package dto;

import domain.LottoPaper;
import java.util.List;

public class LottoPaperDto {
    private int rowNum;
    private List<RowDto> rows;

    public LottoPaperDto(int rowNum, List<RowDto> rows) {
        this.rowNum = rowNum;
        this.rows = rows;
    }

    public int getRowNum() {
        return rowNum;
    }

    public List<RowDto> getRows() {
        return rows;
    }

    public LottoPaper toEntity() {
        LottoPaper lottoPaperEntity = new LottoPaper(rowNum);
        for (int i = 0; i < rowNum; i++) {
            lottoPaperEntity.writeRow(rows.get(i).toEntity());
        }
        return lottoPaperEntity;
    }
}
