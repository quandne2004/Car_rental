package QuanDen.demo.repository;

import QuanDen.demo.dto.BookACarDto;
import QuanDen.demo.entity.BookACar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookACarRepository extends JpaRepository<BookACar,Long> {

    List<BookACar> findAllByUserId(Long userId);


    // Tính tổng giá trị từ trường price trong bảng BookACar
    @Query("SELECT SUM(b.price) FROM BookACar b")
    Long sumTotalPrice();

}
