package QuanDen.demo.services.admin.dashboard;

import QuanDen.demo.dto.DashboardStatsDTO;

public interface DashboardService {


    long getTotalCars();
    long getTotalBookings();
    long getTotalRentalContracts();
    long getTotalRevenue();
}
