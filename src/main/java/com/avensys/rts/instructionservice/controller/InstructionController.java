package com.avensys.rts.instructionservice.controller;

import com.avensys.rts.instructionservice.constant.MessageConstants;
import com.avensys.rts.instructionservice.payloadrequest.InstructionRequestDTO;
import com.avensys.rts.instructionservice.payloadrequest.InstructionUpdateRequestDTO;
import com.avensys.rts.instructionservice.payloadresponse.InstructionResponseDTO;
import com.avensys.rts.instructionservice.service.InstructionService;
import com.avensys.rts.instructionservice.util.ResponseUtil;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/***
 * @author Koh He Xiang
 * This class is used to define the endpoints for the Currency Controller
 */
@RestController
public class InstructionController {

    private final Logger log = LoggerFactory.getLogger(InstructionController.class);
    private final InstructionService instructionService;
    private final MessageSource messageSource;

    public InstructionController(InstructionService instructionService, MessageSource messageSource) {
        this.instructionService = instructionService;
        this.messageSource = messageSource;
    }

    /**
     * This method is used to create a instruction
     * @param instructionRequest
     * @return HttpResponse with instructionResponseDTO
     */
    @PostMapping(value = "/instructions")
    public ResponseEntity<Object> createInstruction(@Valid @RequestBody InstructionRequestDTO instructionRequest) {
        log.info("Instruction create: Controller");
        InstructionResponseDTO instructionResponse = instructionService.createInstruction(instructionRequest);
        return ResponseUtil.generateSuccessResponse(instructionResponse, HttpStatus.OK, messageSource.getMessage(MessageConstants.MESSAGE_SUCCESS, null, LocaleContextHolder.getLocale()));
    }

    /**
     * This endpoint is to get instruction by Id
     * @param instructionId
     * @return
     */
    @GetMapping(value = "/instructions/{instructionId}")
    public ResponseEntity<Object> getInstructionById(@PathVariable int instructionId) {
        log.info("Instruction get by id: Controller");
        InstructionResponseDTO instructionResponse = instructionService.getInstructionById(instructionId);
        return ResponseUtil.generateSuccessResponse(instructionResponse, HttpStatus.OK, messageSource.getMessage(MessageConstants.MESSAGE_SUCCESS, null, LocaleContextHolder.getLocale()));
    }

    /**
     * This endpoint is to get instruction by account Id
     * @param accountId
     * @return
     */
    @GetMapping(value = "/instructions")
    public ResponseEntity<Object> getInstructionByAccountId(@RequestParam int accountId) {
        log.info("Instruction get by account id: Controller");
        InstructionResponseDTO instructionResponse = instructionService.getInstructionByAccountId(accountId);
        return ResponseUtil.generateSuccessResponse(instructionResponse, HttpStatus.OK, messageSource.getMessage(MessageConstants.MESSAGE_SUCCESS, null, LocaleContextHolder.getLocale()));
    }

    /**
     * This endpoint is to update instruction by Id
     * @param instructionId
     * @param instructionUpdateRequest
     * @return
     */
    @PutMapping(value = "/instructions/{instructionId}")
    public ResponseEntity<Object> updateInstructionById(@PathVariable int instructionId, @Valid @RequestBody InstructionUpdateRequestDTO instructionUpdateRequest) {
        log.info("Instruction update by id: Controller");
        InstructionResponseDTO instructionResponse = instructionService.updateInstructionById(instructionId, instructionUpdateRequest);
        return ResponseUtil.generateSuccessResponse(instructionResponse, HttpStatus.OK, messageSource.getMessage(MessageConstants.MESSAGE_SUCCESS, null, LocaleContextHolder.getLocale()));
    }

}
