package com.avensys.rts.instructionservice.payloadrequest;

//import com.avensys.rts.documentservice.annotation.FileSize;

import com.avensys.rts.instructionservice.annotation.FileSize;
import com.avensys.rts.instructionservice.annotation.ValidPdfFile;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * author: Koh He Xiang
 * This is the DTO class for a request to add a document
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InstructionRequestDTO {
    private Integer id;
    private String guidelines;
    private Integer accountId;
    private Integer entityId;
    private String entityType;

    private Integer formId;
    private String formData;
}
