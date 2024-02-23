package com.avensys.rts.instructionservice.payloadrequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentListRequestDTO {
	DocumentRequestDTO[] documentRequestList;
}
