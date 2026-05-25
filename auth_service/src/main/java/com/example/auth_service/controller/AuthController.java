 package com.example.auth_service.controller;


import  com.example.auth_service.entities.RefreshToken;
import com.example.auth_service.model.UserInfoDto;
import com.example.auth_service.response.JwtResponseDTO;
import com.example.auth_service.service.JwtService;
import com.example.auth_service.service.RefreshTokenService;
import com.example.auth_service.service.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class   AuthController
{

    @Autowired
    private JwtService jwtService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;




    @PostMapping("auth/v1/signup")
    public ResponseEntity<?> SignUp(@RequestBody UserInfoDto userInfoDto) {

        // 3. Removed the dangerous 'this.userInfoDto = userInfoDto;' line.
        // We only use the local 'userInfoDto' parameter provided by the @RequestBody.

        try {
            Boolean isSignUped = userDetailsService.signupUser(userInfoDto);
            if (Boolean.FALSE.equals(isSignUped)) {
                return new ResponseEntity<>("Already Exist", HttpStatus.BAD_REQUEST);
            }

            RefreshToken refreshToken = refreshTokenService.createRefreshToken(userInfoDto.getUsername());
            String jwtToken = jwtService.GenerateToken(userInfoDto.getUsername());

            return new ResponseEntity<>(JwtResponseDTO.builder()
                    .accessToken(jwtToken)
                    .token(refreshToken.getToken())
                    .build(), HttpStatus.OK);

        } catch (Exception ex) {
            // Note: In the future, log 'ex.getMessage()' here so you can see exact errors in your console!
            return new ResponseEntity<>("Exception in User Service", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
