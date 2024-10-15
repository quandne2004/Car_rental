package QuanDen.demo.services.auth;

import QuanDen.demo.dto.SignupRequest;
import QuanDen.demo.dto.UserDto;

public interface AuthService {

    UserDto createUser(SignupRequest signupRequest);

    boolean hasCustomerWithEmail(String email);
}
