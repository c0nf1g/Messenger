package com.iot.messenger.presentation.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.iot.messenger.R;
import com.iot.messenger.presentation.DTO.ResponseDTO;
import com.iot.messenger.presentation.listeners.FragmentsListener;
import com.iot.messenger.presentation.viewModels.SignInViewModel;

import org.jetbrains.annotations.NotNull;

public class SignInFragment extends Fragment {
    private SignInViewModel signInViewModel;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputEditText editTextEmail;
    private TextInputEditText editTextPassword;
    private Button signInButton;
    private TextView signUpNavigation;

    private FragmentsListener signUpNavListener;
    private FragmentsListener signInListener;

    private final View.OnClickListener onSignInButtonClickListener = view -> {
        removeErrors();
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        signInViewModel.signIn(email, password);
    };

    private final View.OnClickListener onSignUpNavClickListener = view -> {
        signUpNavListener.onSignUpNavClicked();
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        try {
            signInListener = (FragmentsListener) context;
            signUpNavListener = (FragmentsListener) context;
        } catch (ClassCastException exception) {
            throw new ClassCastException(context.toString() + " must implement FragmentsListener methods");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        signInListener = null;
        signUpNavListener = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View signInView = inflater.inflate(R.layout.fragment_sign_in, container, false);

        initUI(signInView);

        signInButton.setOnClickListener(onSignInButtonClickListener);
        signUpNavigation.setOnClickListener(onSignUpNavClickListener);

        registerViewModel();
        workWithResponse();

        return signInView;
    }

    private void registerViewModel() {
        signInViewModel = new ViewModelProvider(this).get(SignInViewModel.class);
    }

    private void workWithResponse() {
        signInViewModel.getResponseDTOMutableLiveData().observe(getViewLifecycleOwner(), responseDTO -> {
            if (responseDTO.isSignedIn()) {
                Toast.makeText(getActivity(), "Logged in", Toast.LENGTH_SHORT).show();
                signInListener.onSignInClicked();
            } else {
                Toast.makeText(getActivity(), "Could not log in", Toast.LENGTH_SHORT).show();
                showErrors(responseDTO);
            }
        });
    }

    private void showErrors(ResponseDTO response) {

        if (response.isWrongEmailOrPassword()) {
            return;
        }

        if (!response.isValidEmail()) {
            textInputLayoutEmail.setError("Email format is not correct");
        }
        if (!response.isValidPassword()) {
            textInputLayoutPassword.setError("Password format is not correct");
        }
    }

    private void removeErrors() {
        textInputLayoutEmail.setError(null);
        textInputLayoutPassword.setError(null);
    }

    private void initUI(View signInView) {
        textInputLayoutEmail = signInView.findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = signInView.findViewById(R.id.textInputLayoutPassword);
        editTextEmail = signInView.findViewById(R.id.editTextEmail);
        editTextPassword = signInView.findViewById(R.id.editTextPassword);
        signInButton = signInView.findViewById(R.id.buttonSignIn);
        signUpNavigation = signInView.findViewById(R.id.signUpNav);
    }
}