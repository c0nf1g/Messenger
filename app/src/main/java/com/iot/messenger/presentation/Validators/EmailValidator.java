package com.iot.messenger.presentation.Validators;

import android.text.TextUtils;
import android.util.Patterns;

public class EmailValidator implements ICredentialValidator {
    public boolean isValid(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
