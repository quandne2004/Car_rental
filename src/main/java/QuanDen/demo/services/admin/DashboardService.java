package QuanDen.demo.services.admin;

import QuanDen.demo.dto.DashboardStatsDTO;

public interface DashboardService {


    long getTotalCars();
    long getTotalBookings();
    long getTotalRentalContracts();
    long getTotalRevenue();
}
