package lk.ijse.manathungatours.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class BookingDetail {
    private String bookingId;
    private String regNumber;
    private String description;
    private String cost;
    private Date date;


}
