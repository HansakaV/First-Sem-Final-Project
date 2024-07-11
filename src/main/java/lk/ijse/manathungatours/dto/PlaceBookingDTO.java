package lk.ijse.manathungatours.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlaceBookingDTO {
   private BookingDTO booking;
   private List<BookingDetailDTO> OdList;

}
