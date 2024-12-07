package QuanDen.demo.services.home;

import QuanDen.demo.dto.CarDto;

import java.util.List;

public interface HomeService {
    List<CarDto> getAllCar();

    CarDto getCarById(Long carId);
    List<CarDto> searchCarByName(String name);

    List<CarDto> searchByNumberSeat(Long numberSeat);

}
