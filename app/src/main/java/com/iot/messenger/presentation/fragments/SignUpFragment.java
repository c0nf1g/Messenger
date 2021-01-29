package com.iot.messenger.presentation.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.iot.messenger.R;
import com.iot.messenger.presentation.DTO.ResponseDTO;
import com.iot.messenger.presentation.listeners.FragmentsListener;
import com.iot.messenger.presentation.viewModels.SignUpViewModel;

import org.jetbrains.annotations.NotNull;

public class SignUpFragment extends Fragment {
    private SignUpViewModel signUpViewModel;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutConfirmPassword;
    private TextInputEditText editTextEmail;
    private TextInputEditText editTextPassword;
    private TextInputEditText editTextConfirmPassword;
    private Button signUpButton;
    private Toolbar toolbarSignUp;

    private FragmentsListener signInNavListener;
    private FragmentsListener signUpListener;

    private final View.OnClickListener onSignUpButtonClickListener = v -> {
        removeErrors();
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        String confirmPassword = editTextConfirmPassword.getText().toString();

        signUpViewModel.signUp(email, password, confirmPassword);
    };

    private final View.OnClickListener onSignInNavClickListener = view -> {
        signInNavListener.onSignInNavClicked();
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        try {
            signUpListener = (FragmentsListener) context;
            signInNavListener = (FragmentsListener) context;
        } catch (ClassCastException exception) {
            throw new ClassCastException(context.toString() + " must implement FragmentsListener methods");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        signUpListener = null;
        signInNavListener = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View signUpView = inflater.inflate(R.layout.fragment_sign_up, container, false);

        initUI(signUpView);

        signUpButton.setOnClickListener(onSignUpButtonClickListener);
        toolbarSignUp.setNavigationOnClickListener(onSignInNavClickListener);

        registerViewModel();

        workWithResponse();

        return signUpView;
    }

    private void registerViewModel() {
        signUpViewModel = new ViewModelProvider(this).get(SignUpViewModel.class);
    }

    private void workWithResponse() {
        signUpViewModel.getResponseDTOMutableLiveData().observe(getViewLifecycleOwner(), responseDTO -> {
            if (responseDTO.isSignedUp()) {
                Toast.makeText(getActivity(), "Signed Up", Toast.LENGTH_SHORT).show();
                signUpListener.onSignUpClicked();
            } else {
                showErrors(responseDTO);
            }
        });
    }

    private void showErrors(ResponseDTO response) {

        if (response.isFailedToCreateAccount()) {
            return;
        }

        if (!response.isValidEmail()) {
            textInputLayoutEmail.setError("Email format is not correct");
        }

        if (!response.isValidPassword()) {
            textInputLayoutPassword.setError("Password format is not correct");
        }

        if (!response.isValidConfirmPassword()) {
            textInputLayoutConfirmPassword.setError("Confirm password format is not correct");
        }

        if (!response.isPasswordMatches()) {
            textInputLayoutConfirmPassword.setError("Passwords are not matches");
        }
    }

    private void removeErrors() {
        textInputLayoutEmail.setError(null);
        textInputLayoutPassword.setError(null);
        textInputLayoutConfirmPassword.setError(null);
    }

    private void initUI(View signUpView) {
        textInputLayoutEmail = signUpView.findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = signUpView.findViewById(R.id.textInputLayoutPassword);
        textInputLayoutConfirmPassword = signUpView.findViewById(R.id.textInputLayoutConfirmPassword);
        editTextEmail = signUpView.findViewById(R.id.editTextEmail);
        editTextPassword = signUpView.findViewById(R.id.editTextPassword);
        editTextConfirmPassword = signUpView.findViewById(R.id.editTextConfirmPassword);
        signUpButton = signUpView.findViewById(R.id.buttonSignUp);
        toolbarSignUp = signUpView.findViewById(R.id.toolbarSignUp);
    }
}