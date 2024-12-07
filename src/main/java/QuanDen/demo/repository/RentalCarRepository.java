package QuanDen.demo.repository;

import QuanDen.demo.entity.RentalContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


@Repository
public interface RentalCarRepository extends JpaRepository<RentalContract,Long> {
    Optional<RentalContract> findByBookACarId(Long bookACarId);

    @Query("SELECT r FROM RentalContract r WHERE r.bookACar.id = :bookACarId")
    List<RentalContract> searchByBookACarId(@Param("bookACarId") Long bookACarId);
}
