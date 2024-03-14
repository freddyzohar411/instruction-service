package com.avensys.rts.instructionservice.service;

import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;

import com.avensys.rts.instructionservice.APIClient.FormSubmissionAPIClient;
import com.avensys.rts.instructionservice.APIClient.UserAPIClient;
import com.avensys.rts.instructionservice.entity.InstructionEntity;
import com.avensys.rts.instructionservice.repository.InstructionRepository;

@SpringBootTest
public class InstructionServiceImplTest {

	@Autowired
	InstructionService instructionService;

	@MockBean
	InstructionRepository instructionRepository;

	@Mock
	MessageSource messageSource;

	@Mock
	AutoCloseable autoCloseable;

	@Mock
	UserAPIClient userAPIClient;

	@Mock
	FormSubmissionAPIClient formSubmissionAPIClient;

	InstructionEntity instructionEntity;
	Optional<InstructionEntity> optionalInstruction;

	@BeforeEach
	void setUp() {
		autoCloseable = MockitoAnnotations.openMocks(this);
		instructionEntity = new InstructionEntity(1, "type", "guideLines", 1, "entityType", 1, 1, 1);
		instructionRepository.save(instructionEntity);
		optionalInstruction = Optional.of(instructionEntity);
	}

	@AfterEach
	void tearDown() throws Exception {
		autoCloseable.close();
	}

	@Test
	void testGetInstructionById() {
		when(instructionRepository.findById(1)).thenReturn(optionalInstruction);
	}

}
