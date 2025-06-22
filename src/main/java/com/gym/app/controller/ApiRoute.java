package com.gym.app.controller;


/**
 * Created by Manjunath Reddy
 **/
public class ApiRoute {
    public static final String BASE_MAPPING_API_V1 = "/app/services/api/v1";
    public static final String METADATA = "/metadata";
    public static final String CONFIGURATION = "/config";
    // Admin
    public static final String ALL_MEMBERSHIPS = "/admin/memberships";
    public static final String MEMBERSHIP = "/admin/memberships/retrieveByMembershipId";
    // All Other
    public static final String MEMBERSHIP_PLAN = "/user/memberships/chooseMembershipPlan";
    public static final String CONTACT_US = "/contactUs";
    public static final String CONTACT_US_RETRIEVE_BY_CATEGORY_NAME = "/contactUs/retrieveByCategoryName";
    public static final String LOCATION_COUNTRY = "/location/country";
    public static final String LOCATION_STATE_BY_COUNTRY_ID = "/location/state";
    public static final String LOCATION_CITY_BY_STATE_ID = "/location/city";
    public static final String USER_SIGN_UP = "/user/signUp";
    public static final String WORKOUT_SCHEDULE_UPDATE = "/user/workoutSchedule";
    public static final String USER_LOG_IN = "/user/login";
    public static final String USER_LOG_OUT = "/user/logout";
    public static final String USER_FORGOT_PASSWORD = "/user/forgotPassword";
    public static final String USER_FORGOT_LOGINID = "/user/forgotLoginId";
    public static final String USER_UPDATE_PASSWORD = "/user/updatePassword";
    public static final String USER_ACCOUNT_PERSONAL_PROFILE = "/user/account/personalProfile";
    public static final String USER_ACCOUNT_PERSONAL_PROFILE_RETRIEVE = "/user/account/retrievePersonalProfile";
    public static final String USER_HEALTH_PROFILE_RETRIEVE = "/user/account/retrieveHealthProfile";
    public static final String USER_ACCOUNT_HEALTH_PROFILE = "/user/account/healthProfile";
    public static final String USER_ACCOUNT_HEALTH_PROFILE_RETRIEVE = "/user/account/retrievehealthProfile";
    public static final String USER_ACCOUNT_DETAILS_DELETE = "/user/loginId/delete";
    public static final String USER_THANKS = "/user/thanks";

    public static final String MESSAGE_CENTER_SEND = "/messageCenter/sendMessage";
    public static final String MESSAGE_CENTER_BY_LOGIN_ID_STATUS = "/messageCenter/retrieveByStatus";

    public static final String MESSAGE_CENTER_BY_LOGIN_ID_METADATA_READ = "/messageCenter/retrieveReadMessagesByMetaData";
    public static final String MESSAGE_CENTER_BY_LOGIN_ID_METADATA_UNREAD = "/messageCenter/retrieveUnReadMessagesByMetaData";
    public static final String MESSAGE_CENTER_UPDATE_METADATA = "/messageCenter/metadata";
    public static final String WORKOUT_DETAILS_BY_LOGIN_ID = "/workout/details/retrieveByLoginId";
    public static final String EXERCISE_DETAILS_BY_ID = "/exercise/details/retrieveById";
    public static final String EXERCISE_DETAILS_BY_ID_LIST = "/exercise/details/retrieveByIds";
    public static final String CHAT_AI = "/chat/ai";

    public static final String ALL_USER_DETAILS="/getUserDetails";
    public static final String ALL_USER_PERSONAL_DETAILS_BY_DATE="/getUserPersonalDetails/date";
    public static final String ALL_USER_ACCOUNT_DETAILS_BY_DATE="/getUserAccountDetails/date";
    public static final String ALL_MESSAGE_DETAILS_BY_DATE="/getMessagesByDate/date";
    public static final String ALL_DAILY_DIET_DETAILS_BY_DATE="/getDailyDietDetails/date";
    public static final String ALL_DAILY_EXERCISE_DETAILS_BY_DATE="/getDailyExerciseDetails/date";
    public static final String ALL_HEALTH_DETAILS_BY_DATE="/getHealthDetailsByDate/date";
    public static final String CREATE_WORKOUT_PLAN="/create/workoutPlan";

    private ApiRoute() {
    }


}
