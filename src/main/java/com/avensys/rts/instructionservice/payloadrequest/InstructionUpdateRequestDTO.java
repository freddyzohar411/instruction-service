package com.avensys.rts.instructionservice.payloadrequest;

//import com.avensys.rts.documentservice.annotation.FileSize;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * author: Koh He Xiang
 * This is the DTO class for a request to add a document
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InstructionUpdateRequestDTO {
    private Integer id;
    private String guidelines;
    private Integer accountId;
}
