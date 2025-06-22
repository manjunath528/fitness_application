package com.gym.app.service.impl;

import com.google.common.base.Strings;
import com.gym.app.baseframework.exception.SystemException;
import com.gym.app.baseframework.exception.enums.ApiErrors;
import com.gym.app.common.Constants;
import com.gym.app.common.DateTimeFormatterUtil;
import com.gym.app.entity.MessageCenter;
import com.gym.app.repository.MessageCenterRepository;
import com.gym.app.service.MessageCenterService;
import com.gym.app.service.dto.MessageCenterRequest;
import com.gym.app.service.dto.MessageCenterRetrieveRequest;
import com.gym.app.service.dto.MessageCenterUpdateRequest;
import com.gym.app.service.dto.MessageUpdateRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MessageCenterServiceImpl implements MessageCenterService {

    private static final Logger logger = LoggerFactory.getLogger(MessageCenterServiceImpl.class);

    @Autowired
    MessageCenterRepository messageCenterRepository;

    @Override
    public List<MessageCenter> sendMessage(List<MessageCenterRequest> messageCenterRequestList) throws SystemException {
        logger.info("sendMessage: Request -> {}", messageCenterRequestList);
        List<MessageCenter> messageCenterList = new ArrayList<>();
        for (MessageCenterRequest messageCenterRequest : messageCenterRequestList) {
            if (Strings.isNullOrEmpty(messageCenterRequest.getTargetLoginId()) || Strings.isNullOrEmpty(messageCenterRequest.getMessage())) {
                logger.error("sendMessage: Missing mandatory data for {}", messageCenterRequest);
                throw new SystemException(ApiErrors.MISSING_MANDATORY_FIELDS_FOR_ATTRIBUTES);
            }
            MessageCenter messageCenter = new MessageCenter();
            messageCenter.setTargetLoginId(messageCenterRequest.getTargetLoginId());
            messageCenter.setSourceLoginId(messageCenterRequest.getSourceLoginId());
            messageCenter.setMessage(messageCenterRequest.getMessage());
            messageCenter.setIsRead(Constants.STATUS_NO);
            messageCenter.setStatus(Constants.STATUS_ACTIVE);
            messageCenter.setCreatedTs(DateTimeFormatterUtil.getCurrentTimestampInUTC());
            messageCenter.setUpdatedTs(DateTimeFormatterUtil.getCurrentTimestampInUTC());
            messageCenterRepository.save(messageCenter);
            logger.info("sendMessage: Response sent successfully");
            messageCenterList.add(messageCenter);
        }
        return messageCenterList;
    }

    @Override
    public List<MessageCenter> triggerMessage(String sourceLoginId, String targetLoginId, String message, Long packDetailsId) throws SystemException {
        List<MessageCenterRequest> messageCenterRequestList = new ArrayList<>();
        MessageCenterRequest messageCenterRequest = new MessageCenterRequest();
        messageCenterRequest.setSourceLoginId(sourceLoginId);
        messageCenterRequest.setTargetLoginId(targetLoginId);
        messageCenterRequest.setMessage(message);
        messageCenterRequestList.add(messageCenterRequest);
        return sendMessage(messageCenterRequestList);
    }

    @Override
    public List<MessageCenter> retrieveMessagesByLoginIdAndStatus(MessageCenterRetrieveRequest messageCenterRetrieveRequest) throws SystemException {
        logger.info("retrieveMessagesByLoginIdAndStatus: Request -> {}", messageCenterRetrieveRequest);
        if (Strings.isNullOrEmpty(messageCenterRetrieveRequest.getLoginId())) {
            logger.error("retrieveMessagesByLoginIdAndStatus: Missing mandatory data");
            throw new SystemException(ApiErrors.MISSING_MANDATORY_FIELDS_FOR_ATTRIBUTES);
        }
        List<String> statusList = new ArrayList<>();
        if (!Strings.isNullOrEmpty(messageCenterRetrieveRequest.getStatus())) {
            statusList.add(messageCenterRetrieveRequest.getStatus().toLowerCase());
        } else {
            statusList.add(Constants.STATUS_ACTIVE.toLowerCase());
            statusList.add(Constants.STATUS_INACTIVE.toLowerCase());
            statusList.add(Constants.STATUS_EXPIRED.toLowerCase());
        }

        List<MessageCenter> messageCenterList = messageCenterRepository.retrieveMessagesByLoginIdAndStatus(messageCenterRetrieveRequest.getLoginId().toLowerCase(), statusList);
        logger.info("retrieveMessagesByLoginIdAndStatus: Response sent successfully");
        return messageCenterList;
    }
    @Override
    public List<MessageCenter> retrieveMessagesByLoginIdAndMetaData(MessageCenterRetrieveRequest messageCenterRetrieveRequest, boolean onlyReadMessagesFlag) throws SystemException {
        logger.info("retrieveMessagesByLoginIdAndMetaData: Request -> {}, onlyReadMessagesFlag -> {}", messageCenterRetrieveRequest, onlyReadMessagesFlag);
        if (Strings.isNullOrEmpty(messageCenterRetrieveRequest.getLoginId())) {
            logger.error("retrieveMessagesByLoginIdAndMetaData: Missing mandatory data");
            throw new SystemException(ApiErrors.MISSING_MANDATORY_FIELDS_FOR_ATTRIBUTES);
        }
        List<String> statusList = new ArrayList<>();
        if (!Strings.isNullOrEmpty(messageCenterRetrieveRequest.getStatus())) {
            statusList.add(messageCenterRetrieveRequest.getStatus().toLowerCase());
        } else {
            statusList.add(Constants.STATUS_ACTIVE.toLowerCase());
            statusList.add(Constants.STATUS_INACTIVE.toLowerCase());
            statusList.add(Constants.STATUS_EXPIRED.toLowerCase());
        }
        String onlyReadMessages = Constants.STATUS_NO;
        if (onlyReadMessagesFlag) {
            onlyReadMessages = Constants.STATUS_YES;
        }
        List<MessageCenter> messageCenterList = messageCenterRepository.retrieveMessagesByLoginIdAndMetaData(messageCenterRetrieveRequest.getLoginId().toLowerCase(), statusList, onlyReadMessages.toLowerCase());
        logger.info("retrieveMessagesByLoginIdAndMetaData: Response sent successfully");
        return messageCenterList;
    }

    @Override
    public MessageCenter updateMessageMetaData(MessageCenterUpdateRequest messageCenterUpdateRequest) throws SystemException {
        logger.info("updateMessageMetaData: Request -> {}", messageCenterUpdateRequest);
        if ((messageCenterUpdateRequest.getMessageCenterId() == 0) || Strings.isNullOrEmpty(messageCenterUpdateRequest.getLoginId())) {
            logger.error("updateMessageMetaData: Missing mandatory data");
            throw new SystemException(ApiErrors.MISSING_MANDATORY_FIELDS_FOR_ATTRIBUTES);
        }
        String messageAcceptanceStatus = Constants.STATUS_ACCEPTED;
        if (!Strings.isNullOrEmpty(messageCenterUpdateRequest.getMessageAcceptanceStatus())) {
            messageAcceptanceStatus = messageCenterUpdateRequest.getMessageAcceptanceStatus();
        }
        Optional<MessageCenter> messageCenterOptional = messageCenterRepository.findById(messageCenterUpdateRequest.getMessageCenterId());
        if (messageCenterOptional == null || !messageCenterOptional.isPresent()) {
            logger.error("updateMessageMetaData: Message center details doesn't exists for id ->{}", messageCenterUpdateRequest.getMessageCenterId());
            throw new SystemException(ApiErrors.NO_RECORD_FOUND);
        }
        MessageCenter messageCenter = messageCenterOptional.get();
        if (messageCenterUpdateRequest.getIsRead() != null) {
            messageCenter.setIsRead(messageCenterUpdateRequest.getIsRead());
        } else {
            messageCenter.setIsRead(Constants.STATUS_NO);
        }
        if (!Strings.isNullOrEmpty(messageCenterUpdateRequest.getStatus())) {
            messageCenter.setSourceLoginId(messageCenterUpdateRequest.getLoginId());
        }
        messageCenter.setMessageAcceptanceStatus(messageAcceptanceStatus);
        messageCenter.setUpdatedTs(DateTimeFormatterUtil.getCurrentTimestampInUTC());
        messageCenterRepository.save(messageCenter);
        logger.info("updateMessageMetaData: Response sent successfully");
        return messageCenter;
    }

    @Override
    public MessageCenter updateMessage(MessageUpdateRequest messageUpdateRequest) throws SystemException {
        logger.info("updateMessage: Request -> {}", messageUpdateRequest);
        if(messageUpdateRequest.getMessageId() == null || Strings.isNullOrEmpty(messageUpdateRequest.getLoginId())) {
            logger.error("updateMessage: Missing mandatory data");
            throw new SystemException(ApiErrors.MISSING_MANDATORY_FIELDS_FOR_ATTRIBUTES);
        }
        Optional<MessageCenter> messageCenterOptional = messageCenterRepository.findById(messageUpdateRequest.getMessageId());
        if(messageCenterOptional == null || !messageCenterOptional.isPresent()) {
            logger.error("updateMessage: Message center doesn't exists for id ->{}", messageUpdateRequest.getMessageId());
            throw new SystemException(ApiErrors.NO_RECORD_FOUND);
        }
        if(!messageCenterOptional.get().getSourceLoginId().equals(messageUpdateRequest.getLoginId())) {
            logger.error("updation not possible ->{}", messageUpdateRequest.getLoginId());
            throw new SystemException(ApiErrors.NOT_POSSIBLE_FOR_USER);
        }
        MessageCenter messageCenter = messageCenterOptional.get();
        if(messageCenter.getIsRead().equals(Constants.STATUS_YES)) {
            logger.error("Message is already read -> {}", messageUpdateRequest.getMessageId());
            throw new SystemException(ApiErrors.MESSAGE_ALREADY_READ);
        }
        messageCenter.setMessage(messageUpdateRequest.getMessage());
        messageCenter.setUpdatedTs(DateTimeFormatterUtil.getCurrentTimestampInUTC());
        messageCenterRepository.save(messageCenter);
        logger.info("updateMessage: Response sent successfully");
        return messageCenter;
    }

    @Override
    public List<MessageCenter> retrieveMessagesByDate(String date) throws SystemException {
        if(Strings.isNullOrEmpty(date)) {
            logger.error("retrieveMessagesByDate: Missing mandatory data");
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
            List<MessageCenter> messageCenterListByDate = messageCenterRepository.messagesByDate(startOfDay, endOfDay);
            return messageCenterListByDate;
        }
        catch (DateTimeParseException e) {
            logger.error("getAllUserPersonalDetailsAfterDate: ", e);
            throw new SystemException(ApiErrors.NO_RECORD_FOUND);
        }
    }
}
