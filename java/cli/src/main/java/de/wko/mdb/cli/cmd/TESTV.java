package de.wko.mdb.cli.cmd;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;
import java.lang.annotation.ElementType;

@Constraint( validatedBy = TestvValidator.class )
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER })
@Retention( RetentionPolicy.RUNTIME )
@Documented
public @interface TESTV {
    String message() default "ist keine gültige E-Mail-Adresse";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
