package QuanDen.demo.controller;


import QuanDen.demo.dto.*;
import QuanDen.demo.entity.CarFix;
import QuanDen.demo.entity.RentalContract;
import QuanDen.demo.services.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @PutMapping("/rental/{id}")
    public ResponseEntity<RentalContractDto> updateRentalContract(@PathVariable Long id, @ModelAttribute RentalContractDto rentalContractDto) throws IOException{
        RentalContractDto updatedContractDto = customerService.updateRentalContract(id, rentalContractDto);
        return ResponseEntity.ok(updatedContractDto);
    }


    @GetMapping("/search")
    public ResponseEntity<List<CarDto>> searchCarByName(@RequestParam("name") String name) {
        try {
            List<CarDto> carDtos = customerService.searchCarByName(name);
            return ResponseEntity.ok(carDtos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/updateBookACar/{bookACarId}")
    public ResponseEntity<Map<String, String>> updateBookACar(@PathVariable Long bookACarId, @RequestBody BookACarDto bookACarDto) {
        boolean updated = customerService.updateBookCar(bookACarId, bookACarDto);
        if (updated) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "BookACar details updated successfully.");
            return ResponseEntity.ok(response);  // Trả về JSON
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Failed to update BookACar.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);  // Trả về lỗi
        }
    }


    @GetMapping("/searchNumberSeat")
    public ResponseEntity<List<CarDto>> searchCarByNumberSeat(@RequestParam("numberSeat") Long numberSeat) {
        try {
            List<CarDto> carDtos = customerService.searchByNumberSeat(numberSeat);
            return ResponseEntity.ok(carDtos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }



    @GetMapping("/searchBookACarId")
    public ResponseEntity<List<RentalContractDto>> searchByBookACarId(@RequestParam("bookACarId") Long bookACarId){
        try {
            List<RentalContractDto> bookACarDtos = customerService.searchByBookACarId(bookACarId);
            return ResponseEntity.ok(bookACarDtos);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/searchDays")
    public ResponseEntity<List<BookACarDto>> searchByDays(@RequestParam("days") Long days){
        try {
            List<BookACarDto> bookACarDtos = customerService.searchByDays(days);
            return ResponseEntity.ok(bookACarDtos);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
