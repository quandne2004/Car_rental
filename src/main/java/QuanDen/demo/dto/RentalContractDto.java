package QuanDen.demo.dto;


import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;

@Data
public class RentalContractDto {

    private Long id;

    private String customerName;

    private Long customerIdNumber;

    private String customerEmail;

    // Thông tin xe thuê
    private Long CarId;
    private String carName;
    private String carBrand;

    private String carModel;

    private Date yearOfManufacture;

    private String carColor;


    private Long bookACarId;
    private String paymentMethod;
    private String maintenanceTerms;
    private String usageTerms;
    private String terminationTerms;
}
