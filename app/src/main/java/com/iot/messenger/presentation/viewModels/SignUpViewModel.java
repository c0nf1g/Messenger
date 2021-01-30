package com.iot.messenger.presentation.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.iot.messenger.presentation.DTO.ResponseDTO;
import com.iot.messenger.presentation.validators.EmailValidator;
import com.iot.messenger.presentation.validators.ICredentialValidator;
import com.iot.messenger.presentation.validators.PasswordValidator;

public class SignUpViewModel extends ViewModel {
    private final ICredentialValidator emailValidator = new EmailValidator();
    private final ICredentialValidator passwordValidator = new PasswordValidator();
    private final MutableLiveData<ResponseDTO> responseDTOMutableLiveData = new MutableLiveData<>();
    private final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private final ResponseDTO responseDTO = new ResponseDTO();

    public void signUp(String email, String password, String confirmPassword) {
        if (isValidParam(email, password, confirmPassword)) {
            firebaseSignUp(email, password);
        }
        responseDTOMutableLiveData.setValue(responseDTO);
    }

    private boolean isValidParam(String email, String password, String confirmPassword) {
        boolean isValidEmail = emailValidator.isValid(email);
        boolean isValidPassword = passwordValidator.isValid(password);
        boolean isValidConfirmPassword = passwordValidator.isValid(confirmPassword);

        responseDTO.setValidEmail(isValidEmail);
        responseDTO.setValidPassword(isValidPassword);
        responseDTO.setValidConfirmPassword(isValidConfirmPassword);
        responseDTO.setPasswordMatches(password.equals(confirmPassword));

        if (isValidEmail && isValidPassword && isValidConfirmPassword && password.equals(confirmPassword)) {
            return true;
        } else {
            responseDTO.setSignedUp(false);
            return false;
        }
    }

    private void firebaseSignUp(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    responseDTO.setSignedUp(task.isSuccessful());
                    if (!task.isSuccessful()) {
                        responseDTO.setFailedToCreateAccount(true);
                    }
                    responseDTOMutableLiveData.setValue(responseDTO);
                });
    }

    public MutableLiveData<ResponseDTO> getResponseDTOMutableLiveData() {
        return responseDTOMutableLiveData;
    }
}
