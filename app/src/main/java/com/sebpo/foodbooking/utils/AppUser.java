package com.sebpo.foodbooking.utils;


/**
 * Created by Shihab .
 */

public class AppUser {
    private static final String KEY_USER_ID = "KEY_USER_ID";
    private static final String KEY_USER_NAME = "KEY_USER_NAME";
    private static final String KEY_PROFILE_IMAGE = "KEY_PROFILE_IMAGE";
    private static final String KEY_EMAIL = "KEY_EMAIL";
    private static final String KEY_PHONE_NUMBER = "KEY_PHONE_NUMBER";
    private static final String KEY_PASSWORD = "KEY_PASSWORD";
    private static final String KEY_ADDRESS = "KEY_ADDRESS";
    private static final String KEY_DOB = "DATE_OF_BIRTH";
    private static final String KEY_SESSION_TOKEN = "KEY_SESSION_TOKEN";

    private static final String KEY_BMDC_REGISTRATION_ID = "KEY_BMDC_REGISTRATION_ID";
    private static final String KEY_DESIGNATION = "KEY_DESIGNATION";
    private static final String KEY_TITLE = "KEY_TITLE";
    private static final String KEY_SPECIALITY = "KEY_SPECIALITY";
    private static final String KEY_DEVICE_TOKEN = "KEY_DEVICE_TOKEN";
    private static final String KEY_QUALIFICATION = "KEY_QUALIFICATION";
    private static final String KEY_YEAR_OF_EXP = "KEY_YEAR_OF_EXP";
    private static final String KEY_SIGNED_UP = "KEY_SIGNED_UP";
    private static final String KEY_USER_IS_LOGGED_IN = "KEY_USER_IS_LOGGED_IN";

    private static final String KEY_ONLINE = "KEY_ONLINE";
    private static final String KEY_AT_HOME_APPOINTMENT_AVAILABLE = "KEY_AT_HOME_APPOINTMENT_AVAILABLE";
    private static final String KEY_BALANCE = "KEY_BALANCE";
    private static final String KEY_CURRENCY = "KEY_CURRENCY";
    private static final String KEY_LANGUAGE = "KEY_LANGUAGE";

    private AppSharedPreferences appSharedPreferences = AppSharedPreferences.getDefaultPreferences();
    private static AppUser currentUser = new AppUser();

    private Long sleepTime;
    private Long wakeUpTime;

    private Long createdAt;
    private Long updatedAt;

    private String deviceToken;
    private String language;

    private String userId;
    private String name;
    private String displayName;
    private String profileImageURL;
    private String phoneNumber;
    private String passcode;

    private String pin;

    private String address;

    private String email;

    private String password;

    private Integer age;

    private String balance;
    private String currency;

    private String registrationID;
    private String designation;
    private String title;
    private String speciality;
    private String qualification;
    private Integer yearOfExp;

    private String sessionToken;

    private boolean atHomeAppointmentAvailable;
    private boolean offline;
    private boolean signedUp;
    private boolean signedIn;
    private boolean isDoctor;

    public boolean isLoggedOut(){
        return false;
    }

    public AppUser() {
    }

    public static AppUser getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(AppUser currentUser) {
        AppUser.currentUser = currentUser;
    }

    public String getDeviceToken() {
        return this.appSharedPreferences.getString(KEY_DEVICE_TOKEN);
    }

    public void setDeviceToken(String deviceToken) {
        this.appSharedPreferences.putString(KEY_DEVICE_TOKEN, deviceToken);
    }

    public String getUserId() {
        return this.appSharedPreferences.getString(KEY_USER_ID);
    }

    public void setUserId(String userId) {
        this.appSharedPreferences.putString(KEY_USER_ID, userId);
    }

    public String getName() {
        return this.appSharedPreferences.getString(KEY_USER_NAME);
    }

    public void setName(String name) {
        this.appSharedPreferences.putString(KEY_USER_NAME, name);
    }

    public String getDisplayName() {
        if (this.designation != null) {
            return this.designation + " " + this.name;
        }

        return this.name;
    }

    public String getProfileImageURL() {
        return ApplicationData.FINAL_URL + this.appSharedPreferences.getString(KEY_PROFILE_IMAGE);
    }

    public void setProfileImageURL(String profileImageURL) {
        this.appSharedPreferences.putString(KEY_PROFILE_IMAGE, profileImageURL);
    }

    public String getPhoneNumber() {
        return this.appSharedPreferences.getString(KEY_PHONE_NUMBER);
    }

    public void setPhoneNumber(String phoneNumber) {
        this.appSharedPreferences.putString(KEY_PHONE_NUMBER, phoneNumber);
    }

    public String getAddress() {
        return this.appSharedPreferences.getString(KEY_ADDRESS);
    }

    public void setAddress(String address) {
        this.appSharedPreferences.putString(KEY_ADDRESS, address);
    }

    public String getEmail() {
        return this.appSharedPreferences.getString(KEY_EMAIL);
    }

    public void setEmail(String email) {
        this.appSharedPreferences.putString(KEY_EMAIL, email);
    }

    public String getPassword() {
        return this.appSharedPreferences.getString(KEY_PASSWORD);
    }

    public void setPassword(String password) {
        this.appSharedPreferences.putString(KEY_PASSWORD, password);
    }

    public Integer getAge() {
        return this.appSharedPreferences.getInteger(KEY_DOB);
    }

    public void setAge(Integer age) {
        this.appSharedPreferences.putInteger(KEY_DOB, age);
    }

    public String getBalance() {
        return this.appSharedPreferences.getString(KEY_BALANCE);
    }

    public void setBalance(String balance) {
        this.appSharedPreferences.putString(KEY_BALANCE, balance);
    }

    public String getCurrency() {
        return this.appSharedPreferences.getString(KEY_CURRENCY);
    }

    public void setCurrency(String currency) {
        this.appSharedPreferences.putString(KEY_CURRENCY, currency);
    }

    public String getLanguage() {
        return this.appSharedPreferences.getString(KEY_LANGUAGE);
    }

    public void setLanguage(String language) {
        this.appSharedPreferences.putString(KEY_LANGUAGE, language);
    }

    public String getRegistrationID() {
        return this.appSharedPreferences.getString(KEY_BMDC_REGISTRATION_ID);
    }

    public void setRegistrationID(String registrationID) {
        this.appSharedPreferences.putString(KEY_BMDC_REGISTRATION_ID, registrationID);
    }

    public String getDesignation() {
        return this.appSharedPreferences.getString(KEY_DESIGNATION);
    }

    public void setDesignation(String designation) {
        this.appSharedPreferences.putString(KEY_DESIGNATION, designation);
    }

    public String getTitle() {
        return this.appSharedPreferences.getString(KEY_TITLE);
    }

    public void setTitle(String title) {
        this.appSharedPreferences.putString(KEY_TITLE, title);
    }

    public String getSpeciality() {
        return this.appSharedPreferences.getString(KEY_SPECIALITY);
    }

    public void setSpeciality(String speciality) {
        this.appSharedPreferences.putString(KEY_SPECIALITY,speciality);
    }

    public String getQualification() {
        return this.appSharedPreferences.getString(KEY_QUALIFICATION);
    }

    public void setQualification(String qualification) {
        this.appSharedPreferences.putString(KEY_QUALIFICATION, qualification);
    }

    public Integer getYearOfExp() {
        return this.appSharedPreferences.getInteger(KEY_YEAR_OF_EXP);
    }

    public void setYearOfExp(Integer yearOfExp) {
        this.appSharedPreferences.putInteger(KEY_YEAR_OF_EXP, yearOfExp);
    }

    public String getSessionToken() {
        return this.appSharedPreferences.getString(KEY_SESSION_TOKEN);
    }

    public void setSessionToken(String sessionToken) {
        this.appSharedPreferences.putString(KEY_SESSION_TOKEN,sessionToken);
    }

    public boolean isAtHomeAppointmentAvailable() {
        return this.appSharedPreferences.getBoolean(KEY_AT_HOME_APPOINTMENT_AVAILABLE);
    }

    public void setAtHomeAppointmentAvailable(boolean atHomeAppointmentAvailable) {
        this.appSharedPreferences.putBoolean(KEY_AT_HOME_APPOINTMENT_AVAILABLE, atHomeAppointmentAvailable);
    }

    public boolean isOffline() {
        return this.appSharedPreferences.getBoolean(KEY_ONLINE);
    }

    public void setOffline(boolean offline) {
        this.appSharedPreferences.putBoolean(KEY_ONLINE,offline);
    }

    public boolean isSignedUp() {
        return (this.getSessionToken() != null && !this.getSessionToken().isEmpty());
    }

    public void setSignedUp(boolean signedUp) {
        this.appSharedPreferences.putBoolean(KEY_SIGNED_UP, signedUp);
    }

    public boolean isSignedIn() {
        return (this.getSessionToken() != null && !this.getSessionToken().isEmpty());
    }

    public void setSignedIn(boolean signedIn) {
        this.appSharedPreferences.putBoolean(KEY_USER_IS_LOGGED_IN, signedIn);
    }


}
