package QuanDen.demo.controller;


import QuanDen.demo.dto.*;
import QuanDen.demo.services.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/car")
    public ResponseEntity<?> postCar(@ModelAttribute CarDto carDto) throws IOException {
        boolean success = adminService.postCar(carDto);
        if (success){
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/allCar")
    public ResponseEntity<?> getAllCar(){
        return ResponseEntity.ok(adminService.getAllCar());
    }

    @DeleteMapping("/cars/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id){
        adminService.deleteCar(id);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/car/{id}")
    public ResponseEntity<CarDto> getCarById(@PathVariable Long id){
        CarDto carDto = adminService.getCarById(id);
        return ResponseEntity.ok(carDto);
    }

    @PutMapping("/car/{carId}")
    public ResponseEntity<Void> updateCar(@PathVariable Long carId, @ModelAttribute CarDto carDto) throws IOException{
        try{
            boolean success = adminService.updateCar(carId,carDto);
            if (success) return ResponseEntity.status(HttpStatus.OK).build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    @GetMapping("/car/bookings")
    public ResponseEntity<List<BookACarDto>> getBookings(){
        return ResponseEntity.ok(adminService.getBookings());
    }


    @GetMapping("/car/booking/{bookingId}/{status}")
    public ResponseEntity<?> changeBookingStatus(@PathVariable Long bookingId, @PathVariable String status){
        boolean success = adminService.changeBookingStatus(bookingId,status);
        if (success){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/car/search")
    public ResponseEntity<?> searchCar(@RequestBody SearchCarDto searchCarDto){
        return ResponseEntity.ok(adminService.searchCar(searchCarDto));
    }



    @GetMapping("/all-comment")
    public ResponseEntity<List<CommentDto>> getAllComment(){
        List<CommentDto> commentDtoList = adminService.getAllComment();
        return ResponseEntity.ok(commentDtoList);
    }


    @GetMapping("/car/{carId}/{status}")
    public ResponseEntity<?> changeCarStatus(@PathVariable Long carId,@PathVariable String status){
        boolean success = adminService.changeCarStatus(carId,status);
        if (success){
            return ResponseEntity.status(HttpStatus.OK).build();
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    @GetMapping("/carFix")
    public ResponseEntity<List<CarFixDto>> getAllCarFix(){
        List<CarFixDto> carFixDtos = adminService.getAllCarFix();
        return ResponseEntity.ok(carFixDtos);
    }


    
    @GetMapping("/carFix/{carFixId}/{status}")
    public ResponseEntity<?> changeCarFixStatus(@PathVariable Long carFixId,@PathVariable String status){
        boolean success = adminService.changeCarFixStatus(carFixId,status);
        if (success){
            return ResponseEntity.status(HttpStatus.OK).build();
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }






    @PostMapping("/payment/{carFixId}")
    public ResponseEntity<?> postPaymentCarFix(@PathVariable Long carFixId,@RequestBody PaymentCarFixDto paymentCarFixDto){
        PaymentCarFixDto paymentCarFixDto1 = adminService.postPaymentCarFixDto(carFixId,paymentCarFixDto);
        if (paymentCarFixDto1 == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(paymentCarFixDto1);
        }
    }


    @GetMapping("/carFix/{carFixId}")
    public ResponseEntity<?> getCarFixById(@PathVariable Long carFixId){
        CarFixDto carFixDto = adminService.getCarFixById(carFixId);
        return ResponseEntity.ok(carFixDto);
    }


    @PostMapping("/contract/{carId}")
    public ResponseEntity<ContractDto> postContract(@PathVariable Long carId,@RequestBody ContractDto contractDto){
        ContractDto contractDto1 = adminService.postContractDto(carId,contractDto);
        if (contractDto1 == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(contractDto1);
        }
    }

    @GetMapping("/contract/list")
    public ResponseEntity<List<ContractDto>> getContract(){
        List<ContractDto> contractDtos = adminService.getAllListContract();
        return ResponseEntity.ok(contractDtos);
    }

    @PutMapping("/update/contract/{contractId}")
    public ResponseEntity<ContractDto> updateContract(@PathVariable Long contractId,@RequestBody ContractDto contractDto){
        ContractDto contractDto1 = adminService.updateContract(contractId,contractDto);
        if (contractDto1 == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(contractDto1);
        }
    }



    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteContract(@PathVariable Long id){
        adminService.deleteContract(id);
        return ResponseEntity.noContent().build();
    }



    @PostMapping("/rental")
    public ResponseEntity<RentalContractDto> postRental(@RequestBody RentalContractDto rentalContractDto){
        RentalContractDto rentalContractDto1 = adminService.postContractDto(rentalContractDto);
        if (rentalContractDto1 == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(rentalContractDto1);
        }
    }



    @GetMapping("/rental")
    public ResponseEntity<List<RentalContractDto>> getAllRental(){
        List<RentalContractDto> list = adminService.getAllContractRental();
        return ResponseEntity.ok().body(list);
    }


    @GetMapping("/rental/{id}")
    public ResponseEntity<RentalContractDto> getRentalContractById(@PathVariable Long id){
        RentalContractDto rentalContractDto = adminService.getRentalContractById(id);
        return ResponseEntity.status(HttpStatus.OK).body(rentalContractDto);
    }
}
