package QuanDen.demo.services.admin;


import QuanDen.demo.dto.*;
import QuanDen.demo.entity.*;
import QuanDen.demo.enums.BookCarStatus;
import QuanDen.demo.enums.CarFixStatus;
import QuanDen.demo.enums.CarStatus;
import QuanDen.demo.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{
    private final CarRepository carRepository;

    private final BookACarRepository bookACarRepository;
    private final CommentRepository commentRepository;
    private final CarFixRepository carFixRepository;
    private final UserRepository userRepository;
    private final PaymentCarFixRepository paymentCarFixRepository;

    private final RentalCarRepository rentalCarRepository;

    @Override
    public boolean postCar(CarDto carDto) throws IOException {
   try{
       Car car = new Car();
       car.setName(carDto.getName());
       car.setBrand(carDto.getBrand());
       car.setColor(carDto.getColor());
       car.setPrice(carDto.getPrice());
       car.setType(carDto.getType());
       car.setYear(carDto.getYear());
       car.setCarStatus(CarStatus.FIXING);
       car.setDescription(carDto.getDescription());
       car.setTransmission(carDto.getTransmission());
       car.setImage(carDto.getImage().getBytes());
       car.setNumberSeat(carDto.getNumberSeat());
       car.setCarOwner(carDto.getCarOwner());
       carRepository.save(car);
       return true;
   }catch (Exception e){
       return false;
   }

    }



    @Override
    public List<CarDto> getAllCar() {
        return carRepository.findAll().stream().map(Car::getCarDto).collect(Collectors.toList());
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public CarDto getCarById(Long id) {
        Optional<Car> optionalCar = carRepository.findById(id);
        return optionalCar.map(Car::getCarDto).orElse(null);
    }

    @Override
    public boolean updateCar(Long carId, CarDto carDto) throws IOException {
        Optional<Car> optionalCar = carRepository.findById(carId);
        if (optionalCar.isPresent()){
            Car existingCar  = optionalCar.get();
            if (carDto.getImage() != null)
                existingCar.setImage(carDto.getImage().getBytes());
            existingCar.setPrice(carDto.getPrice());
            existingCar.setYear(carDto.getYear());
            existingCar.setType(carDto.getType());
            existingCar.setDescription(carDto.getDescription());
            existingCar.setTransmission(carDto.getTransmission());
            existingCar.setColor(carDto.getColor());
            existingCar.setName(carDto.getName());
            existingCar.setBrand(carDto.getBrand());
            carRepository.save(existingCar);
            return true;
        }else {
            return false;

        }
    }

    @Override
    public List<BookACarDto> getBookings() {
        return bookACarRepository.findAll().stream().map(BookACar::getBookACarDto).collect(Collectors.toList());
    }

    @Override
    public boolean changeBookingStatus(Long bookingId, String status) {
        Optional<BookACar> optional = bookACarRepository.findById(bookingId);
        if (optional.isPresent()){
            BookACar existingBookACar = optional.get();
            if (Objects.equals(status,"Approve"))
                existingBookACar.setBookCarStatus(BookCarStatus.APPROVED);
            else
                existingBookACar.setBookCarStatus(BookCarStatus.REJECTED);
            bookACarRepository.save(existingBookACar);
            return true;
        }
        return false;
    }

    @Override
    public CarDtoListDto searchCar(SearchCarDto searchCarDto) {
        Car car = new Car();
        car.setBrand(searchCarDto.getBrand());
        car.setType(searchCarDto.getType());
        car.setTransmission(searchCarDto.getTransmission());
        car.setColor(searchCarDto.getColor());
        car.setNumberSeat(searchCarDto.getNumberSeat());
        ExampleMatcher exampleMatcher =
                ExampleMatcher.matchingAll()
                        .withMatcher("brand",ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                        .withMatcher("type",ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                        .withMatcher("transmission",ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                        .withMatcher("color",ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                        .withMatcher("numberSeat", ExampleMatcher.GenericPropertyMatchers.exact());
        Example<Car> carExample = Example.of(car,exampleMatcher);
        List<Car> carList = carRepository.findAll(carExample);
        CarDtoListDto carDtoListDto = new CarDtoListDto();
        carDtoListDto.setCarDtoList(carList.stream().map(Car::getCarDto).collect(Collectors.toList()));
        return carDtoListDto;
    }

    @Override
    public List<CommentDto> getAllComment() {
        return commentRepository.findAll().stream().map(Comment::getCommentDto).collect(Collectors.toList());
    }

    @Override
    public boolean changeCarStatus(Long carId, String status) {
        Optional<Car> optionalCar = carRepository.findById(carId);
        if (optionalCar.isPresent()){
            Car existingCar = optionalCar.get();
            existingCar.setCarStatus(CarStatus.FIXING);
            if (Objects.equals(status,"Use")){
                existingCar.setCarStatus(CarStatus.USE);
            }else if (Objects.equals(status,"Booked")){
                existingCar.setCarStatus(CarStatus.BOOKED);
            }else {
                existingCar.setCarStatus(CarStatus.FIXING);
            }
            carRepository.save(existingCar);
            return true;
        }
        return false;
    }

    @Override
    public List<CarFixDto> getAllCarFix() {
        return carFixRepository.findAll().stream().map(CarFix::getCarFixDto).collect(Collectors.toList());
    }



    @Override
    public boolean changeCarFixStatus(Long carFixId, String status) {
        Optional<CarFix> optionalCarFix = carFixRepository.findById(carFixId);
        if (optionalCarFix.isPresent()){
            CarFix existingCarFix = optionalCarFix.get();
            if (Objects.equals(status,"FIXED")){
                existingCarFix.setCarFixStatus(CarFixStatus.FIXED);
            }else {
                existingCarFix.setCarFixStatus(CarFixStatus.HASFIXED);
            }
            carFixRepository.save(existingCarFix);
            return true;
        }
        return false;
    }

    @Override
    public PaymentCarFixDto postPaymentCarFixDto(Long carFixId,PaymentCarFixDto paymentCarFixDto) {
        Optional<CarFix> carFix = carFixRepository.findById(carFixId);
        Optional<User> optionalUser = userRepository.findById(paymentCarFixDto.getUserId());
        if (carFix.isPresent() && optionalUser.isPresent()){
            PaymentCarFix paymentCarFix = new PaymentCarFix();
            paymentCarFix.setPayment(paymentCarFixDto.getPayment());
            paymentCarFix.setUser(optionalUser.get());
            paymentCarFix.setContent(paymentCarFixDto.getContent());
            PaymentCarFix paymentCarFix1  = paymentCarFixRepository.save(paymentCarFix);
            PaymentCarFixDto paymentCarFixDto1 = new PaymentCarFixDto();
            paymentCarFixDto1.setId(paymentCarFix1.getId());
            return paymentCarFixDto1;
        }
        return null;
    }

    @Override
    public CarFixDto getCarFixById(Long carFixId) {
        Optional<CarFix> optionalCarFix = carFixRepository.findById(carFixId);
        return optionalCarFix.map(CarFix::getCarFixDto).orElse(null);
    }







    @Override
    public RentalContractDto postContractDto(RentalContractDto rentalContractDto){

        Optional<BookACar> optionalBookACar = bookACarRepository.findById(rentalContractDto.getBookACarId());
        if (optionalBookACar.isPresent()){
            RentalContract rentalContract = new RentalContract();
            rentalContract.setBookACar(optionalBookACar.get());
            rentalContract.setMaintenanceTerms(rentalContractDto.getMaintenanceTerms());
            rentalContract.setTerminationTerms(rentalContractDto.getTerminationTerms());
            rentalContract.setRentalContractStatus(rentalContractDto.getRentalContractStatus());
            rentalContract.setUsageTerms(rentalContractDto.getUsageTerms());
            RentalContract rentalContract1 = rentalCarRepository.save(rentalContract);
            RentalContractDto rentalContractDto1 = new RentalContractDto();
            rentalContractDto1.setId(rentalContract1.getId());
            return rentalContractDto1;
        }
        return null;
    }


    @Override
    public List<RentalContractDto> getAllContractRental(){
        return rentalCarRepository.findAll().stream().map(RentalContract::getRentalContractDto).collect(Collectors.toList());
    }



    @Override
    public RentalContractDto getRentalContractById(Long rentalContractId) {
        RentalContract rentalContract = rentalCarRepository.findById(rentalContractId)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy hợp đồng thuê xe với ID: " + rentalContractId));
        return rentalContract.getRentalContractDto();
    }



    @Override
public CarDtoListDto searchCarByName(SearchCarDto searchCarDto){
        Car car = new Car();
        car.setName(searchCarDto.getName());
    ExampleMatcher exampleMatcher =
            ExampleMatcher.matchingAll()
                    .withMatcher("name",ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
    Example<Car> carExample = Example.of(car,exampleMatcher);
    List<Car> carList = carRepository.findAll(carExample);
    CarDtoListDto carDtoListDto = new CarDtoListDto();
    carDtoListDto.setCarDtoList(carList.stream().map(Car::getCarDto).collect(Collectors.toList()));
    return carDtoListDto;
}


    public Map<String, Object> getCarBrandData() {
        List<Object[]> results = carRepository.getCarBrandCount();

        Map<String, Object> response = new HashMap<>();
        List<String> labels = new ArrayList<>();  // Nhãn là tên thương hiệu
        List<Long> values = new ArrayList<>();    // Số lượng xe theo thương hiệu

        for (Object[] result : results) {
            String brand = (String) result[0];  // Thương hiệu
            Long count = (Long) result[1];      // Số lượng xe của thương hiệu đó
            labels.add(brand);
            values.add(count);
        }

        response.put("labels", labels);
        response.put("values", values);

        return response;
    }
    @Override
    public List<CarDto> searchCarByName(String name) {

        List<CarDto> carDtos = carRepository.findCarByNameIgnoreCase(name).stream()
                .map(Car::getCarDto)
                .collect(Collectors.toList());

        return carDtos;
    }



    @Override
    public List<BookACarDto> searchById(Long id){
        List<BookACarDto> bookACarDtos = bookACarRepository.findById(id).stream().map(BookACar::getBookACarDto).collect(Collectors.toList());
        return bookACarDtos;
    }


}
