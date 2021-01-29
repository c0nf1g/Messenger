package com.iot.messenger.presentation.validators;

import android.util.Patterns;

public class EmailValidator implements ICredentialValidator {
    public boolean isValid(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
