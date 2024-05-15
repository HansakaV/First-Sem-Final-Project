package lk.ijse.manathungatours.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlaceBooking {
   private Booking booking;
   private List<BookingDetail> OdList;

}
