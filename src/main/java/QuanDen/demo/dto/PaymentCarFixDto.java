package QuanDen.demo.dto;

import lombok.Data;
import org.springframework.stereotype.Repository;

@Data
public class PaymentCarFixDto {

    private Long id;
    private String payment;
    private String content;
    private Long carFixId;
    private Long userId;
}
