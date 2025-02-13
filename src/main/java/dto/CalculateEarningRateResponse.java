package dto;

public record CalculateEarningRateResponse(
        double earningRate
) {

    public static CalculateEarningRateResponse from(double earningRate) {
        return new CalculateEarningRateResponse(earningRate);
    }
}
