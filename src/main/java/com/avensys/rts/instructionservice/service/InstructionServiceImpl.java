package com.avensys.rts.instructionservice.service;

import com.avensys.rts.instructionservice.APIClient.DocumentAPIClient;
import com.avensys.rts.instructionservice.entity.InstructionEntity;
import com.avensys.rts.instructionservice.payloadrequest.InstructionRequestDTO;
import com.avensys.rts.instructionservice.payloadrequest.InstructionUpdateRequestDTO;
import com.avensys.rts.instructionservice.payloadresponse.InstructionResponseDTO;
import com.avensys.rts.instructionservice.repository.InstructionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Koh He Xiang
 * This class is used to implement the methods for the Currency Service
 */
@Service
public class InstructionServiceImpl implements InstructionService {

    private final Logger log = LoggerFactory.getLogger(InstructionServiceImpl.class);
    private final InstructionRepository instructionRepository;

//    @Autowired
//    private DocumentAPIClient documentAPIClient;

    public InstructionServiceImpl(InstructionRepository instructionRepository) {
        this.instructionRepository = instructionRepository;
    }

    /**
     * This method is used to save Instruction and create a Instruction
     * @param instructionRequest
     * @return
     */
    @Override
    public InstructionResponseDTO createInstruction(InstructionRequestDTO instructionRequest) {
        log.info("Instruction create: Service");
        InstructionEntity instructionEntity = toInstructionEntity(instructionRequest);
        InstructionEntity instructionSaved = instructionRepository.save(instructionEntity);

//        // Generate list of InstuctionDTO to save based on multiple files uploaded
//        if (instructionRequest.getFiles() != null) {
//            instructionRequest.getFiles().forEach((file -> {
//                DocumentRequestDTO documentRequestDTO = new DocumentRequestDTO();
//                documentRequestDTO.setEntityId(instructionSaved.getId());
//                documentRequestDTO.setEntityType("Instruction");
//                documentRequestDTO.setFile(file);
//                documentAPIClient.createDocument(documentRequestDTO);
//            }));
//        }
//        DocumentResponseDTO documentData = mapClientBodyToClass(documentResponse.getData(), DocumentResponseDTO.class);
        return toInstructionResponseDTO(instructionSaved);
    }

    /**
     * This method is used to get an Instruction by id
     * @param instructionId
     * @return
     */
    @Override
    public InstructionResponseDTO getInstructionById(int instructionId) {
        InstructionEntity instructionEntity = instructionRepository.findById(instructionId).orElseThrow(
                () -> new EntityNotFoundException("Instruction not found with id: " + instructionId)
        );
        return toInstructionResponseDTO(instructionEntity);
    }

    /**
     * This method is used to get an Instruction by account id
     * @param accountId
     * @return
     */
    @Override
    public InstructionResponseDTO getInstructionByAccountId(int accountId) {
        InstructionEntity instructionEntity = instructionRepository.findByAccountId(accountId).orElseThrow(
                () -> new EntityNotFoundException("Instruction not found with account id: " + accountId)
        );
        return toInstructionResponseDTO(instructionEntity);
    }

    /**
     * This method is used to update an Instruction by id
     * @param instructionId
     * @param InstructionRequest
     * @return
     */
    @Override
    public InstructionResponseDTO updateInstructionById(int instructionId, InstructionUpdateRequestDTO InstructionRequest) {
        InstructionEntity instructionEntity = instructionRepository.findById(instructionId).orElseThrow(
                () -> new EntityNotFoundException("Instruction not found with id: " + instructionId)
        );
        instructionEntity.setGuideLines(InstructionRequest.getGuidelines());
        InstructionEntity instructionSaved = instructionRepository.save(instructionEntity);
        return toInstructionResponseDTO(instructionSaved);
    }


    /**
     * Internal method used to convert DocumentRequestDTO to DocumentEntity
     * @param instructionRequest
     * @return
     */
    private InstructionEntity toInstructionEntity(InstructionRequestDTO instructionRequest) {
        InstructionEntity instructionEntity = new InstructionEntity();
//        documentEntity.setType(instructionRequest.getType());
        instructionEntity.setAccountId(instructionRequest.getAccountId());
        instructionEntity.setGuideLines(instructionRequest.getGuidelines());
        return instructionEntity;
    }

    /**
     * Internal method used to convert DocumentEntity to DocumentResponseDTO
     * @param instructionEntity
     * @return
     */
    private InstructionResponseDTO toInstructionResponseDTO(InstructionEntity instructionEntity) {
        InstructionResponseDTO instructionResponseDTO = new InstructionResponseDTO();
        instructionResponseDTO.setId(instructionEntity.getId());
        instructionResponseDTO.setGuidelines(instructionEntity.getGuideLines());
        return instructionResponseDTO;
    }

}
