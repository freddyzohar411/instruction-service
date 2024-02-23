package com.avensys.rts.instructionservice.payloadresponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * author: Koh He Xiang This is the DTO class for a response for a retrieved
 * document
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InstructionResponseDTO {
	private Integer id;
	private Integer accountId;
	private String guidelines;

	private Integer formId;
	private String submissionData;
	private Integer formSubmissionId;
}
