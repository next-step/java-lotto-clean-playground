package view.enums;

public enum ProfitEnum {

    PROFIT("이득"),
    DAMAGE("손해"),
    ;

    private final String outputValue;

    ProfitEnum(String outputValue) {
        this.outputValue = outputValue;
    }
}
