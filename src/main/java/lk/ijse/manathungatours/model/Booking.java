package lk.ijse.manathungatours.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Booking {
    private String bookingId;
    private String passengerId;
    private Date date;
    private String desc;

}
