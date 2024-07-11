package lk.ijse.manathungatours.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class BookingDetailDTO {
    private String bookingId;
    private String regNumber;
    private String description;
    private String cost;
    private Date date;


}
