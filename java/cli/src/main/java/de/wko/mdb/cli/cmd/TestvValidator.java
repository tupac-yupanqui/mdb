package de.wko.mdb.cli.cmd;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TestvValidator implements ConstraintValidator<TESTV, String> {
    @Override
    public void initialize( TESTV constraintAnnotation ) { }

    @Override
    public boolean isValid( String value, ConstraintValidatorContext context )
    {
        System.out.println("VALIDATION");
        return value != null && value.matches( "abc" );
    }
}
