package QuanDen.demo.controller;


import QuanDen.demo.dto.CarDto;
import QuanDen.demo.entity.Car;
import QuanDen.demo.services.home.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HomeController {

    private final HomeService homeService;


    @GetMapping("/home")
    public ResponseEntity<List<CarDto>> getAllCar(){
        List<CarDto> carDtoList = homeService.getAllCar();
        return ResponseEntity.ok(carDtoList);
    }



    @GetMapping("/car/{carId}")
    public ResponseEntity<CarDto> getCarById(@PathVariable Long carId){
        CarDto carDto = homeService.getCarById(carId);
        if (carDto == null){
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok(carDto);
        }
    }
}
