package com.avensys.rts.instructionservice.payloadrequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * author: Koh He Xiang This is the DTO class for a request to add a document
 */
@Setter
@Getter
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
