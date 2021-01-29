package com.iot.messenger.presentation.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;

public class SignInViewModel extends ViewModel {
    private final MutableLiveData<Boolean> isLoggedIn = new MutableLiveData<>();
    private final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    public void signIn(String email, String password) {
        //TODO: Password & email validation
        firebaseSignIn(email, password);
    }

    private void firebaseSignIn(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    isLoggedIn.setValue(task.isSuccessful());
                });
    }

    public MutableLiveData<Boolean> getIsLoggedIn() {
        return isLoggedIn;
    }
}
