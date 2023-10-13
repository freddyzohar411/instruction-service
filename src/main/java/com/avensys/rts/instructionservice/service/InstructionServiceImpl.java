package com.avensys.rts.instructionservice.service;

import com.avensys.rts.instructionservice.APIClient.DocumentAPIClient;
import com.avensys.rts.instructionservice.APIClient.FormSubmissionAPIClient;
import com.avensys.rts.instructionservice.customresponse.HttpResponse;
import com.avensys.rts.instructionservice.entity.InstructionEntity;
import com.avensys.rts.instructionservice.payloadrequest.FormSubmissionsRequestDTO;
import com.avensys.rts.instructionservice.payloadrequest.InstructionRequestDTO;
import com.avensys.rts.instructionservice.payloadrequest.InstructionUpdateRequestDTO;
import com.avensys.rts.instructionservice.payloadresponse.FormSubmissionsResponseDTO;
import com.avensys.rts.instructionservice.payloadresponse.InstructionResponseDTO;
import com.avensys.rts.instructionservice.repository.InstructionRepository;
import com.avensys.rts.instructionservice.util.MappingUtil;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Koh He Xiang
 * This class is used to implement the methods for the Currency Service
 */
@Service
public class InstructionServiceImpl implements InstructionService {

    private final Logger log = LoggerFactory.getLogger(InstructionServiceImpl.class);
    private final InstructionRepository instructionRepository;

    @Autowired
    private FormSubmissionAPIClient formSubmissionAPIClient;

    public InstructionServiceImpl(InstructionRepository instructionRepository) {
        this.instructionRepository = instructionRepository;
    }

    /**
     * This method is used to save Instruction and create a Instruction
     *
     * @param instructionRequest
     * @return
     */
    @Override
    public InstructionResponseDTO createInstruction(InstructionRequestDTO instructionRequest) {
        log.info("Instruction create: Service");
        InstructionEntity instructionSaved = instructionRepository.save(toInstructionEntity(instructionRequest));

        // Set formdata in form submission microservice
        FormSubmissionsRequestDTO formSubmissionsRequestDTO = instructionRequestDTOTFormSubmissionRequestDTO(instructionRequest);
        HttpResponse formSubmissionResponse = formSubmissionAPIClient.addFormSubmission(formSubmissionsRequestDTO);
        FormSubmissionsResponseDTO formSubmissionData = MappingUtil.mapClientBodyToClass(formSubmissionResponse.getData(), FormSubmissionsResponseDTO.class);
        instructionSaved.setFormSubmissionId(formSubmissionData.getId());

        instructionSaved = instructionRepository.save(instructionSaved);

        return toInstructionResponseDTO(instructionSaved);
    }

    /**
     * This method is used to get an Instruction by id
     *
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
     *
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
     *
     * @param instructionId
     * @param InstructionRequest
     * @return
     */
    @Override
    public InstructionResponseDTO updateInstructionById(int instructionId, InstructionRequestDTO InstructionRequest) {
        InstructionEntity instructionEntity = instructionRepository.findById(instructionId).orElseThrow(
                () -> new EntityNotFoundException("Instruction not found with id: " + instructionId)
        );
        InstructionEntity updatedInstructionEntity = toUpdateInstructionEntity(instructionEntity, InstructionRequest);

        // Update formdata in form submission microservice
        FormSubmissionsRequestDTO formSubmissionsRequestDTO = instructionRequestDTOTFormSubmissionRequestDTO(InstructionRequest);
        HttpResponse formSubmissionResponse = formSubmissionAPIClient.updateFormSubmission(updatedInstructionEntity.getFormSubmissionId(), formSubmissionsRequestDTO);
        FormSubmissionsResponseDTO formSubmissionData = MappingUtil.mapClientBodyToClass(formSubmissionResponse.getData(), FormSubmissionsResponseDTO.class);
        updatedInstructionEntity.setFormSubmissionId(formSubmissionData.getId());

        InstructionEntity instructionSaved = instructionRepository.save(instructionEntity);
        return toInstructionResponseDTO(instructionSaved);
    }

    @Override
    public InstructionResponseDTO getInstructionByEntityTypeAndEntityId(String entityType, Integer entityId) {
        Optional<InstructionEntity> instructionEntity = instructionRepository.findByEntityTypeAndEntityId(entityType, entityId);
        if (instructionEntity.isEmpty()) {
            return null;
        } else {
            return toInstructionResponseDTO(instructionEntity.get());
        }
    }


    /**
     * Internal method used to convert DocumentRequestDTO to DocumentEntity
     *
     * @param instructionRequest
     * @return
     */
    private InstructionEntity toInstructionEntity(InstructionRequestDTO instructionRequest) {
        InstructionEntity instructionEntity = new InstructionEntity();
        instructionEntity.setEntityId(instructionRequest.getEntityId());
        instructionEntity.setEntityType(instructionRequest.getEntityType());
        instructionEntity.setGuideLines(instructionRequest.getGuidelines());
        instructionEntity.setFormId(instructionRequest.getFormId());
        return instructionEntity;
    }

    /**
     * Internal method used to update instructionRequestDTO to instructionEntity
     * @param instructionEntity
     * @param instructionRequest
     * @return
     */
    private InstructionEntity toUpdateInstructionEntity(InstructionEntity instructionEntity, InstructionRequestDTO instructionRequest) {
        instructionEntity.setEntityId(instructionRequest.getEntityId());
        instructionEntity.setEntityType(instructionRequest.getEntityType());
        instructionEntity.setGuideLines(instructionRequest.getGuidelines());
        instructionEntity.setFormId(instructionRequest.getFormId());
        return instructionEntity;
    }

    /**
     * Internal method used to convert DocumentEntity to DocumentResponseDTO
     *
     * @param instructionEntity
     * @return
     */
    private InstructionResponseDTO toInstructionResponseDTO(InstructionEntity instructionEntity) {
        InstructionResponseDTO instructionResponseDTO = new InstructionResponseDTO();
        instructionResponseDTO.setId(instructionEntity.getId());
        instructionResponseDTO.setGuidelines(instructionEntity.getGuideLines());
        instructionResponseDTO.setFormId(instructionEntity.getFormId());
        // Get form data
        if (instructionEntity.getFormSubmissionId() != null){
            HttpResponse formSubmissionResponse = formSubmissionAPIClient.getFormSubmission(instructionEntity.getFormSubmissionId());
            FormSubmissionsResponseDTO formSubmissionData = MappingUtil.mapClientBodyToClass(formSubmissionResponse.getData(), FormSubmissionsResponseDTO.class);
            instructionResponseDTO.setSubmissionData(formSubmissionData.getSubmissionData());
        }
        return instructionResponseDTO;
    }



    /**
     * Internal Method to formsubmissionRequest
     */
    private FormSubmissionsRequestDTO instructionRequestDTOTFormSubmissionRequestDTO(InstructionRequestDTO instructionRequestDTO) {
        FormSubmissionsRequestDTO formSubmissionsRequestDTO = new FormSubmissionsRequestDTO();
        formSubmissionsRequestDTO.setSubmissionData(instructionRequestDTO.getFormData());
        formSubmissionsRequestDTO.setFormId(instructionRequestDTO.getFormId());
        formSubmissionsRequestDTO.setEntityId(instructionRequestDTO.getEntityId());
        formSubmissionsRequestDTO.setEntityType(instructionRequestDTO.getEntityType());
        return formSubmissionsRequestDTO;
    }

}
