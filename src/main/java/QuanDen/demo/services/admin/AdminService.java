package QuanDen.demo.services.admin;

import QuanDen.demo.dto.*;

import java.io.IOException;
import java.util.List;

public interface AdminService {
    boolean postCar(CarDto carDto) throws IOException;

    List<CarDto> getAllCar();

    void deleteCar(Long id);

    CarDto getCarById(Long id);

    boolean updateCar(Long carId,CarDto carDto) throws IOException;

    List<BookACarDto> getBookings();

    boolean changeBookingStatus(Long bookingId,String status);
    CarDtoListDto searchCar(SearchCarDto searchCarDto);

    List<CommentDto> getAllComment();

    boolean changeCarStatus(Long carId, String status);

    List<CarFixDto> getAllCarFix();

    boolean changeCarFixStatus(Long carFixId, String status);

    PaymentCarFixDto postPaymentCarFixDto(Long carFixId,PaymentCarFixDto paymentCarFixDto);

    CarFixDto getCarFixById(Long carFixId);

    boolean postContract(ContractDto contractDto) throws IOException;

}
