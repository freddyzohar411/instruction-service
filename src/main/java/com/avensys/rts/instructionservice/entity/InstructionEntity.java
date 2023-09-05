package com.avensys.rts.instructionservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/***
 * @author Koh He Xiang
 * This is the entity class for the currency table in the database
 */
@Entity
@Table(name = "instruction")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstructionEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "type", length = 250 )
    private String type;

    @Column(name = "guidelines", length = 250 )
    private String guideLines;

    @Column(name = "account_id")
    private Integer accountId;

    @Column(name = "entity_id")
    private Integer entityId;

}
