package com.avensys.rts.instructionservice.annotation;

import com.avensys.rts.instructionservice.validators.ValidPdfFileValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * author: Koh He Xiang
 * This is the annotation valid format for PDF file
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidPdfFileValidator.class)
public @interface ValidPdfFile {
    String message() default "Invalid file format. Only PDF files are allowed.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}