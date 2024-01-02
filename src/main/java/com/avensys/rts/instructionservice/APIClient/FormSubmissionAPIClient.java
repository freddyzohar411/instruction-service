package com.avensys.rts.instructionservice.APIClient;

import com.avensys.rts.instructionservice.customresponse.HttpResponse;
import com.avensys.rts.instructionservice.interceptor.JwtTokenInterceptor;
import com.avensys.rts.instructionservice.payloadrequest.FormSubmissionsRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "form-service", url = "${api.form-submission.url}", configuration = JwtTokenInterceptor.class)
public interface FormSubmissionAPIClient {
    @PostMapping("/form-submissions")
    HttpResponse addFormSubmission(@RequestBody FormSubmissionsRequestDTO formSubmissionsRequestDTO);

    @GetMapping("/form-submissions/{formSubmissionId}")
    HttpResponse getFormSubmission(@PathVariable int formSubmissionId);

    @PutMapping("/form-submissions/{formSubmissionId}")
    HttpResponse updateFormSubmission(@PathVariable int formSubmissionId, @RequestBody FormSubmissionsRequestDTO formSubmissionsRequestDTO);

    @DeleteMapping("/form-submissions/{formSubmissionId}")
    HttpResponse deleteFormSubmission(@PathVariable int formSubmissionId);

}
