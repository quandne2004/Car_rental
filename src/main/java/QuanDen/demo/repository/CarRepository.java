package QuanDen.demo.repository;

import QuanDen.demo.dto.CarDto;
import QuanDen.demo.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Repository
public interface CarRepository extends JpaRepository<Car,Long> {


    List<CarDto> findByNameContainingIgnoreCase(String name);

    // Truy vấn lấy số lượng xe theo tên xe
    @Query("SELECT c.brand, COUNT(c) FROM Car c GROUP BY c.brand")
    List<Object[]> getCarBrandCount();

    @Query("SELECT c FROM Car c WHERE upper(c.name) LIKE upper(concat(:name, '%'))")
    List<Car> findCarByNameStartsWithIgnoreCase(@Param("name") String name);


    @Query("SELECT c FROM Car c WHERE c.numberSeat = :numberSeat")
    List<Car> findByNumberSeat(@Param("numberSeat") Long numberSeat);}
