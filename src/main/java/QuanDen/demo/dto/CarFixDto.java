package QuanDen.demo.dto;


import QuanDen.demo.enums.BookCarStatus;
import QuanDen.demo.enums.CarFixStatus;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CarFixDto {

    private Long id;
    private String description;
    private Long carId;
    private String carName;
    private String username;
    private Long userId;
    private MultipartFile image;
    private byte[] returnedImage;
    private CarFixStatus carFixStatus;
    private Long contentId;
    private String message;
}
