package com.gym.app.service;

import com.gym.app.baseframework.exception.SystemException;
import com.gym.app.entity.MessageCenter;
import com.gym.app.service.dto.MessageCenterRequest;
import com.gym.app.service.dto.MessageCenterRetrieveRequest;
import com.gym.app.service.dto.MessageCenterUpdateRequest;
import com.gym.app.service.dto.MessageUpdateRequest;

import java.util.List;

public interface MessageCenterService {

    List<MessageCenter> sendMessage(List<MessageCenterRequest> messageCenterRequestList) throws SystemException;

    List<MessageCenter> triggerMessage(String source, String targetLoginId, String message, Long packDetailsId) throws SystemException;

    List<MessageCenter> retrieveMessagesByLoginIdAndStatus(MessageCenterRetrieveRequest messageCenterRetrieveRequest) throws SystemException;

    List<MessageCenter> retrieveMessagesByLoginIdAndMetaData(MessageCenterRetrieveRequest messageCenterRetrieveRequest, boolean onlyReadMessagesFlag) throws SystemException;

    MessageCenter updateMessageMetaData(MessageCenterUpdateRequest messageCenterUpdateRequest) throws SystemException;

    MessageCenter updateMessage(MessageUpdateRequest messageUpdateRequest) throws SystemException;

    List<MessageCenter> retrieveMessagesByDate(String date) throws SystemException;
}
