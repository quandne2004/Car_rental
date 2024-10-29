package QuanDen.demo.dto;


import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;

@Data
public class RentalContractDto {

    private Long id;

    private Long bookACarId;
    private String paymentMethod;
    private Date BookACarfromDate;
    private Date BookACartoDate;
    private Long BookACarDays;
    private Long BookACarPrice;
    private String carName;

    private String carColor;
    private Date carYear;
    private String userName;
    private String userEmail;
    private String maintenanceTerms;
    private String usageTerms;
    private String terminationTerms;
}
