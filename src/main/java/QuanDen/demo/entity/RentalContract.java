package QuanDen.demo.entity;


import QuanDen.demo.dto.RentalContractDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
@Table(name = "rental_contract")
public class RentalContract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    // Các điều khoản và điều kiện thuê
    @Column(name = "maintenance_terms", length = 500)
    private String maintenanceTerms;

    @Column(name = "usage_terms", length = 500)
    private String usageTerms;


    @Column(name = "termination_terms", length = 500)
    private String terminationTerms;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "bookACar_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private BookACar bookACar;


    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "car_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Car car;


    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "user_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;



    public RentalContractDto getRentalContractDto(){
        RentalContractDto rentalContractDto = new RentalContractDto();

        rentalContractDto.setId(id);
        rentalContractDto.setCarColor(car.getColor());
        rentalContractDto.setCarBrand(car.getBrand());
        rentalContractDto.setCarModel(car.getType());
        rentalContractDto.setCustomerEmail(user.getEmail());
        rentalContractDto.setCustomerName(user.getName());
        rentalContractDto.setCustomerIdNumber(user.getId());
        rentalContractDto.setPaymentMethod(bookACar.getPayment());
        rentalContractDto.setMaintenanceTerms(maintenanceTerms);
        rentalContractDto.setTerminationTerms(terminationTerms);
        rentalContractDto.setYearOfManufacture(car.getYear());
        rentalContractDto.setCarName(car.getName());
        rentalContractDto.setUsageTerms(usageTerms);
        rentalContractDto.setCarId(car.getId());
        rentalContractDto.setBookACarId(bookACar.getId());
        return rentalContractDto;
    }

}
