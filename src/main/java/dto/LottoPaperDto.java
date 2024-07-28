package dto;

import domain.LottoPaper;
import java.util.ArrayList;
import java.util.List;

public class LottoPaperDto {

    private int rowNum;
    private List<RowDto> rows;

    public LottoPaperDto(LottoPaper lottoPaper) {
        this.rowNum = lottoPaper.getRowNum();
        this.rows = new ArrayList<>();
        for (int i = 0; i < lottoPaper.getRowNum(); i++) {
            rows.add(new RowDto(lottoPaper.getRows().get(i)));
        }
    }

    public int getRowNum() {
        return rowNum;
    }

    public List<RowDto> getRows() {
        return rows;
    }

    public LottoPaper toEntity() {
        LottoPaper lottoPaperEntity = new LottoPaper();
        for (int i = 0; i < rowNum; i++) {
            lottoPaperEntity.writeRow(rows.get(i).toEntity());
        }
        return lottoPaperEntity;
    }
}
