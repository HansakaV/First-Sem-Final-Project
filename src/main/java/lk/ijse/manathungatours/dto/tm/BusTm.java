package lk.ijse.manathungatours.dto.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode

public class BusTm {
    private String regNumber;
    private String seats;
    private String status;
    private String service;
}
