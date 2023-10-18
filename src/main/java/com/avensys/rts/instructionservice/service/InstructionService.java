package com.avensys.rts.instructionservice.service;

import com.avensys.rts.instructionservice.payloadrequest.InstructionRequestDTO;
import com.avensys.rts.instructionservice.payloadrequest.InstructionUpdateRequestDTO;
import com.avensys.rts.instructionservice.payloadresponse.InstructionResponseDTO;

/**
 * @author Koh He Xiang
 * This interface is used to define the methods for the Currency Service
 */
public interface InstructionService {

    /**
     * This method is used to save Instruction and create a Instruction
     * @param InstructionRequest
     * @return
     */
    InstructionResponseDTO createInstruction(InstructionRequestDTO InstructionRequest);

    /**
     *  This method is used to get an Instruction by id
     * @param instructionId
     * @return
     */
    InstructionResponseDTO getInstructionById(int instructionId);

    /**
     * This method is used to get an Instruction by account id
     * @param accountId
     * @return
     */
    InstructionResponseDTO getInstructionByAccountId(int accountId);

    /**
     * This method is used to update an Instruction by id
     * @param instructionId
     * @param InstructionRequest
     * @return
     */
    InstructionResponseDTO updateInstructionById(int instructionId, InstructionRequestDTO InstructionRequest);

    InstructionResponseDTO getInstructionByEntityTypeAndEntityId(String entityType, Integer entityId);

    void deleteInstructionByEntityTypeAndEntityId(String entityType, Integer entityId);

}
