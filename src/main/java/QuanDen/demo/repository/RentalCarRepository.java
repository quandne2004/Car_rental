package QuanDen.demo.repository;

import QuanDen.demo.entity.RentalContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RentalCarRepository extends JpaRepository<RentalContract,Long> {
}
