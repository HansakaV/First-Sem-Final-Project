package lk.ijse.manathungatours.model.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode

public class RouteTm {
    private String route;
    private String busReg;
    private String driverId;
    private String conductorId;
}
