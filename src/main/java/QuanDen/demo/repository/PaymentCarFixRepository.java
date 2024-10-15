package QuanDen.demo.repository;

import QuanDen.demo.dto.PaymentCarFixDto;
import QuanDen.demo.entity.PaymentCarFix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PaymentCarFixRepository extends JpaRepository<PaymentCarFix,Long> {
    List<PaymentCarFix> findAllByUserId(Long userId);
}
