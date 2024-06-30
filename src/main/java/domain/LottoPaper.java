package domain;

import dto.LottoPaperDto;
import dto.RowDto;
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

    public void writeRow(Row row){
        rows.add(row);
    }

    public int getRowNum() {
        return rowNum;
    }

    public List<Row> getRows() {
        return rows;
    }

    private void validateRowNum(int rowNum) {
        if(rowNum < 1) {
            throw new IllegalArgumentException("rowNum must be greater than 0");
        }
    }

    public LottoPaperDto toDto(){
        List<RowDto> rowDtos = new ArrayList<>();
        for(int i = 0; i < rowNum; i++){
            rowDtos.add(rows.get(i).toDto());
        }
        LottoPaperDto lottoPaperDto = new LottoPaperDto(rowNum,rowDtos);
        return lottoPaperDto;
    }
}
