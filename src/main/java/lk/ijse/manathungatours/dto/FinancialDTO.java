package lk.ijse.manathungatours.dto;

public class FinancialDTO {
    private String busId;
    private String description;
    private String income;
    private String cost;

    public FinancialDTO() {
    }

    public FinancialDTO(String busId, String description, String income, String cost) {
        this.busId = busId;
        this.description = description;
        this.income = income;
        this.cost = cost;
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Financial{" +
                "busId='" + busId + '\'' +
                ", description='" + description + '\'' +
                ", income='" + income + '\'' +
                ", cost='" + cost + '\'' +
                '}';
    }
}
