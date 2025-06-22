package com.gym.app.common;

/**
 * Created by Manjunath Reddy
 */
public class Constants {

    private Constants() {
    }
    public static final String KEY_ACCEPT = "Accept";
    public static final String KEY_CONTENT_TYPE = "Content-Type";
    public static final String KEY_ORIGIN = "Origin";
    public static final String VALUE_CONTENT_TYPE_JSON = "application/json";
    public static final int RETRY_MAX_ATTEMPTS_EMAIL = 3;
    public static final int EXPIRY_HOURS_PACK_CALL = 1;
    public static final String SOURCE_SYSTEM = "System";
    public static final String STATUS_REGISTERED = "Registered";
    public static final String STATUS_ACTIVE = "Active";
    public static final String STATUS_NOT_UPLOADED = "Not Uploaded";
    public static final String STATUS_UPLOADED = "Uploaded";
    public static final String STATUS_EXIT = "Exit";
    public static final String STATUS_INACTIVE = "Inactive";
    public static final String STATUS_INVITED = "Invited";
    public static final String STATUS_SCHEDULED = "Scheduled";
    public static final String STATUS_COMPLETED = "Completed";
    public static final String STATUS_OVERRIDDEN = "Overridden";
    public static final String STATUS_ACCEPTED = "Accepted";
    public static final String STATUS_RECEIVED = "Received";
    public static final String STATUS_SUCCESS = "Success";
    public static final String STATUS_FAILURE = "Failure";
    public static final String STATUS_EXPIRED = "Expired";
    public static final String STATUS_YES = "Yes";
    public static final String STATUS_NO = "No";
    public static final String USER_TYPE_USER = "User";
    public static final String USER_TYPE_ADMIN = "Admin";
    public static final String USER_LOGOUT_MESSAGE = "You have successfully logged out.";
    public static final String USER_THANKS_RESPONSE_MESSAGE = "Thanks for encouraging us.";
    public static final String DELIMITER_SPACE = " ";
    public static final String DELIMITER_UNDERSCORE = "_";
    public static final String DELIMITER_NEW_LINE = " \n ";
    public static final String DELIMITER_PERIOD = ".";
    public static final String UNITS_MONTHS = "Month(s)";
    public static final String KEY_PACK_NAME = "Pack";
    public static final String REASON_USER_ACCOUNT_COMPANY_DELETE = "Account company deleted by User";
    public static final int MINUTES_ONE = 1;
    public static final int LIMIT_RECORDS = 100;
    public static final int THRESHOLD_DAYS_LOGIN_ISSUES = 180;
    public static final int THRESHOLD_DAYS_USER_RATiNG = 30;
    //REGEX
    public static final String REGEX_USER = "$user$";
    public static final String REGEX_HOURS = "$hours$";

    //Message Center Templates
    public static final String MESSAGE_WELCOME = "Thanks for registering with us. Your account is now active.";
    public static final String MESSAGE_SUBSCRIPTION_PLAN_ACTIVATED_FT = "Your free subscription plan is activated.";
    public static final String MESSAGE_PASSWORD_UPDATED_SUCCESS = "You have updated your password successfully. Report us immediately if it it not you.";


}
