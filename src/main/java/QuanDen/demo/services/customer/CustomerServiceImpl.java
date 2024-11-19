package QuanDen.demo.services.customer;

import QuanDen.demo.dto.*;
import QuanDen.demo.entity.*;
import QuanDen.demo.enums.*;
import QuanDen.demo.repository.*;
import QuanDen.demo.services.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CarRepository carRepository;
    private final UserRepository userRepository;
    private final BookACarRepository bookACarRepository;
    private final CommentRepository commentRepository;
    private final CarFixRepository carFixRepository;
    private final PaymentCarFixRepository paymentCarFixRepository;
    private final RentalCarRepository rentalCarRepository;
    private final AdminService adminService;


    @Override
    public List<CarDto> getAllCars() {
        return carRepository.findAll().stream().map(Car::getCarDto).collect(Collectors.toList());
    }

    @Override
    public boolean bookACar(Long carId, BookACarDto bookACarDto) {
        Optional<Car> optionalCar = carRepository.findById(carId);
        Optional<User> optionalUser = userRepository.findById(bookACarDto.getUserId());

        if (optionalCar.isPresent() && optionalUser.isPresent()) {
            Car existingCar = optionalCar.get();

            // Check if the car is already booked
            if (existingCar.getCarStatus() == CarStatus.BOOKED) {
                System.out.println("The Car has already been booked!");
                return false; // Return false if the car is already booked
            }

            // Update car status to BOOKED
            existingCar.setCarStatus(CarStatus.BOOKED);
            carRepository.save(existingCar); // Save the updated car status

            // Create a new booking
            BookACar bookACar = new BookACar();
            bookACar.setUser(optionalUser.get());
            bookACar.setCar(existingCar);
            bookACar.setBookCarStatus(BookCarStatus.PENDING);

            long diffInMilliSeconds = bookACarDto.getToDate().getTime() - bookACarDto.getFromDate().getTime();
            long days = TimeUnit.MILLISECONDS.toDays(diffInMilliSeconds);  // Calculate days
            bookACar.setFromDate(bookACarDto.getFromDate());
            bookACar.setToDate(bookACarDto.getToDate());
            bookACar.setPayment(bookACarDto.getPayment());
            bookACar.setDays(days);
            bookACar.setPrice(existingCar.getPrice() * days);
            BookACar bookACar1 = bookACarRepository.save(bookACar);

            // Create and post rental contract
            RentalContractDto rentalContractDto = new RentalContractDto();
            rentalContractDto.setBookACarId(bookACar1.getId());
            rentalContractDto.setMaintenanceTerms("Rent for " + days + " days.");
            rentalContractDto.setRentalContractStatus(RentalContractStatus.ACCEPT);
            rentalContractDto.setTerminationTerms("Ends in " + days + " days.");
            rentalContractDto.setUsageTerms("Vehicles may only be used for personal and legal purposes. Do not use the vehicle for racing activities, overloading, or any commercial purposes.");
            adminService.postContractDto(rentalContractDto);

            return true; // Return true to indicate the booking was successful
        }

        return false; // Return false if the car or user is not found
    }



    @Override
    public boolean bookACar(BookACarDto bookACarDto) {
        return false;
    }


    @Override
    public CarDto getCarById(Long carId) {
        Optional<Car> optionalCar = carRepository.findById(carId);
        return optionalCar.map(Car::getCarDto).orElse(null);

    }

    @Override
    public List<BookACarDto> getBookingsByUserId(Long userId) {
        return bookACarRepository.findAllByUserId(userId).stream().map(BookACar::getBookACarDto).collect(Collectors.toList());
    }

    @Override
    public CarDtoListDto searchCar(SearchCarDto searchCarDto) {
        Car car = new Car();
        car.setBrand(searchCarDto.getBrand());
        car.setType(searchCarDto.getType());
        car.setTransmission(searchCarDto.getTransmission());
        searchCarDto.setColor(searchCarDto.getColor());
        ExampleMatcher exampleMatcher =
                ExampleMatcher.matchingAll()
                        .withMatcher("brand",ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                        .withMatcher("type",ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                        .withMatcher("transmission",ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                        .withMatcher("color",ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                        .withMatcher("numberSeat",ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
        Example<Car> carExample = Example.of(car,exampleMatcher);
        List<Car> carList = carRepository.findAll(carExample);
        CarDtoListDto carDtoListDto = new CarDtoListDto();
        carDtoListDto.setCarDtoList(carList.stream().map(Car::getCarDto).collect(Collectors.toList()));
        return carDtoListDto;
    }







    public BookACarDto getBookingById(Long bookACarId){
        Optional<BookACar> optionalBookACar = bookACarRepository.findById(bookACarId);
        return optionalBookACar.map(BookACar::getBookACarDto).orElse(null);
    }

        @Override
        public CommentDto postComment(CommentDto commentDto, Long carId) {
            Optional<User> optionalUser = userRepository.findById(commentDto.getUserId());
            Optional<Car> optionalCar = carRepository.findById(carId);
            if (optionalCar.isPresent() && optionalUser.isPresent()){
                Comment comment  =new Comment();
                comment.setBody(commentDto.getBody());
                comment.setRating(commentDto.getRating());
                comment.setUser(optionalUser.get());
                comment.setCar(optionalCar.get());
                comment.setCreatedDate(new Date());
                comment.setCommentStatus(CommentStatus.PENDING);
                Comment comment1 = commentRepository.save(comment);
                CommentDto commentDto1 = new CommentDto();
                commentDto1.setId(comment1.getId());
                return commentDto1;
            }
            return null;
        }

    @Override
    public List<CommentDto> getCommentByUserId(Long userId) {
        return commentRepository.findCommentByUserId(userId).stream().map(Comment::getCommentDto).collect(Collectors.toList());
    }

    @Override
    public CarFixDto postCarFix( Long userId,CarFixDto carFixDto) throws IOException {
        Optional<Car> optionalCar = carRepository.findById(carFixDto.getCarId());
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalCar.isPresent() && optionalUser.isPresent()){
            CarFix carFix = new CarFix();
            carFix.setDescription(carFixDto.getDescription());
            carFix.setImage(carFixDto.getImage().getBytes());
            carFix.setCar(optionalCar.get());
            carFix.setCarFixStatus(CarFixStatus.FIXED);
            carFix.setUser(optionalUser.get());
            CarFix carFix1c = carFixRepository.save(carFix);
            CarFixDto carFixDto1 = new CarFixDto();
            carFixDto1.setId(carFix1c.getId());
            return carFixDto1;
        }
        return null;
    }

    @Override
    public List<CarFixDto> getAllCarFix() {
        return carFixRepository.findAll().stream().map(CarFix::getCarFixDto).collect(Collectors.toList());
    }

    @Override
    public List<PaymentCarFixDto> getAllPaymentByUserId(Long userId) {
        return paymentCarFixRepository.findAllByUserId(userId).stream().map(PaymentCarFix:: getPaymentCarFixDto).collect(Collectors.toList());
    }

    @Override
    public CarFixDto getCarFixById(Long carFixId) {
        Optional<CarFix> optionalCarFix = carFixRepository.findById(carFixId);
        return optionalCarFix.map(CarFix::getCarFixDto).orElse(null);
    }



    @Override
    public RentalContractDto getRentalContractById(Long id){
        Optional<RentalContract> optional = rentalCarRepository.findById(id);
        if (optional.isEmpty()) {
            System.out.println("Không tìm thấy hợp đồng với ID: " + id);
        } else {
            System.out.println("Tìm thấy hợp đồng: " + optional.get());
        }
        return optional.map(RentalContract::getRentalContractDto).orElse(null);
    }



    @Override
    public boolean changeRentalContractStatus(Long rentalContractId,String status){
        Optional<RentalContract> optional = rentalCarRepository.findById(rentalContractId);
        if (optional.isPresent()){
            RentalContract rentalContract = optional.get();
            BookACar booking = rentalContract.getBookACar(); // Assuming there's a relation

            if (Objects.equals(status,"Accept")){
                rentalContract.setRentalContractStatus(RentalContractStatus.ACCEPT);
            }else {
                rentalContract.setRentalContractStatus(RentalContractStatus.REJECT);
                if (booking != null) {
                    booking.setBookCarStatus(BookCarStatus.REJECTED); // Update to CANCELED status
                    bookACarRepository.save(booking); // Save the updated booking
                }
                rentalCarRepository.save(rentalContract);
            }
            return true;
        }
        return false;
    }



    @Override
    public List<RentalContractDto> getAllRentalContract(){
        return rentalCarRepository.findAll().stream().map(RentalContract::getRentalContractDto).collect(Collectors.toList());
    }





    @Override
    public RentalContractDto updateRentalContract(Long rentalContractId, RentalContractDto rentalContractDto) {
        // Lấy hợp đồng từ database
        RentalContract rentalContract = rentalCarRepository.findById(rentalContractId)
                .orElseThrow(() -> new RuntimeException("Rental contract not found"));

        // Lấy thông tin từ BookACar
        BookACar bookACar = rentalContract.getBookACar();

        // Cập nhật các trường trong BookACar
        if (rentalContractDto.getPaymentMethod() != null) {
            bookACar.setPayment(rentalContractDto.getPaymentMethod());
        }
        if (rentalContractDto.getBookACarfromDate() != null) {
            bookACar.setFromDate(rentalContractDto.getBookACarfromDate());
        }
        if (rentalContractDto.getBookACartoDate() != null) {
            bookACar.setToDate(rentalContractDto.getBookACartoDate());
        }

        // Lưu lại BookACar (dữ liệu sẽ được lưu vào bảng BookACar)
        bookACarRepository.save(bookACar);

        // Cập nhật lại RentalContractDto với các thông tin từ BookACar
        rentalContractDto.setPaymentMethod(bookACar.getPayment());
        rentalContractDto.setBookACarfromDate(bookACar.getFromDate());
        rentalContractDto.setBookACartoDate(bookACar.getToDate());

        // Các trường không thay đổi trong RentalContract
        rentalContractDto.setMaintenanceTerms(rentalContract.getMaintenanceTerms());
        rentalContractDto.setUsageTerms(rentalContract.getUsageTerms());
        rentalContractDto.setTerminationTerms(rentalContract.getTerminationTerms());
        rentalContractDto.setRentalContractStatus(rentalContract.getRentalContractStatus());

        // Thông tin về xe và người dùng
        rentalContractDto.setCarName(bookACar.getCar().getName());
        rentalContractDto.setCarColor(bookACar.getCar().getColor());
        rentalContractDto.setCarYear(bookACar.getCar().getYear());
        rentalContractDto.setUserName(bookACar.getUser().getName());
        rentalContractDto.setUserEmail(bookACar.getUser().getEmail());

        // Các trường cần hiển thị từ BookACar
        rentalContractDto.setBookACarDays(bookACar.getDays());
        rentalContractDto.setBookACarId(bookACar.getId());
        rentalContractDto.setBookACarPrice(bookACar.getPrice());

        return rentalContractDto;
    }





    @Override
    public List<CarDto> searchCarByName(String name) {

        List<CarDto> carDtos = carRepository.findCarByNameStartsWithIgnoreCase(name).stream()
                .map(Car::getCarDto)
                .collect(Collectors.toList());

        return carDtos;
    }


    @Override
    // Cập nhật BookACar
    public boolean updateBookCar(Long bookACarId, BookACarDto bookACarDto) {
        Optional<BookACar> bookACarOptional = bookACarRepository.findById(bookACarId);
        if (bookACarOptional.isPresent()) {
            BookACar bookACar = bookACarOptional.get();

            // Cập nhật các trường của BookACar
            if (bookACarDto.getPayment() != null) {
                bookACar.setPayment(bookACarDto.getPayment());
            }
            if (bookACarDto.getFromDate() != null) {
                bookACar.setFromDate(bookACarDto.getFromDate());
            }
            if (bookACarDto.getToDate() != null) {
                bookACar.setToDate(bookACarDto.getToDate());
            }

            // Tính toán số ngày và giá tiền mới
            long diffInMilliSeconds = bookACar.getToDate().getTime() - bookACar.getFromDate().getTime();
            long days = TimeUnit.MILLISECONDS.toDays(diffInMilliSeconds);
            bookACar.setDays(days);
            bookACar.setPrice(bookACar.getCar().getPrice() * days);

            // Cập nhật RentalContract tương ứng
            Optional<RentalContract> rentalContractOptional = rentalCarRepository.findByBookACarId(bookACarId);
            if (rentalContractOptional.isPresent()) {
                RentalContract rentalContract = rentalContractOptional.get();
                rentalContract.setMaintenanceTerms("Rent in " + days + " days");
                rentalContract.setTerminationTerms("End in " + days);
                rentalContract.setUsageTerms("Vehicles may only be used for personal and legal purposes.");
                rentalContract.setRentalContractStatus(RentalContractStatus.ACCEPT);

                // Lưu RentalContract
                rentalCarRepository.save(rentalContract);
            }

            // Lưu lại BookACar
            bookACarRepository.save(bookACar);
            return true;
        }
        return false;
    }




}
