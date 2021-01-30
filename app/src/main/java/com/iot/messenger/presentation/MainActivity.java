package com.iot.messenger.presentation;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.iot.messenger.R;
import com.iot.messenger.presentation.fragments.ChatFragment;
import com.iot.messenger.presentation.fragments.SignInFragment;
import com.iot.messenger.presentation.fragments.SignUpFragment;
import com.iot.messenger.presentation.listeners.FragmentsListener;
import com.iot.messenger.presentation.sharedPreferences.SharedPrefs;

public class MainActivity extends AppCompatActivity implements FragmentsListener {
    private SignInFragment signInFragment;
    private SignUpFragment signUpFragment;
    private ChatFragment chatFragment;
    private final SharedPrefs sharedPrefs = new SharedPrefs();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPrefs.init(this);

        initFragments();
        ifLoggedInShowChat();
    }

    private void ifLoggedInShowChat() {
        if (isLogged()) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, chatFragment)
                    .commit();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, signInFragment)
                    .commit();
        }
    }

    private boolean isLogged() {
        String userEmail = sharedPrefs.getUser();
        return userEmail.length() != 0;
    }

    private void initFragments() {
        signInFragment = new SignInFragment();
        signUpFragment = new SignUpFragment();
        chatFragment = new ChatFragment();
    }

    @Override
    public void onSignUpClicked() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, signInFragment)
                .commit();
    }

    @Override
    public void onSignUpNavClicked() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, signUpFragment)
                .commit();
    }

    @Override
    public void onSignInClicked() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, chatFragment)
                .commit();
    }

    @Override
    public void onSignInNavClicked() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, signInFragment)
                .commit();
    }
}