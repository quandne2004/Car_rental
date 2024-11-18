package QuanDen.demo.dto;


import QuanDen.demo.enums.RentalContractStatus;
import jakarta.persistence.Column;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class RentalContractDto {

    private Long id;

    private Long bookACarId;
    private String paymentMethod;
    @DateTimeFormat(pattern = "yyyy-MM-dd")  // Đảm bảo ngày tháng đúng định dạng
    private Date BookACarfromDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")  // Đảm bảo rằng ngày tháng đúng định dạng
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
    private RentalContractStatus rentalContractStatus;
}
