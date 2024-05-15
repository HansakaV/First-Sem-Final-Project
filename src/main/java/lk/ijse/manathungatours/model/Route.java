package lk.ijse.manathungatours.model;

public class Route {
    private String route;
    private String busReg;
    private String driverId;
    private String conductorId;

    public Route() {
    }

    public Route(String route, String busReg, String driverId, String conductorId) {
        this.route = route;
        this.busReg = busReg;
        this.driverId = driverId;
        this.conductorId = conductorId;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getBusReg() {
        return busReg;
    }

    public void setBusReg(String busReg) {
        this.busReg = busReg;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getConductorId() {
        return conductorId;
    }

    public void setConductorId(String conductorId) {
        this.conductorId = conductorId;
    }

    @Override
    public String toString() {
        return "Route{" +
                "route='" + route + '\'' +
                ", busReg='" + busReg + '\'' +
                ", driverId='" + driverId + '\'' +
                ", conductorId='" + conductorId + '\'' +
                '}';
    }
}
