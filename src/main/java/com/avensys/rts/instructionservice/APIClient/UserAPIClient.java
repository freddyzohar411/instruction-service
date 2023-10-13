package com.avensys.rts.instructionservice.APIClient;

import com.avensys.rts.instructionservice.customresponse.HttpResponse;
import com.avensys.rts.instructionservice.interceptor.JwtTokenInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service", url = "http://localhost:8090/api/user", configuration = JwtTokenInterceptor.class)
public interface UserAPIClient {

    @GetMapping("/{id}")
    HttpResponse getUserById(@PathVariable("id") Integer id);

    @GetMapping("")
    HttpResponse getUserByEmail(@RequestParam("email") String email);

}

