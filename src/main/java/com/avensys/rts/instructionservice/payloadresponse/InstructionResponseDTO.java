package com.avensys.rts.instructionservice.payloadresponse;

import com.avensys.rts.instructionservice.annotation.FileSize;
import com.avensys.rts.instructionservice.annotation.ValidPdfFile;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/**
 * author: Koh He Xiang
 * This is the DTO class for a response for a retrieved document
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstructionResponseDTO {
    private Integer id;
//    private String type;
    private String guidelines;
}
