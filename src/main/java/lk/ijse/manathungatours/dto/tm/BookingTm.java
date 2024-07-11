package lk.ijse.manathungatours.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class BookingTm {
    private String passengerId;
    private String regNumber;
    private String driverId;
    private String description;
    private String cost;
    private  Date date;


}
