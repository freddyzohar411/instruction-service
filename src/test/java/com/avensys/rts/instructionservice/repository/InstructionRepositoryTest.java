package com.avensys.rts.instructionservice.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.avensys.rts.instructionservice.entity.InstructionEntity;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class InstructionRepositoryTest {

	@Autowired
	InstructionRepository instructionRepository;

	InstructionEntity instructionEntity;
	Optional<InstructionEntity> optionalInstruction;

	@BeforeEach
	void setUp() {
		instructionEntity = new InstructionEntity(1, "type", "guideLines", 1, "entityType", 1, 1, 1);
		instructionRepository.save(instructionEntity);
	}

	@AfterEach
	void tearDown() throws Exception {
		instructionRepository.deleteAll();
		instructionEntity = null;
	}

	@Test
	void testFindByAccountId() {
		optionalInstruction = instructionRepository.findByAccountId(1);
		assertNotNull(optionalInstruction);
		assertThat(optionalInstruction.get().getEntityType()).isEqualTo("entityType");

	}

	@Test
	void testFindByEntityTypeAndEntityId() {
		optionalInstruction = instructionRepository.findByEntityTypeAndEntityId("entityType", 1);
		assertNotNull(optionalInstruction);
		assertThat(optionalInstruction.get().getType()).isEqualTo("type");
	}

}
