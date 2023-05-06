package ir.tehranluster.paano.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import ir.tehranluster.paano.dto.user.UserDto;
import ir.tehranluster.paano.exceptions.EntityNotFoundException;
import ir.tehranluster.paano.security.JWTLoginSucessReponse;
import ir.tehranluster.paano.security.JwtTokenProvider;
import ir.tehranluster.paano.service.MapValidationErrorService;
import ir.tehranluster.paano.service.UserService;
import ir.tehranluster.paano.service.UserValidator;
import ir.tehranluster.paano.dto.response.ApiSuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserValidator userValidator;

    private final MapValidationErrorService mapValidationErrorService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;


    @GetMapping("/findbyid/{id}")
    public UserDto findById(@PathVariable Long id) throws EntityNotFoundException {
            return userService.findUserById(id);
        }


    @PostMapping("/register")
    @Operation(summary = "add api with condition" , operationId = "addUser" , description = "notice that this has condition")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" , description = "ok hast"),
            @ApiResponse(responseCode = "400" , description = "not found")
    })
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto, BindingResult result) {
        userValidator.validate(userDto,result);
        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationService(result);
        if(errorMap!=null)
            return errorMap;
        userService.save(userDto);
        return new ResponseEntity<ApiSuccessResponse>(new ApiSuccessResponse(200,HttpStatus.CREATED), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody UserDto userDto, BindingResult result){
       System.out.println("SUCCEEEEEEEEEEEEEESSSSSSS");
        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationService(result);
        if(errorMap != null) return errorMap;

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDto.getUsername(),
                        userDto.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = "Bearer " +  tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JWTLoginSucessReponse(true, jwt));
    }

}
