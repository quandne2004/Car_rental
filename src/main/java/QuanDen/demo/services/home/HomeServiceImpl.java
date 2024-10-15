package QuanDen.demo.services.home;


import QuanDen.demo.dto.CarDto;
import QuanDen.demo.entity.Car;
import QuanDen.demo.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HomeServiceImpl implements HomeService{

    private final CarRepository carRepository;


    @Override
    public List<CarDto> getAllCar() {
        return carRepository.findAll().stream().map(Car::getCarDto).collect(Collectors.toList());
    }

    @Override
    public CarDto getCarById(Long carId) {
        Optional<Car> carDto = carRepository.findById(carId);
        return carDto.map(Car::getCarDto).orElse(null);
    }
}
