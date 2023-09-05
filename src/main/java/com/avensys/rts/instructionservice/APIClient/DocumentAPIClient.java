package com.avensys.rts.instructionservice.APIClient;

import com.avensys.rts.instructionservice.customresponse.HttpResponse;
import com.avensys.rts.instructionservice.payloadrequest.DocumentListRequestDTO;
import com.avensys.rts.instructionservice.payloadrequest.DocumentRequestDTO;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;


/**
 * author Koh He Xiang
 * This class is an interface to interact with document microservice
 */

@FeignClient(name = "document-service", url = "http://localhost:8500")
public interface DocumentAPIClient {
    @PostMapping(value = "/documents", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    HttpResponse createDocument(@ModelAttribute DocumentRequestDTO documentRequest);

    @PostMapping(value = "/documentsList" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    HttpResponse createDocumentList(@ModelAttribute DocumentListRequestDTO documentRequestList);
}
