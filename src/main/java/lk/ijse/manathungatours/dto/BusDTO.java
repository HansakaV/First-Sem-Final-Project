package lk.ijse.manathungatours.dto;

public class BusDTO {
    private String regNumber;
    private String seats;
    private String status;
    private String Service;

    public BusDTO() {
    }
    public BusDTO(String regNumber, String seats, String status, String service) {
        this.regNumber = regNumber;
        this.seats = seats;
        this.status = status;
        Service = service;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getService() {
        return Service;
    }

    public void setService(String service) {
        Service = service;
    }

    @Override
    public String toString() {
        return "Bus{" +
                "regNumber='" + regNumber + '\'' +
                ", seats='" + seats + '\'' +
                ", status='" + status + '\'' +
                ", Service='" + Service + '\'' +
                '}';
    }
}
