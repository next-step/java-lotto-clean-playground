package view.enums;

public enum ProfitStatus {

    PROFIT("이득"),
    DAMAGE("손해"),
    ;

    private final String status;

    ProfitStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
