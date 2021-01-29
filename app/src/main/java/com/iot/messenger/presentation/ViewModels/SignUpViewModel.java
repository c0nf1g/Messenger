package com.iot.messenger.presentation.ViewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;

public class SignUpViewModel extends ViewModel {
    private final MutableLiveData<Boolean> isSignedUp = new MutableLiveData<>();
    private final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    public void signUp(String email, String password, String confirmPassword) {
        //TODO: Password & email validation
        firebaseSignUp(email, password);
    }

    private void firebaseSignUp(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    isSignedUp.setValue(task.isSuccessful());
                });
    }

    public MutableLiveData<Boolean> getIsSignedUp() {
        return isSignedUp;
    }
}
