package QuanDen.demo.entity;


import QuanDen.demo.dto.CarDto;
import QuanDen.demo.enums.CarStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String color;
    private String name;
    private String type;
    private String transmission;
    private String description;
    private Long price;
    private Date year;
    private CarStatus carStatus;
    @Column(columnDefinition = "longblob")
    private byte[] image;
    private Long numberSeat;
    private String carOwner;
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private List<Contract> rentalContracts;

    public CarDto getCarDto(){
        CarDto carDto = new CarDto();
        carDto.setId(id);
        carDto.setName(name);
        carDto.setBrand(brand);
        carDto.setColor(color);
        carDto.setPrice(price);
        carDto.setDescription(description);
        carDto.setType(type);
        carDto.setTransmission(transmission);
        carDto.setReturnedImage(image);
        carDto.setYear(year);
        carDto.setCarStatus(carStatus);
        carDto.setNumberSeat(numberSeat);
        carDto.setCarOwner(carOwner);
        return carDto;
    }
}
