    package QuanDen.demo.entity;


    import QuanDen.demo.dto.RentalContractDto;
    import QuanDen.demo.enums.RentalContractStatus;
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


        private RentalContractStatus rentalContractStatus;

        @Column(name = "termination_terms", length = 500)
        private String terminationTerms;

        @ManyToOne(fetch = FetchType.LAZY,optional = false)
        @JoinColumn(name = "bookACar_id",nullable = false)
        @OnDelete(action = OnDeleteAction.CASCADE)
        @JsonIgnore
        private BookACar bookACar;





        public RentalContractDto getRentalContractDto(){
            RentalContractDto rentalContractDto = new RentalContractDto();
            rentalContractDto.setId(id);
            rentalContractDto.setBookACarDays(bookACar.getDays());
            rentalContractDto.setBookACarfromDate(bookACar.getFromDate());
            rentalContractDto.setBookACartoDate(bookACar.getToDate());
            rentalContractDto.setBookACarPrice(bookACar.getPrice());
            rentalContractDto.setBookACarId(bookACar.getId());
            rentalContractDto.setPaymentMethod(bookACar.getPayment());
            rentalContractDto.setMaintenanceTerms(maintenanceTerms);
            rentalContractDto.setTerminationTerms(terminationTerms);
            rentalContractDto.setUsageTerms(usageTerms);
            rentalContractDto.setBookACarId(bookACar.getId());
            rentalContractDto.setCarName(bookACar.getCar().getName());
            rentalContractDto.setCarColor(bookACar.getCar().getColor());
            rentalContractDto.setCarYear(bookACar.getCar().getYear());
            rentalContractDto.setUserName(bookACar.getUser().getName());
            rentalContractDto.setUserEmail(bookACar.getUser().getEmail());
            rentalContractDto.setRentalContractStatus(rentalContractStatus);
            return rentalContractDto;
        }

    }
