package com.gym.app.service.impl;

import com.google.common.base.Strings;
import com.gym.app.baseframework.exception.SystemException;
import com.gym.app.baseframework.exception.enums.ApiErrors;
import com.gym.app.common.Constants;
import com.gym.app.common.DateTimeFormatterUtil;
import com.gym.app.entity.*;
import com.gym.app.repository.*;
import com.gym.app.service.UserDetailsService;
import com.gym.app.service.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    UserPersonalDetailsRepository userPersonalDetailsRepository;
    @Autowired
    UserHealthDetailsRepository userHealthDetailsRepository;

    @Autowired
    MembershipRepository membershipRepository;

    @Autowired
    WorkoutRepository workoutRepository;

    @Autowired
    ExerciseRepository exerciseRepository;

    @Autowired
    private UserLoginDataRepository loginDataRepository;

    @Override
    public UserSignUpResponse signUp(UserSignUpRequest userSignUpRequest) throws SystemException {
        logger.info("Request received -> {}", userSignUpRequest);
        if (userSignUpRequest == null || Strings.isNullOrEmpty(userSignUpRequest.getEmailId()) || Strings.isNullOrEmpty(userSignUpRequest.getLoginId())
                || Strings.isNullOrEmpty(userSignUpRequest.getPassword()) ) {
            logger.error("signUp: Missing mandatory data -> {}", userSignUpRequest);
            throw new SystemException(ApiErrors.MISSING_MANDATORY_FIELDS_FOR_ATTRIBUTES);
        }
        if (userAccountRepository.findByEmailId(userSignUpRequest.getEmailId().toLowerCase()) != null) {
            logger.error("signUp: User already registered for emailId -> {}", userSignUpRequest.getEmailId());
            throw new SystemException(ApiErrors.USER_ALREADY_EXISTS);
        }
        if (userAccountRepository.findByLoginId(userSignUpRequest.getLoginId().toLowerCase()) != null) {
            logger.error("signUp: User already registered for loginId -> {}", userSignUpRequest.getLoginId());
            throw new SystemException(ApiErrors.USER_ALREADY_EXISTS);
        }
        UserAccount userAccount = new UserAccount();
        logger.info("userSignUpRequest: loginId -> {}, emailId -> {}", userSignUpRequest.getLoginId(), userSignUpRequest.getEmailId());
        userAccount.setLoginId(userSignUpRequest.getLoginId());
        userAccount.setEmailId(userSignUpRequest.getEmailId());
        userAccount.setPassword(userSignUpRequest.getPassword());
        userAccount.setPersonal_details_status(Constants.STATUS_REGISTERED);
        userAccount.setHealth_details_status(Constants.STATUS_NOT_UPLOADED);
        userAccount.setCreatedTs(DateTimeFormatterUtil.getCurrentTimestampInUTC());
        userAccount.setUpdatedTs(DateTimeFormatterUtil.getCurrentTimestampInUTC());
        try {
            userAccountRepository.save(userAccount);
        } catch (Exception ex) {
            logger.error("signUp: Exception while saving the data. {}", ex);
            throw new SystemException(ApiErrors.INTERNAL_PROCESSING_ERROR);
        }
        logger.info("UserAccount registered successfully: loginId -> {}, emailId-> {}", userSignUpRequest.getLoginId(), userSignUpRequest.getEmailId());
        UserSignUpResponse userSignUpResponse = new UserSignUpResponse();
        userSignUpResponse.setLoginId(userAccount.getLoginId());
        userSignUpResponse.setEmailId(userAccount.getEmailId());
        userSignUpResponse.setPersonal_details_status(userAccount.getPersonal_details_status());
        userSignUpResponse.setHealth_details_status(userAccount.getHealth_details_status());
        logger.info("Response sent successfully");
        return userSignUpResponse;
    }


    @Override
    public UserLogInResponse userLogin(UserLogInRequest userLogInRequest) throws SystemException {
        logger.info("Request received -> {}", userLogInRequest);
        UserLogInResponse userLogInResponse = new UserLogInResponse();
        if (userLogInRequest == null || Strings.isNullOrEmpty(userLogInRequest.getLoginId()) || Strings.isNullOrEmpty(userLogInRequest.getPassword())) {
            logger.error("userLogin: Missing mandatory data");
            throw new SystemException(ApiErrors.MISSING_MANDATORY_FIELDS_FOR_ATTRIBUTES);
        }
        if (userAccountRepository.findByLoginId(userLogInRequest.getLoginId()) == null) {
            logger.error("userLogin: Account doesn't exists for loginId -> {}", userLogInRequest.getLoginId());
            throw new SystemException(ApiErrors.USER_DOESNOT_EXISTS);
        }
        logger.info("userLogin: User authorization check for loginId -> {}", userLogInRequest.getLoginId());
        UserAccount userAccount = userAccountRepository.userAuthorizationCheck(userLogInRequest.getLoginId(), userLogInRequest.getPassword());
        if (userAccount == null) {
            logger.error("userLogin: Password mismatch for -> {}", userLogInRequest.getLoginId());
            throw new SystemException(ApiErrors.USER_ACCOUNT_PASSWORD_MISMATCH);
        }
        LoginData loginData = new LoginData();
        loginData.setLoginId(userLogInRequest.getLoginId());
        loginData.setLoginTimestamp(DateTimeFormatterUtil.getCurrentTimestampInUTC());
        loginDataRepository.save(loginData);
        userLogInResponse.setLoginId(userLogInRequest.getLoginId());
        userLogInResponse.setEmailId(userAccount.getEmailId());
        userLogInResponse.setAccountStatus(userAccount.getPersonal_details_status());
        return userLogInResponse;
    }


    @Override
    public UserLogOutResponse userLogout(UserLogOutRequest userLogOutRequest) throws SystemException {
        logger.info("Request received -> {}", userLogOutRequest);
        if (userLogOutRequest == null || Strings.isNullOrEmpty(userLogOutRequest.getLoginId()) || Strings.isNullOrEmpty(userLogOutRequest.getToken())) {
            logger.error("userLogout: Missing mandatory data");
            throw new SystemException(ApiErrors.MISSING_MANDATORY_FIELDS_FOR_ATTRIBUTES);
        }
        logger.info("userLogout: User logout for loginId -> {}", userLogOutRequest.getLoginId());
        //TODO -- Expire the token
        List<LoginData> recentLogins = loginDataRepository.findAllByLoginIdOrderByLoginTimestampDesc(userLogOutRequest.getLoginId());
        if (!recentLogins.isEmpty()) {
            LoginData latest = recentLogins.get(0);
            if (latest.getLogoutTimestamp() == null) {
                latest.setLogoutTimestamp(DateTimeFormatterUtil.getCurrentTimestampInUTC());
                loginDataRepository.save(latest);
            }
        }
        UserLogOutResponse userLogOutResponse = new UserLogOutResponse();
        userLogOutResponse.setLoginId(userLogOutRequest.getLoginId());
        userLogOutResponse.setMessage(Constants.USER_LOGOUT_MESSAGE);
        logger.info("userLogout: User logout is successful for loginId -> {}", userLogOutRequest.getLoginId());
        return userLogOutResponse;
    }

    @Override
    public UserAccountResponse saveOrUpdateUserAccountProfile(UserAccountRequest userAccountRequest, boolean statusUpdateFlag) throws SystemException {
        logger.info("Request received -> {}, statusUpdateFlag -> {}", userAccountRequest, statusUpdateFlag);
        UserAccountResponse userAccountResponse = new UserAccountResponse();
        userAccountResponse.setLoginId(userAccountRequest.getLoginId());
        UserAccount userAccount = userAccountRepository.findByLoginId(userAccountRequest.getLoginId());
        logger.info("saveOrUpdateUserAccountProfile: userAccountRequest -> {}, statusUpdateFlag-> {}", userAccountRequest, statusUpdateFlag);
        if (userAccountRequest == null || Strings.isNullOrEmpty(userAccountRequest.getLoginId())) {
            logger.error("saveOrUpdateUserAccountProfile: Missing mandatory data -> {}", userAccountRequest);
            throw new SystemException(ApiErrors.MISSING_MANDATORY_FIELDS_FOR_ATTRIBUTES);
        }
        if (userAccountRepository.findByLoginId(userAccountRequest.getLoginId()) == null) {
            logger.error("saveOrUpdateUserAccountProfile: User account doesn't exist for loginId -> {}", userAccountRequest.getLoginId());
            throw new SystemException(ApiErrors.USER_DOESNOT_EXISTS);
        }
        if (userAccountRequest.getPersonalInformation() != null) {
            logger.info("saveOrUpdateUserAccountProfile: Personal Information -> {}", userAccountRequest.getPersonalInformation());
            UserPersonalDetails userPersonalDetails = userPersonalDetailsRepository.findByLoginId(userAccountRequest.getLoginId());
            if (userPersonalDetails == null) {
                logger.info("saveOrUpdateUserAccountProfile: Existing profile details NOT available. Saving for loginId -> {}", userAccountRequest.getLoginId());
                userPersonalDetails = new UserPersonalDetails();
                userPersonalDetails.setCreatedTs(DateTimeFormatterUtil.getCurrentTimestampInUTC());
                userAccount.setPersonal_details_status(Constants.STATUS_ACTIVE);
            } else {
                logger.info("saveOrUpdateUserAccountProfile: Existing profile details available. Updating for loginId -> {}", userAccountRequest.getLoginId());
            }
            userPersonalDetails.setLoginId(userAccountRequest.getLoginId());
            userPersonalDetails.setFirstName(userAccountRequest.getPersonalInformation().getFirstName());
            userPersonalDetails.setLastName(userAccountRequest.getPersonalInformation().getLastName());
            userPersonalDetails.setMobileNumber(userAccountRequest.getPersonalInformation().getMobileNumber());
            userPersonalDetails.setCountryId(userAccountRequest.getPersonalInformation().getCountryId());
            userPersonalDetails.setStateId(userAccountRequest.getPersonalInformation().getStateId());
            userPersonalDetails.setCityId(userAccountRequest.getPersonalInformation().getCityId());


            userPersonalDetails.setUpdatedTs(DateTimeFormatterUtil.getCurrentTimestampInUTC());
            userPersonalDetailsRepository.save(userPersonalDetails);
            logger.info("saveOrUpdateUserAccountProfile: Personal Information saved successfully -> {}", userAccountRequest.getPersonalInformation());
        }



        if (statusUpdateFlag) {
            if (userPersonalDetailsRepository.findByLoginId(userAccountRequest.getLoginId()) == null) {
                logger.info("saveOrUpdateUserAccountProfile: SignUp requirements not completed for loginId -> {}, status -> {}", userAccountRequest.getLoginId(), Constants.STATUS_REGISTERED);
                userAccountResponse.setPersonal_details_status(Constants.STATUS_REGISTERED);
            } else
            {
                logger.info("saveOrUpdateUserAccountProfile: SignUp requirements are completed for loginId -> {}, status -> {}", userAccountRequest.getLoginId(), Constants.STATUS_ACTIVE);
                //messageCenterService.triggerMessage(Constants.SOURCE_SYSTEM, userAccountRequest.getLoginId(), Constants.MESSAGE_WELCOME, null);
                userAccountResponse.setPersonal_details_status(Constants.STATUS_ACTIVE);
            }
        }

        else {
            userAccount = userAccountRepository.findByLoginId(userAccountRequest.getLoginId().toLowerCase());
            logger.info("saveOrUpdateUserAccountProfile: Status update not required for loginId -> {}, status -> {}", userAccountRequest.getLoginId(), userAccount.getPersonal_details_status());
            userAccountResponse.setPersonal_details_status(userAccount.getPersonal_details_status());
        }
        logger.info("Response sent successfully for loginId -> {}", userAccountRequest.getLoginId());
        return userAccountResponse;
    }
    @Override
    public UserAccountProfileResponse retrieveUserAccountProfileByLoginId(String loginId) throws SystemException {
        logger.info("Request received -> {}", loginId);
        if (loginId == null) {
            logger.error("retrieveUserAccountProfileByLoginId: Missing mandatory data");
            throw new SystemException(ApiErrors.MISSING_MANDATORY_FIELDS_FOR_ATTRIBUTES);
        }
        UserAccount userAccount = userAccountRepository.findByLoginId(loginId.toLowerCase());
        if (userAccount == null) {
            logger.error("retrieveUserAccountProfileByLoginId: User account doesn't exists");
            throw new SystemException(ApiErrors.USER_DOESNOT_EXISTS);
        }
        if (userAccount != null && userAccount.getPersonal_details_status().equalsIgnoreCase(Constants.STATUS_REGISTERED)) {
            logger.error("retrieveUserAccountProfileByLoginId: User account exists but not uploaded personal details status");
            throw new SystemException(ApiErrors.USER_PERSONAL_STATUS_DOESNOT_EXISTS);
        }

        UserAccountProfileResponse userAccountProfileResponse = new UserAccountProfileResponse();
        userAccountProfileResponse.setLoginId(loginId);
        userAccountProfileResponse.setEmailId(userAccount.getEmailId());
        userAccountProfileResponse.setAccountStatus(userAccount.getPersonal_details_status());
        userAccountProfileResponse.setUserPersonalDetails(userPersonalDetailsRepository.findByLoginId(loginId));
        return userAccountProfileResponse;
    }
    @Override
    public UserAccountProfileResponse retrieveUserAccountProfile(RetrieveUserProfileRequest retrieveUserProfileRequest) throws SystemException {
        logger.info("Request received -> {}", retrieveUserProfileRequest);
        if (retrieveUserProfileRequest == null || (Strings.isNullOrEmpty(retrieveUserProfileRequest.getEmailId()) && Strings.isNullOrEmpty(retrieveUserProfileRequest.getLoginId()))) {
            logger.error("retrieveUserAccountProfile: Missing mandatory data");
            throw new SystemException(ApiErrors.MISSING_MANDATORY_FIELDS_FOR_ATTRIBUTES);
        }
        if (!(Strings.isNullOrEmpty(retrieveUserProfileRequest.getEmailId())) && !(Strings.isNullOrEmpty(retrieveUserProfileRequest.getLoginId()))) {
            logger.info("retrieveUserAccountProfile: Retrieving user account details for emailId and loginId combination");
            UserAccount userAccount = userAccountRepository.findByLoginId(retrieveUserProfileRequest.getLoginId().toLowerCase());
            if (userAccount == null) {
                logger.error("retrieveUserAccountProfile: User account doesn't exists");
                throw new SystemException(ApiErrors.USER_DOESNOT_EXISTS);
            }
            if (userAccount.getEmailId().equalsIgnoreCase(retrieveUserProfileRequest.getEmailId())) {
                logger.info("retrieveUserAccountProfile: Retrieving user account details for emailId -> {} and loginId -> {}",
                        retrieveUserProfileRequest.getEmailId(), retrieveUserProfileRequest.getEmailId());
                return retrieveUserAccountProfileByLoginId(retrieveUserProfileRequest.getLoginId());
            } else {
                logger.error("retrieveUserAccountProfile: User account doesn't exists for emailId -> {} and loginId -> {}",
                        retrieveUserProfileRequest.getEmailId(), retrieveUserProfileRequest.getEmailId());
                throw new SystemException(ApiErrors.USER_DOESNOT_EXISTS);
            }
        }
        if (!(Strings.isNullOrEmpty(retrieveUserProfileRequest.getEmailId()))) {
            logger.info("retrieveUserAccountProfile: Retrieving user account details for emailId -> {}", retrieveUserProfileRequest.getEmailId());
            UserAccount userAccount = userAccountRepository.findByEmailId(retrieveUserProfileRequest.getEmailId().toLowerCase());
            if (userAccount == null) {
                logger.error("retrieveUserAccountProfile: User account doesn't exists for loginId -> {}", retrieveUserProfileRequest.getEmailId());
                throw new SystemException(ApiErrors.USER_DOESNOT_EXISTS);
            }
            logger.info("retrieveUserAccountProfile: User account exists for emailId  -> {}", retrieveUserProfileRequest.getEmailId());
            return retrieveUserAccountProfileByLoginId(userAccount.getLoginId());
        }
        if (!Strings.isNullOrEmpty(retrieveUserProfileRequest.getLoginId())) {
            logger.info("retrieveUserAccountProfile: Retrieving user account details for loginId -> {}", retrieveUserProfileRequest.getLoginId());
            return retrieveUserAccountProfileByLoginId(retrieveUserProfileRequest.getLoginId());
        }
        return null;
    }

    @Override
    public UserHealthResponse saveOrUpdateUserHealthProfile(UserHealthRequest userHealthRequest, boolean statusUpdateFlag) throws SystemException {
        logger.info("Request received -> {}, statusUpdateFlag -> {}", userHealthRequest, statusUpdateFlag);
        UserHealthResponse userHealthResponse = new UserHealthResponse();
        userHealthResponse.setLoginId(userHealthRequest.getLoginId());
        UserAccount userAccount = userAccountRepository.findByLoginId(userHealthRequest.getLoginId());
        logger.info("saveOrUpdateUserHealthProfile: userHealthProfileRequest -> {}, statusUpdateFlag-> {}", userHealthRequest, statusUpdateFlag);
        if (userHealthRequest == null || Strings.isNullOrEmpty(userHealthRequest.getLoginId())) {
            logger.error("saveOrUpdateUserHealthProfile: Missing mandatory data -> {}", userHealthRequest);
            throw new SystemException(ApiErrors.MISSING_MANDATORY_FIELDS_FOR_ATTRIBUTES);
        }
        if (userAccountRepository.findByLoginId(userHealthRequest.getLoginId()) == null) {
            logger.error("saveOrUpdateUserHealthProfile: User account doesn't exist for loginId -> {}", userHealthRequest.getLoginId());
            throw new SystemException(ApiErrors.USER_DOESNOT_EXISTS);
        }
        if (userHealthRequest.getHealthInformation() != null) {
            logger.info("saveOrUpdateUserHealthProfile: Health Information -> {}", userHealthRequest.getHealthInformation());
            UserHealthDetails userHealthDetails = userHealthDetailsRepository.findByLoginId(userHealthRequest.getLoginId());
            if (userHealthDetails == null) {
                logger.info("saveOrUpdateUserHealthProfile: Existing health details NOT available. Saving for loginId -> {}", userHealthRequest.getLoginId());
                userHealthDetails = new UserHealthDetails();
                userHealthDetails.setCreatedTs(DateTimeFormatterUtil.getCurrentTimestampInUTC());
                userAccount.setHealth_details_status(Constants.STATUS_UPLOADED);
            } else {
                logger.info("saveOrUpdateUserHealthProfile: Existing health details available. Updating for loginId -> {}", userHealthRequest.getLoginId());
            }
            userHealthDetails.setLoginId(userHealthRequest.getLoginId());
            userHealthDetails.setAge(userHealthRequest.getHealthInformation().getAge());
            userHealthDetails.setGender(userHealthRequest.getHealthInformation().getGender());
            userHealthDetails.setHeight(userHealthRequest.getHealthInformation().getHeight());
            userHealthDetails.setCurrentWeight(userHealthRequest.getHealthInformation().getCurrentWeight());
            userHealthDetails.setGoalWeight(userHealthRequest.getHealthInformation().getGoalWeight());
            userHealthDetails.setActivityLevel(userHealthRequest.getHealthInformation().getActivityLevel());
            userHealthDetails.setTargetCalories(userHealthRequest.getHealthInformation().getTargetCalories());

            userHealthDetails.setUpdatedTs(DateTimeFormatterUtil.getCurrentTimestampInUTC());
            userHealthDetailsRepository.save(userHealthDetails);
            logger.info("saveOrUpdateUserHealthProfile: Health Information saved successfully -> {}", userHealthRequest.getHealthInformation());
        }



        if (statusUpdateFlag) {
            if (userHealthDetailsRepository.findByLoginId(userHealthRequest.getLoginId()) == null) {
                logger.info("saveOrUpdateUserHealthProfile: SignUp requirements not completed for loginId -> {}, status -> {}", userHealthRequest.getLoginId(), Constants.STATUS_REGISTERED);
                userHealthResponse.setHealth_details_status(Constants.STATUS_NOT_UPLOADED);
            } else
            {
                logger.info("saveOrUpdateUserHealthProfile: SignUp requirements are completed for loginId -> {}, status -> {}", userHealthRequest.getLoginId(), Constants.STATUS_ACTIVE);

                userHealthResponse.setHealth_details_status(Constants.STATUS_UPLOADED);

            }
        }

        else {
            userAccount = userAccountRepository.findByLoginId(userHealthRequest.getLoginId().toLowerCase());
            logger.info("saveOrUpdateUserHealthProfile: Status update not required for loginId -> {}, status -> {}", userHealthRequest.getLoginId(), userAccount.getPersonal_details_status());
            userHealthResponse.setHealth_details_status(userAccount.getPersonal_details_status());
        }
        logger.info("Response sent successfully for loginId -> {}", userHealthRequest.getLoginId());
        return userHealthResponse;
    }

    @Override
    public UserDeleteResponse userAccountDetailsDelete(String loginId) throws SystemException {
        logger.info("userAccountDetailsDelete: Request received -> {}", loginId);
        UserDeleteResponse userDeleteResponse = new UserDeleteResponse();
        userDeleteResponse.setLoginId(loginId);
        if (loginId == null || Strings.isNullOrEmpty(loginId)) {
            logger.error("userAccountDetailsDelete: Missing mandatory data -> {}", loginId);
            throw new SystemException(ApiErrors.MISSING_MANDATORY_FIELDS_FOR_ATTRIBUTES);
        }
        if (userAccountRepository.findByLoginId(loginId) == null) {
            logger.error("userAccountDetailsDelete: User account doesn't exist for loginId -> {}", loginId);
            throw new SystemException(ApiErrors.USER_DOESNOT_EXISTS);
        }

        if (userPersonalDetailsRepository.findByLoginId(loginId) != null) {
            logger.info("userPersonalDetailsDelete: User Personal Details Information -> {}", userPersonalDetailsRepository.findByLoginId(loginId));
            UserPersonalDetails userPersonalDetails = userPersonalDetailsRepository.findByLoginId(loginId);
                logger.info("userPersonalDetailsDelete: Inactivate for userPersonalId -> {}", userPersonalDetails.getId());
                userPersonalDetailsRepository.deleteById(userPersonalDetails.getId());
                userDeleteResponse.setPersonal_details_status(Constants.STATUS_SUCCESS);
            }

        if (userHealthDetailsRepository.findByLoginId(loginId) != null) {
            logger.info("userHealthDetailsDelete: User Personal Details Information -> {}", userHealthDetailsRepository.findByLoginId(loginId));
            UserHealthDetails userHealthDetails = userHealthDetailsRepository.findByLoginId(loginId);
            logger.info("userHealthDetailsDelete: Inactivate for userHealthId -> {}", userHealthDetails.getId());
            userDeleteResponse.setHealth_details_status(Constants.STATUS_SUCCESS);
        }
        UserAccount userAccount = userAccountRepository.findByLoginId(loginId);
        userAccountRepository.deleteById(userAccount.getId());
        logger.info("userAccountDetailsDelete: Response sent successfully for loginId -> {}", userAccountRepository.findByLoginId(loginId));
        return userDeleteResponse;
    }

    @Override
    public UserHealthProfileResponse retrieveUserHealthProfileByLoginId(String loginId) throws SystemException {
        logger.info("Request received -> {}", loginId);
        if (loginId == null) {
            logger.error("retrieveUserAccountProfileByLoginId: Missing mandatory data");
            throw new SystemException(ApiErrors.MISSING_MANDATORY_FIELDS_FOR_ATTRIBUTES);
        }
        UserAccount userAccount = userAccountRepository.findByLoginId(loginId.toLowerCase());
        if (userAccount == null) {
            logger.error("retrieveUserAccountProfileByLoginId: User account doesn't exists");
            throw new SystemException(ApiErrors.USER_DOESNOT_EXISTS);
        }
        if (userAccount != null && userAccount.getHealth_details_status().equalsIgnoreCase(Constants.STATUS_NOT_UPLOADED)) {
            logger.error("retrieveUserAccountProfileByLoginId: User account exists but not uploaded health details status");
            throw new SystemException(ApiErrors.USER_HEALTH_STATUS_DOESNOT_EXISTS);
        }

        UserHealthProfileResponse userHealthProfileResponse = new UserHealthProfileResponse();
        userHealthProfileResponse.setLoginId(loginId);
        userHealthProfileResponse.setEmailId(userAccount.getEmailId());
        userHealthProfileResponse.setAccountStatus(userAccount.getPersonal_details_status());
        userHealthProfileResponse.setUserHealthDetails(userHealthDetailsRepository.findByLoginId(loginId));
        return userHealthProfileResponse;
    }

    @Override
    public UserHealthProfileResponse retrieveUserHealthProfile(RetrieveUserProfileRequest retrieveUserProfileRequest) throws SystemException {
        logger.info("Request received -> {}", retrieveUserProfileRequest);
        if (retrieveUserProfileRequest == null || (Strings.isNullOrEmpty(retrieveUserProfileRequest.getEmailId()) && Strings.isNullOrEmpty(retrieveUserProfileRequest.getLoginId()))) {
            logger.error("retrieveUserAccountProfile: Missing mandatory data");
            throw new SystemException(ApiErrors.MISSING_MANDATORY_FIELDS_FOR_ATTRIBUTES);
        }
        if (!(Strings.isNullOrEmpty(retrieveUserProfileRequest.getEmailId())) && !(Strings.isNullOrEmpty(retrieveUserProfileRequest.getLoginId()))) {
            logger.info("retrieveUserAccountProfile: Retrieving user account details for emailId and loginId combination");
            UserAccount userAccount = userAccountRepository.findByLoginId(retrieveUserProfileRequest.getLoginId().toLowerCase());
            if (userAccount == null) {
                logger.error("retrieveUserAccountProfile: User account doesn't exists");
                throw new SystemException(ApiErrors.USER_DOESNOT_EXISTS);
            }
            if (userAccount.getEmailId().equalsIgnoreCase(retrieveUserProfileRequest.getEmailId())) {
                logger.info("retrieveUserAccountProfile: Retrieving user account details for emailId -> {} and loginId -> {}",
                        retrieveUserProfileRequest.getEmailId(), retrieveUserProfileRequest.getEmailId());
                return retrieveUserHealthProfileByLoginId(retrieveUserProfileRequest.getLoginId());
            } else {
                logger.error("retrieveUserAccountProfile: User account doesn't exists for emailId -> {} and loginId -> {}",
                        retrieveUserProfileRequest.getEmailId(), retrieveUserProfileRequest.getEmailId());
                throw new SystemException(ApiErrors.USER_DOESNOT_EXISTS);
            }
        }
        if (!(Strings.isNullOrEmpty(retrieveUserProfileRequest.getEmailId()))) {
            logger.info("retrieveUserAccountProfile: Retrieving user account details for emailId -> {}", retrieveUserProfileRequest.getEmailId());
            UserAccount userAccount = userAccountRepository.findByEmailId(retrieveUserProfileRequest.getEmailId().toLowerCase());
            if (userAccount == null) {
                logger.error("retrieveUserAccountProfile: User account doesn't exists for loginId -> {}", retrieveUserProfileRequest.getEmailId());
                throw new SystemException(ApiErrors.USER_DOESNOT_EXISTS);
            }
            logger.info("retrieveUserAccountProfile: User account exists for emailId  -> {}", retrieveUserProfileRequest.getEmailId());
            return retrieveUserHealthProfileByLoginId(userAccount.getLoginId());
        }
        if (!Strings.isNullOrEmpty(retrieveUserProfileRequest.getLoginId())) {
            logger.info("retrieveUserAccountProfile: Retrieving user account details for loginId -> {}", retrieveUserProfileRequest.getLoginId());
            return retrieveUserHealthProfileByLoginId(retrieveUserProfileRequest.getLoginId());
        }
        return null;
    }

    @Override
    public ForgotAccountResponse forgotPassword(ForgotAccountRequest forgotAccountRequest) throws SystemException {
        logger.info("Request received -> {}", forgotAccountRequest);
        if (Strings.isNullOrEmpty(forgotAccountRequest.getEmailId())) {
            logger.error("forgotPassword: Missing mandatory data");
            throw new SystemException(ApiErrors.MISSING_MANDATORY_FIELDS_FOR_ATTRIBUTES);
        }
        UserAccount userAccount = userAccountRepository.findByEmailId(forgotAccountRequest.getEmailId().toLowerCase());
        if (userAccount == null) {
            logger.error("forgotPassword: User account doesn't exists");
            throw new SystemException(ApiErrors.USER_DOESNOT_EXISTS);
        }

        logger.info("forgotPassword: Response sent successfully for emailId -> {}", forgotAccountRequest.getEmailId());
        ForgotAccountResponse forgotAccountResponse = new ForgotAccountResponse();
        forgotAccountResponse.setEmailId(forgotAccountRequest.getEmailId());
        forgotAccountResponse.setLoginId(userAccount.getLoginId());
        forgotAccountResponse.setStatus(Constants.STATUS_SUCCESS);
        return forgotAccountResponse;
    }

    @Override
    public ForgotAccountResponse forgotLoginId(ForgotAccountRequest forgotAccountRequest) throws SystemException {
        logger.info("Request received -> {}", forgotAccountRequest);
        if (Strings.isNullOrEmpty(forgotAccountRequest.getEmailId())) {
            logger.error("forgotLoginId: Missing mandatory data");
            throw new SystemException(ApiErrors.MISSING_MANDATORY_FIELDS_FOR_ATTRIBUTES);
        }
        UserAccount userAccount = userAccountRepository.findByEmailId(forgotAccountRequest.getEmailId().toLowerCase());
        if (userAccount == null) {
            logger.error("forgotLoginId: User account doesn't exists");
            throw new SystemException(ApiErrors.USER_DOESNOT_EXISTS);
        }

        logger.info("forgotLoginId: Response sent successfully for emailId -> {}", forgotAccountRequest.getEmailId());
        ForgotAccountResponse forgotAccountResponse = new ForgotAccountResponse();
        forgotAccountResponse.setEmailId(forgotAccountRequest.getEmailId());
        forgotAccountResponse.setLoginId(userAccount.getLoginId());
        forgotAccountResponse.setStatus(Constants.STATUS_SUCCESS);
        return forgotAccountResponse;
    }

    @Override
    public UpdatePasswordResponse updatePassword(UpdatePasswordRequest updatePasswordRequest) throws SystemException {
        logger.info("Request received -> {}", updatePasswordRequest);
        if (Strings.isNullOrEmpty(updatePasswordRequest.getLoginId()) || Strings.isNullOrEmpty(updatePasswordRequest.getPassword())) {
            logger.error("updatePassword: Missing mandatory data");
            throw new SystemException(ApiErrors.MISSING_MANDATORY_FIELDS_FOR_ATTRIBUTES);
        }
        UserAccount userAccount = userAccountRepository.findByLoginId(updatePasswordRequest.getLoginId().toLowerCase());
        if (userAccount == null) {
            logger.error("updatePasswordRequest: User account doesn't exists for loginId -> {}", updatePasswordRequest.getLoginId());
            throw new SystemException(ApiErrors.USER_DOESNOT_EXISTS);
        }
        userAccount.setPassword(updatePasswordRequest.getPassword());
        userAccount.setUpdatedTs(DateTimeFormatterUtil.getCurrentTimestampInUTC());
        userAccountRepository.save(userAccount);
        logger.info("updatePasswordRequest: Updated successfully");
        UpdatePasswordResponse updatePasswordResponse = new UpdatePasswordResponse();
        updatePasswordResponse.setLoginId(userAccount.getLoginId());
        updatePasswordResponse.setEmailId(userAccount.getEmailId());
        updatePasswordResponse.setStatus(Constants.STATUS_SUCCESS);
        return updatePasswordResponse;
    }

    @Override
    public UserMembershipResponse chooseMembershipPlan(UserMembershipRequest userMembershipRequest) throws SystemException {
        logger.info("Request received -> {}", userMembershipRequest);
        if (userMembershipRequest.getMembershipId() == null || Strings.isNullOrEmpty(userMembershipRequest.getLoginId())) {
            logger.error("chooseMembershipPlan: Missing mandatory data");
            throw new SystemException(ApiErrors.MISSING_MANDATORY_FIELDS_FOR_ATTRIBUTES);
        }
        UserAccount userAccount = userAccountRepository.findByLoginId(userMembershipRequest.getLoginId().toLowerCase());
        if (userAccount == null) {
            logger.error("updatePasswordRequest: User account doesn't exists for loginId -> {}", userMembershipRequest.getLoginId());
            throw new SystemException(ApiErrors.USER_DOESNOT_EXISTS);
        }

        Optional<Membership> membership = membershipRepository.findById(userMembershipRequest.getMembershipId());
        if (!membership.isPresent() || membership == null) {
            logger.error("chooseMembershipPlan: Membership not found for loginId -> {}", userMembershipRequest.getLoginId());
            throw new SystemException(ApiErrors.NO_RECORD_FOUND);
        }
        userAccount.setMembershipId(userMembershipRequest.getMembershipId());
        userAccount.setUpdatedTs(DateTimeFormatterUtil.getCurrentTimestampInUTC());
        userAccountRepository.save(userAccount);
        logger.info("updateMembershipPlan: Updated successfully");
        UserMembershipResponse userMembershipResponse = new UserMembershipResponse();
        userMembershipResponse.setLoginId(userAccount.getLoginId());
        userMembershipResponse.setEmail(userAccount.getEmailId());
        userMembershipResponse.setMembershipName(membership.get().getName());
        return userMembershipResponse;
    }

    @Override
    public List<Workout> getWorkoutsByLoginId(String loginId) throws SystemException {
        logger.info("Request received -> {}", loginId);
        if(loginId == null) {
            logger.error("getWorkoutsByLoginId: Missing mandatory data");
            throw new SystemException(ApiErrors.MISSING_MANDATORY_FIELDS_FOR_ATTRIBUTES);
        }
        UserAccount userAccount = userAccountRepository.findByLoginId(loginId.toString());
        if(userAccount == null) {
            logger.info("User doesn't exists for loginId -> {}", loginId);
            throw new SystemException(ApiErrors.USER_DOESNOT_EXISTS);
        }
        Long membershipId = userAccount.getMembershipId();
        if(membershipId == null || membershipId == 0) {
            logger.error("getWorkoutsByLoginId: User doesn't subscribed the Memebership ->{}", loginId);
            throw new SystemException(ApiErrors.NO_RECORD_FOUND);
        }
        List<Workout> workoutList = workoutRepository.workoutDetailsByMembershipId(membershipId);
       return workoutList;
    }

    @Override
    public ExerciseResponse getExerciseById(Long id) throws SystemException {
        logger.info("Request received -> {}", id);
        if(id == null) {
            logger.error("getExerciseById: Missing mandatory data");
            throw new SystemException(ApiErrors.MISSING_MANDATORY_FIELDS_FOR_ATTRIBUTES);
        }
        Exercise exercise = exerciseRepository.findById(id).orElse(null);
        if(exercise == null) {
            logger.error("getExerciseById: Exercise not found for id -> {}", id);
            throw new SystemException(ApiErrors.NO_RECORD_FOUND);
        }
        ExerciseResponse exerciseResponse = new ExerciseResponse();
        exerciseResponse.setId(exercise.getId());
        exerciseResponse.setName(exercise.getExerciseName());
        exerciseResponse.setMuscleGroup(exercise.getMuscleGroup());
        exerciseResponse.setDescription(exercise.getDescription());
        return exerciseResponse;
    }

    @Override
    public List<UserPersonalDetails> getAllUserPersonalDetails() throws SystemException {
        List<UserPersonalDetails> details = userPersonalDetailsRepository.findAll();
        System.out.println("Fetched User Details: " + details);
        return details;
    }

    @Override
    public List<UserPersonalDetails> getAllUserPersonalDetailsAfterDate(String date) throws SystemException {
        if(Strings.isNullOrEmpty(date)) {
            logger.error("getAllUserPersonalDetailsAfterDate: Missing mandatory data");
            throw new SystemException(ApiErrors.MISSING_MANDATORY_FIELDS_FOR_ATTRIBUTES);
        }
        try {
            LocalDateTime localDateTime = LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME);
            Timestamp timestamp = Timestamp.valueOf(localDateTime);
            LocalDate inputDate = timestamp.toLocalDateTime().toLocalDate();
            // Calculate yesterday's date
            //LocalDate yesterday = inputDate.minusDays(1);
            // Convert the LocalDate for yesterday to start of the day (00:00:00) and end of the day (23:59:59)
            Timestamp startOfDay = Timestamp.valueOf(inputDate.atStartOfDay()); // 00:00:00
            Timestamp endOfDay = Timestamp.valueOf(inputDate.atTime(23, 59, 59));
            if (startOfDay == null || endOfDay == null) {
                logger.error("getAllUserPersonalDetailsAfterDate: Timestamp is null");
                throw new SystemException(ApiErrors.NO_RECORD_FOUND);
            }
            List<UserPersonalDetails> userPersonalDetailsListByDate = userPersonalDetailsRepository.userPersonalDetailsByDate(startOfDay, endOfDay);
            return userPersonalDetailsListByDate;

        } catch (DateTimeParseException e) {
            logger.error("getAllUserPersonalDetailsAfterDate: Invalid dateRequest format - {}", date);
            throw new SystemException(ApiErrors.INVALID_DATE_FORMAT);
        }
    }

    @Override
    public List<UserAccount> getUserAccountsFromDate(String date) throws SystemException {
        if(Strings.isNullOrEmpty(date)) {
            logger.error("getUserAccountsFromDate: Missing mandatory data");
            throw new SystemException(ApiErrors.MISSING_MANDATORY_FIELDS_FOR_ATTRIBUTES);
        }
        try {
            LocalDateTime localDateTime = LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME);
            Timestamp timestamp = Timestamp.valueOf(localDateTime);
            LocalDate inputDate = timestamp.toLocalDateTime().toLocalDate();
            // Calculate yesterday's date
            //LocalDate yesterday = inputDate.minusDays(1);
            // Convert the LocalDate for yesterday to start of the day (00:00:00) and end of the day (23:59:59)
            Timestamp startOfDay = Timestamp.valueOf(inputDate.atStartOfDay()); // 00:00:00
            Timestamp endOfDay = Timestamp.valueOf(inputDate.atTime(23, 59, 59));
            if (startOfDay == null || endOfDay == null) {
                logger.error("getAllUserAccountDetailsAfterDate: Timestamp is null");
                throw new SystemException(ApiErrors.NO_RECORD_FOUND);
            }
            List<UserAccount> userAccountsByDate = userAccountRepository.userAccountsByDate(startOfDay, endOfDay);
            return userAccountsByDate;

        } catch (DateTimeParseException e) {
            logger.error("getAllUserAccountDetailsAfterDate: Invalid dateRequest format - {}", date);
            throw new SystemException(ApiErrors.INVALID_DATE_FORMAT);
        }

    }

    @Override
    public List<UserHealthDetails> getUserHealthDetailsByDate(String date) throws SystemException {
        if(Strings.isNullOrEmpty(date)) {
            logger.error("getUserHealthDetails: Missing mandatory data");
            throw new SystemException(ApiErrors.MISSING_MANDATORY_FIELDS_FOR_ATTRIBUTES);
        }
        try {
            LocalDateTime localDateTime = LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME);
            Timestamp timestamp = Timestamp.valueOf(localDateTime);
            LocalDate inputDate = timestamp.toLocalDateTime().toLocalDate();
            // Calculate yesterday's date
            //LocalDate yesterday = inputDate.minusDays(1);
            // Convert the LocalDate for yesterday to start of the day (00:00:00) and end of the day (23:59:59)
            Timestamp startOfDay = Timestamp.valueOf(inputDate.atStartOfDay()); // 00:00:00
            Timestamp endOfDay = Timestamp.valueOf(inputDate.atTime(23, 59, 59));
            if (startOfDay == null || endOfDay == null) {
                logger.error("getAllUserHealthDetailsAfterDate: Timestamp is null");
                throw new SystemException(ApiErrors.NO_RECORD_FOUND);
            }
            List<UserHealthDetails> userHealthDetailsListByDate = userHealthDetailsRepository.retrieveHealthDetailsFromDate(startOfDay, endOfDay);
            return userHealthDetailsListByDate;

        } catch (DateTimeParseException e) {
            logger.error("getAllUserHealthDetailsAfterDate: Invalid dateRequest format - {}", date);
            throw new SystemException(ApiErrors.INVALID_DATE_FORMAT);
        }
    }


}
