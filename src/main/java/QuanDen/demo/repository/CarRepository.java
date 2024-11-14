package QuanDen.demo.repository;

import QuanDen.demo.dto.CarDto;
import QuanDen.demo.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;


@Repository
public interface CarRepository extends JpaRepository<Car,Long> {


    List<CarDto> findByNameContainingIgnoreCase(String name);

    @Query("SELECT c.type, COUNT(c) FROM Car c GROUP BY c.type")
    List<Object[]> getCarTypeCount();
}
