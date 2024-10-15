package QuanDen.demo.dto;

import QuanDen.demo.entity.Car;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class ContractDto {


    private Long id;
    // Thông tin các bên
    private String lessorName;  // Tên bên cho thuê

    private String lesseeName;  // Tên bên thuê

    private String lesseeIdCard;  // CMND/CCCD bên thuê

    private String lesseeAddress;  // Địa chỉ bên thuê

    private String lesseePhone;  // Số điện thoại bên thuê



    private Long carId;
    private String carName;
    // Thời gian thuê
    private LocalDate rentalStartDate;  // Ngày bắt đầu thuê

    private LocalDate rentalEndDate;  // Ngày kết thúc thuê

    // Địa điểm giao nhận xe
    private String pickupLocation;  // Địa điểm nhận xe

    private String returnLocation;  // Địa điểm trả xe

    // Giá thuê và các khoản phí
    private double rentalPricePerDay;  // Giá thuê mỗi ngày

    private double deposit;  // Tiền đặt cọc

    // Điều khoản thanh toán
    private String paymentMethod;  // Phương thức thanh toán

    private boolean isPaid;  // Trạng thái thanh toán

    // Trách nhiệm pháp lý và bảo hiểm
    private String insuranceDetails;  // Thông tin bảo hiểm (nếu có)

    // Điều khoản hủy bỏ hợp đồng
    private String cancellationPolicy;  // Chính sách hủy hợp đồng

    // Các trường khác
    private String additionalTerms;  // Các điều khoản khác
}
