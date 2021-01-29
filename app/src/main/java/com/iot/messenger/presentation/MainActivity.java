package com.iot.messenger.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.iot.messenger.R;
import com.iot.messenger.presentation.Fragments.ChatFragment;
import com.iot.messenger.presentation.Fragments.SignInFragment;
import com.iot.messenger.presentation.Fragments.SignUpFragment;
import com.iot.messenger.presentation.Listeners.FragmentsListener;

public class MainActivity extends AppCompatActivity implements FragmentsListener {
    private SignInFragment signInFragment;
    private SignUpFragment signUpFragment;
    private ChatFragment chatFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        return false;
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