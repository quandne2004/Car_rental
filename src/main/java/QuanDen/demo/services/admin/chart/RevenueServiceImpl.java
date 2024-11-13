package QuanDen.demo.services.admin.chart;


import QuanDen.demo.enums.BookCarStatus;
import QuanDen.demo.repository.BookACarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RevenueServiceImpl implements RevenueService{

    private final BookACarRepository bookACarRepository;

    public Map<String, Object> getMonthlyRevenue() {
        List<Object[]> results = bookACarRepository.getMonthlyRevenue(BookCarStatus.APPROVED);

        Map<String, Object> response = new HashMap<>();

        // Default labels for months
        String[] monthNames = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        String[] labels = new String[12];
        Double[] values = new Double[12];

        // Initialize the values array with 0.0 for all months
        Arrays.fill(values, 0.0);

        // Fill the labels array with the month names
        System.arraycopy(monthNames, 0, labels, 0, monthNames.length);

        // Loop over the results from the repository and assign values to the correct month index
        for (Object[] result : results) {
            Integer month = (Integer) result[0];
            // Check if the revenue is a Long and convert it to Double
            Object revenueObj = result[1];
            Double totalRevenue = (revenueObj instanceof Long) ? ((Long) revenueObj).doubleValue() : (Double) revenueObj;

            // Ensure the month is valid (1-12)
            if (month >= 1 && month <= 12) {
                values[month - 1] = totalRevenue;
            }
        }

        // Put the labels and values into the response map
        response.put("labels", labels);
        response.put("values", values);

        return response;
    }


}
