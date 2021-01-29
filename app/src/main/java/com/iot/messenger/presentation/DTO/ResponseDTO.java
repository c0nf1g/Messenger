package com.iot.messenger.presentation.DTO;

public class ResponseDTO {

    private boolean isValidEmail;
    private boolean isValidPassword;
    private boolean isValidConfirmPassword;
    private boolean isPasswordMatches;
    private boolean isSignedIn;
    private boolean isSignedUp;
    private boolean isWrongEmailOrPassword;
    private boolean isFailedToCreateAccount;

    public boolean isValidEmail() {
        return isValidEmail;
    }

    public void setValidEmail(boolean validEmail) {
        isValidEmail = validEmail;
    }

    public boolean isValidPassword() {
        return isValidPassword;
    }

    public void setValidPassword(boolean validPassword) {
        isValidPassword = validPassword;
    }

    public boolean isValidConfirmPassword() {
        return isValidConfirmPassword;
    }

    public void setValidConfirmPassword(boolean validConfirmPassword) {
        isValidConfirmPassword = validConfirmPassword;
    }

    public boolean isPasswordMatches() {
        return isPasswordMatches;
    }

    public void setPasswordMatches(boolean passwordMatches) {
        isPasswordMatches = passwordMatches;
    }

    public boolean isSignedIn() {
        return isSignedIn;
    }

    public void setSignedIn(boolean signedIn) {
        isSignedIn = signedIn;
    }

    public boolean isSignedUp() {
        return isSignedUp;
    }

    public void setSignedUp(boolean signedUp) {
        isSignedUp = signedUp;
    }

    public boolean isWrongEmailOrPassword() {
        return isWrongEmailOrPassword;
    }

    public void setWrongEmailOrPassword(boolean wrongEmailOrPassword) {
        isWrongEmailOrPassword = wrongEmailOrPassword;
    }

    public boolean isFailedToCreateAccount() {
        return isFailedToCreateAccount;
    }

    public void setFailedToCreateAccount(boolean failedToCreateAccount) {
        isFailedToCreateAccount = failedToCreateAccount;
    }
}
