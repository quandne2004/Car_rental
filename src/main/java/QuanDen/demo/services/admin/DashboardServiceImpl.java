package QuanDen.demo.services.admin;


import QuanDen.demo.dto.DashboardStatsDTO;
import QuanDen.demo.repository.BookACarRepository;
import QuanDen.demo.repository.CarRepository;
import QuanDen.demo.repository.RentalCarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService{

        private final CarRepository carRepository;

        private final BookACarRepository bookACarRepository;


        private final RentalCarRepository rentalContractRepository;

    public long getTotalCars() {
        return carRepository.count(); // Đếm số lượng xe
    }

    public long getTotalRevenue() {
        return bookACarRepository.sumTotalPrice(); // Tính tổng doanh thu từ bảng bookACar
    }

    public long getTotalBookings() {
        return bookACarRepository.count(); // Đếm số lượng người thuê xe từ bảng bookACar
    }

    public long getTotalRentalContracts() {
        return rentalContractRepository.count(); // Đếm số lượng hợp đồng thuê xe
    }
}



