package QuanDen.demo.repository;

import QuanDen.demo.dto.BookACarDto;
import QuanDen.demo.entity.BookACar;
import QuanDen.demo.enums.BookCarStatus;
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

    // Lấy tổng doanh thu theo tháng chỉ cho các đơn đã thanh toán thành công
    @Query("SELECT MONTH(b.toDate) as month, SUM(b.price) as totalRevenue " +
            "FROM BookACar b " +
            "WHERE b.bookCarStatus = :status " +
            "GROUP BY MONTH(b.toDate) " +
            "ORDER BY MONTH(b.toDate)")
    List<Object[]> getMonthlyRevenue(BookCarStatus status);

}
