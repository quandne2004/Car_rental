package QuanDen.demo.services.customer;

import QuanDen.demo.dto.*;
import QuanDen.demo.entity.RentalContract;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface CustomerService {
    List<CarDto> getAllCars();
    boolean bookACar(Long carId,BookACarDto bookACarDto);

    boolean bookACar(BookACarDto bookACarDto);

    CarDto getCarById(Long carId);
    List<BookACarDto> getBookingsByUserId(Long userId);
    CarDtoListDto searchCar(SearchCarDto searchCarDto);

    BookACarDto getBookingById(Long bookACarId);

    CommentDto postComment(CommentDto commentDto, Long carId);

    List<CommentDto> getCommentByUserId(Long userId);

    CarFixDto postCarFix( Long userId,CarFixDto carFixDto) throws IOException;

    List<CarFixDto> getAllCarFix();


    List<PaymentCarFixDto> getAllPaymentByUserId(Long userId);

    CarFixDto getCarFixById(Long carFixId);
    RentalContractDto getRentalContractById(Long id);
    List<RentalContractDto> getAllRentalContract();
    boolean changeRentalContractStatus(Long rentalContractId,String status);

    List<CarDto> searchCarByName(String name);

    RentalContractDto updateRentalContract(Long rentalContractId, RentalContractDto rentalContractDto);
    boolean updateBookCar( Long bookACarId, BookACarDto bookACarDto);

    List<CarDto> searchByNumberSeat(Long numberSeat);

    List<RentalContractDto> searchByBookACarId(Long bookACarId);
    List<BookACarDto> searchByDays(Long days);
}
