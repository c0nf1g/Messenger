package com.iot.messenger.presentation.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.iot.messenger.presentation.DTO.ResponseDTO;
import com.iot.messenger.presentation.validators.EmailValidator;
import com.iot.messenger.presentation.validators.ICredentialValidator;
import com.iot.messenger.presentation.validators.PasswordValidator;

public class SignInViewModel extends ViewModel {
    private final ICredentialValidator emailValidator = new EmailValidator();
    private final ICredentialValidator passwordValidator = new PasswordValidator();
    private final MutableLiveData<ResponseDTO> responseDTOMutableLiveData = new MutableLiveData<>();
    private final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private final ResponseDTO responseDTO = new ResponseDTO();

    public void signIn(String email, String password) {
        if (isValidParam(email, password)) {
            firebaseSignIn(email, password);
        }
        responseDTOMutableLiveData.setValue(responseDTO);
    }

    private boolean isValidParam(String email, String password) {
        boolean isValidEmail = emailValidator.isValid(email);
        boolean isValidPassword = passwordValidator.isValid(password);

        if (isValidEmail && isValidPassword) {
            return true;
        } else {
            responseDTO.setValidEmail(isValidEmail);
            responseDTO.setValidPassword(isValidPassword);
            responseDTO.setSignedUp(false);
            return false;
        }
    }

    private void firebaseSignIn(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    responseDTO.setSignedIn(task.isSuccessful());
                    if (!task.isSuccessful()) {
                        responseDTO.setWrongEmailOrPassword(true);
                    }
                    responseDTOMutableLiveData.setValue(responseDTO);
                });
    }

    public MutableLiveData<ResponseDTO> getResponseDTOMutableLiveData() {
        return responseDTOMutableLiveData;
    }
}
