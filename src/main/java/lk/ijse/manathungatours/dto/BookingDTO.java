package lk.ijse.manathungatours.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookingDTO {
    private String bookingId;
    private String passengerId;
    private Date date;
    private String desc;

}
