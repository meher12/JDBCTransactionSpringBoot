package guru.jdbctransaction.form;

public class SendMoneyForm {

    private Long fromEmployeeId;
    private Long toEmployeeId;
    private double amount;

    public SendMoneyForm(Long fromEmployeeId, Long toEmployeeId, double amount) {
        this.fromEmployeeId = fromEmployeeId;
        this.toEmployeeId = toEmployeeId;
        this.amount = amount;
    }

    public Long getFromEmployeeId() {
        return fromEmployeeId;
    }

    public void setFromEmployeeId(Long fromEmployeeId) {
        this.fromEmployeeId = fromEmployeeId;
    }

    public Long getToEmployeeId() {
        return toEmployeeId;
    }

    public void setToEmployeeId(Long toEmployeeId) {
        this.toEmployeeId = toEmployeeId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }




}
