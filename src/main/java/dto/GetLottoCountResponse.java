package dto;

public record GetLottoCountResponse(
        long lottoCount
) {

    public static GetLottoCountResponse from(long lottoCount) {
        return new GetLottoCountResponse(lottoCount);
    }
}
