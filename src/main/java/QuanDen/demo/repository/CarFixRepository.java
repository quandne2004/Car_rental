package QuanDen.demo.repository;

import QuanDen.demo.entity.CarFix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CarFixRepository extends JpaRepository<CarFix,Long> {
}
