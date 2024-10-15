package QuanDen.demo.entity;


import QuanDen.demo.dto.ContractDto;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@Table(name = "contract")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Thông tin các bên
    @Column(name = "lessor_name", nullable = true)
    private String lessorName;  // Tên bên cho thuê

    @Column(name = "lessee_name", nullable = true)
    private String lesseeName;  // Tên bên thuê

    @Column(name = "lessee_id_card", nullable = true)
    private String lesseeIdCard;  // CMND/CCCD bên thuê

    @Column(name = "lessee_address", nullable = true)
    private String lesseeAddress;  // Địa chỉ bên thuê

    @Column(name = "lessee_phone", nullable = true)
    private String lesseePhone;  // Số điện thoại bên thuê

    // Thông tin về xe (liên kết với entity Car)
    @ManyToOne
    @JoinColumn(name = "car_id", nullable = true)
    private Car car;  // Tham chiếu đến entity Car

    // Thời gian thuê
    @Column(name = "rental_start_date", nullable = true)
    private LocalDate rentalStartDate;  // Ngày bắt đầu thuê

    @Column(name = "rental_end_date", nullable = true)
    private LocalDate rentalEndDate;  // Ngày kết thúc thuê

    // Địa điểm giao nhận xe
    @Column(name = "pickup_location", nullable = true)
    private String pickupLocation;  // Địa điểm nhận xe

    @Column(name = "return_location", nullable = true)
    private String returnLocation;  // Địa điểm trả xe

    // Giá thuê và các khoản phí
    @Column(name = "rental_price_per_day", nullable = true)
    private double rentalPricePerDay;  // Giá thuê mỗi ngày

    @Column(name = "deposit", nullable = true)
    private double deposit;  // Tiền đặt cọc

    // Điều khoản thanh toán
    @Column(name = "payment_method", nullable = true)
    private String paymentMethod;  // Phương thức thanh toán

    @Column(name = "is_paid", nullable = true)
    private boolean isPaid;  // Trạng thái thanh toán

    // Trách nhiệm pháp lý và bảo hiểm
    @Column(name = "insurance_details", nullable = true)
    private String insuranceDetails;  // Thông tin bảo hiểm (nếu có)

    // Điều khoản hủy bỏ hợp đồng
    @Column(name = "cancellation_policy", nullable = true)
    private String cancellationPolicy;  // Chính sách hủy hợp đồng

    // Các trường khác
    @Column(name = "additional_terms", nullable = true)
    private String additionalTerms;  // Các điều khoản khác



    public ContractDto getContractDto(){
        ContractDto contractDto = new ContractDto();
        contractDto.setId(id);
        contractDto.setCarId(car.getId());
        contractDto.setLesseeName(lesseeName);
        contractDto.setLesseeAddress(lesseeAddress);
        contractDto.setPaid(isPaid);
        contractDto.setLesseePhone(lesseePhone);
        contractDto.setInsuranceDetails(insuranceDetails);
        contractDto.setDeposit(deposit);
        contractDto.setPaymentMethod(paymentMethod);
        contractDto.setRentalEndDate(rentalEndDate);
        contractDto.setRentalPricePerDay(rentalPricePerDay);
        contractDto.setRentalStartDate(rentalStartDate);
        contractDto.setCancellationPolicy(cancellationPolicy);
        contractDto.setPickupLocation(pickupLocation);
        contractDto.setAdditionalTerms(additionalTerms);
        contractDto.setReturnLocation(returnLocation);
        return contractDto;

    }

}
