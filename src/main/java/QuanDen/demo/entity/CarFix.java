package QuanDen.demo.entity;


import QuanDen.demo.dto.CarFixDto;
import QuanDen.demo.enums.BookCarStatus;
import QuanDen.demo.enums.CarFixStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
@Table(name = "carFix")
public class CarFix {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;


    @Column(columnDefinition = "longblob")
    private byte[] image;

    private CarFixStatus carFixStatus;


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







    public CarFixDto getCarFixDto(){
        CarFixDto carFixDto = new CarFixDto();
        carFixDto.setId(id);
        carFixDto.setCarName(car.getName());
        carFixDto.setCarId(car.getId());
        carFixDto.setReturnedImage(image);
        carFixDto.setCarFixStatus(carFixStatus);
        carFixDto.setDescription(description);
        carFixDto.setUsername(user.getName());
        carFixDto.setUserId(user.getId());
        return carFixDto;
    }
}
