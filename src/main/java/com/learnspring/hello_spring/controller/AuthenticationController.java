package com.learnspring.hello_spring.controller;

import com.learnspring.hello_spring.dto.request.ApiResponse;
import com.learnspring.hello_spring.dto.request.AuthenticationRequest;
import com.learnspring.hello_spring.dto.request.IntrospectRequest;
import com.learnspring.hello_spring.dto.response.AuthenticationResponse;
import com.learnspring.hello_spring.dto.response.IntrospectResponse;
import com.learnspring.hello_spring.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/token")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        var result = authenticationService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        IntrospectResponse result = authenticationService.introspect(request);
        return ApiResponse
                .<IntrospectResponse>builder()
                .result(result)
                .build();
    }
}
