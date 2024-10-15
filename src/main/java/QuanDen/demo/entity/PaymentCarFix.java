package QuanDen.demo.entity;


import QuanDen.demo.dto.PaymentCarFixDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
public class PaymentCarFix {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String payment;
    private String content;


    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "carFix_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private CarFix carFix;



    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "user_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;



    public PaymentCarFixDto getPaymentCarFixDto(){
        PaymentCarFixDto paymentCarFixDto = new PaymentCarFixDto();
        paymentCarFixDto.setId(id);
        paymentCarFixDto.setPayment(payment);
        paymentCarFixDto.setCarFixId(carFix.getId());
        paymentCarFixDto.setContent(content);
        paymentCarFixDto.setUserId(user.getId());
        return paymentCarFixDto;
    }
}
