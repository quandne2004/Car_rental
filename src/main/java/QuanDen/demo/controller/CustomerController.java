package QuanDen.demo.controller;


import QuanDen.demo.dto.*;
import QuanDen.demo.entity.CarFix;
import QuanDen.demo.services.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/car")
    public ResponseEntity<List<CarDto>> getAllCars(){
        List<CarDto> carDtoList  = customerService.getAllCars();
        return ResponseEntity.ok(carDtoList);
    }


    @PostMapping("/cars/postACar/{carId}")
    public ResponseEntity<Void> bookACar(@PathVariable Long carId,@RequestBody BookACarDto bookACarDto){
       boolean success= customerService.bookACar(carId,bookACarDto);
        if (success) return ResponseEntity.status(HttpStatus.CREATED).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/car/{carId}")
    public ResponseEntity<CarDto> getCarById(@PathVariable Long carId){
       CarDto carDto = customerService.getCarById(carId);
        if (carDto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(carDto);
    }


    @GetMapping("/car/bookings/{userId}")
    public ResponseEntity<List<BookACarDto>> getBookingsByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(customerService.getBookingsByUserId(userId));
    }

    @PostMapping("/car/search")
    public ResponseEntity<?> searchCar(@RequestBody SearchCarDto searchCarDto){
        return ResponseEntity.ok(customerService.searchCar(searchCarDto));
    }


    @GetMapping("/booking/{bookACarId}")
    public ResponseEntity<BookACarDto> getBookingById(@PathVariable Long bookACarId){
        BookACarDto bookACarDto = customerService.getBookingById(bookACarId);
        if(bookACarDto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(bookACarDto);
    }



    @PostMapping("/comment/{carId}")
    public ResponseEntity<?> postComment(@PathVariable Long carId,@RequestBody CommentDto commentDto){
        CommentDto commentDto1 = customerService.postComment(commentDto,carId);
        if (commentDto1 == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(commentDto1);
        }
    }



    @GetMapping("/comments/{userId}")
    public ResponseEntity<List<CommentDto>> getCommentDtoByUserId(@PathVariable Long userId){
        List<CommentDto> commentDtoList = customerService.getCommentByUserId(userId);
        return ResponseEntity.ok(commentDtoList);
    }


    @PostMapping("/carFix/{userId}")
    public ResponseEntity<?> postCarFix(@PathVariable Long userId,@ModelAttribute CarFixDto carFixDto) throws IOException {
        CarFixDto carFixDto1 = customerService.postCarFix(userId,carFixDto);
        if (carFixDto1 == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }else {
            return ResponseEntity.status(HttpStatus.CREATED).body(carFixDto1);
        }
    }


    @GetMapping("/all-carfix")
    public ResponseEntity<List<CarFixDto>> getAllCarFix(){
        List<CarFixDto> carFixDtoList = customerService.getAllCarFix();
        return ResponseEntity.ok(carFixDtoList);
    }


    @GetMapping("/payment/{userId}")
    public ResponseEntity<List<PaymentCarFixDto>> getPaymentCarFixByUserId(@PathVariable Long userId){
        List<PaymentCarFixDto> paymentCarFixDtoList = customerService.getAllPaymentByUserId(userId);
        return ResponseEntity.ok(paymentCarFixDtoList);
    }



    @GetMapping("/carFix/{carFixId}")
    public ResponseEntity<?> getCarFixById(@PathVariable Long carFixId){
        CarFixDto carFixDto1 = customerService.getCarFixById(carFixId);
        return ResponseEntity.ok(carFixDto1);
    }



    @GetMapping("/rentalContract/{id}")
    public ResponseEntity<?> getRentalContractById(@PathVariable Long id){
        RentalContractDto rentalContractDto = customerService.getRentalContractById(id);
        return ResponseEntity.ok(rentalContractDto);
    }


    @GetMapping("/rentalContract/{rentalContractId}/{status}")
    public ResponseEntity<?> changeRentalStatus(@PathVariable Long rentalContractId,@PathVariable String status){
        boolean success = customerService.changeRentalContractStatus(rentalContractId,status);
        if (success){
            return ResponseEntity.status(HttpStatus.OK).build();
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }



    @GetMapping("/rental")
    public ResponseEntity<List<RentalContractDto>> getAllRentalContract(){
        List<RentalContractDto> list = customerService.getAllRentalContract();
        if (list == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(list);
        }
    }




   
}
