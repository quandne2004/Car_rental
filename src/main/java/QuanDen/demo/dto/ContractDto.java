package QuanDen.demo.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ContractDto {


    private Long id;

    // Information about the Lessor
    private String lessorName;
    private String lessorAddress;
    private String lessorTaxCode;
    private String lessorPhone;
    private String lessorEmail;
    private String lessorRepresentative;

    // Information about the Lessee
    private String lesseeName;
    private String lesseeAddress;
    private String lesseeIdentityCard;
    private String lesseePhone;
    private String lesseeEmail;
    private String lesseeRepresentative;

    // Vehicle information
    private String carType;
    private String carBrand;
    private String carColor;
    private String chassisNumber;
    private String engineNumber;
    private String licensePlate;
    private String carCondition;
    private Date manufacturingDate;
    private Long kilometersDriven;

    // Rental duration
    private Date rentalStartDate;
    private Date rentalEndDate;
    private Date handoverDate;
    private Date returnDate;

    // Rental fees
    private Double rentalPricePerDay;
    private Double totalRentalAmount;
    private Double depositAmount;
    private String paymentMethod;

    // Insurance terms
    private String insuranceResponsibility;
    private String insuranceDetails;
    private String accidentResponsibility;

    // Maintenance and repair terms
    private String maintenanceResponsibility;
    private String repairTerms;

    // Usage restrictions
    private String allowedUsageArea;
    private Long mileageLimit;
    private String usagePurpose;

    // Commitments and responsibilities
    private String vehiclePreservationCommitment;
    private String resaleOrSubleaseRights;
    private String vehicleConditionCommitment;

    // Penalties and contract termination
    private String contractTerminationTerms;
    private String penaltyAmount;

    // Dispute resolution
    private String disputeResolutionMethod;

    // Signatures
    private String lessorSignature;
    private String lesseeSignature;
    private String seal;
}
