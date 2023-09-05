package com.avensys.rts.instructionservice.repository;

import com.avensys.rts.instructionservice.entity.InstructionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * author: Koh He Xiang
 * This is the repository class for the currency table in the database
 */
public interface InstructionRepository extends JpaRepository<InstructionEntity, Integer> {
    Optional<InstructionEntity> findByAccountId(int accountId);
}
