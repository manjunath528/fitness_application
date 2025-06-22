package com.gym.app.controller;

import com.gym.app.baseframework.controller.BaseController;
import com.gym.app.baseframework.exception.BaseException;
import com.gym.app.entity.MessageCenter;
import com.gym.app.service.MessageCenterService;
import com.gym.app.service.dto.MessageCenterRequest;
import com.gym.app.service.dto.MessageCenterRetrieveRequest;
import com.gym.app.service.dto.MessageCenterUpdateRequest;
import com.gym.app.service.dto.MessageUpdateRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiRoute.BASE_MAPPING_API_V1)
@CrossOrigin(origins = "*")
public class MessageCenterController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(MessageCenterController.class);

    @Autowired
    private MessageCenterService messageCenterService;

    @PostMapping(value = ApiRoute.MESSAGE_CENTER_SEND)
    public ResponseEntity<List<MessageCenter>> sendMessage(@RequestBody List<MessageCenterRequest> messageCenterRequestList) throws BaseException {
        logger.info("sendMessage: Received");
        return new ResponseEntity<>(messageCenterService.sendMessage(messageCenterRequestList), HttpStatus.OK);
    }

    @PostMapping(value = ApiRoute.MESSAGE_CENTER_BY_LOGIN_ID_STATUS)
    public ResponseEntity<List<MessageCenter>> retrieveMessagesByLoginIdAndStatus(@RequestBody MessageCenterRetrieveRequest messageCenterRetrieveRequest) throws BaseException {
        logger.info("retrieveMessagesByLoginIdAndStatus: Received");
        return new ResponseEntity<>(messageCenterService.retrieveMessagesByLoginIdAndStatus(messageCenterRetrieveRequest), HttpStatus.OK);
    }

    @PostMapping(value = ApiRoute.MESSAGE_CENTER_BY_LOGIN_ID_METADATA_READ)
    public ResponseEntity<List<MessageCenter>> retrieveReadMessagesByLoginIdAndMetaData(@RequestBody MessageCenterRetrieveRequest messageCenterRetrieveRequest) throws BaseException {
        logger.info("retrieveReadMessagesByLoginIdAndMetaData: Received");
        return new ResponseEntity<>(messageCenterService.retrieveMessagesByLoginIdAndMetaData(messageCenterRetrieveRequest, true), HttpStatus.OK);
    }

    @PostMapping(value = ApiRoute.MESSAGE_CENTER_BY_LOGIN_ID_METADATA_UNREAD)
    public ResponseEntity<List<MessageCenter>> retrieveUnReadMessagesByLoginIdAndMetaData(@RequestBody MessageCenterRetrieveRequest messageCenterRetrieveRequest) throws BaseException {
        logger.info("retrieveUnReadMessagesByLoginIdAndMetaData: Received");
        return new ResponseEntity<>(messageCenterService.retrieveMessagesByLoginIdAndMetaData(messageCenterRetrieveRequest, false), HttpStatus.OK);
    }

    @PutMapping(value = ApiRoute.MESSAGE_CENTER_UPDATE_METADATA)
    public ResponseEntity<MessageCenter> updateMessageMetaData(@RequestBody MessageCenterUpdateRequest messageCenterUpdateRequest) throws BaseException {
        logger.info("updateMessageMetaData: Received");
        return new ResponseEntity<>(messageCenterService.updateMessageMetaData(messageCenterUpdateRequest), HttpStatus.OK);
    }

    @PutMapping(value = ApiRoute.MESSAGE_CENTER_SEND)
    public ResponseEntity<MessageCenter> updateMessage(@RequestBody MessageUpdateRequest messageUpdateRequest) throws BaseException {
        logger.info("updateMessage: Received");
        return new ResponseEntity<>(messageCenterService.updateMessage(messageUpdateRequest), HttpStatus.OK);
    }

    @GetMapping(value=ApiRoute.ALL_MESSAGE_DETAILS_BY_DATE)
    public ResponseEntity<List<MessageCenter>> getAllHealthDetailsByDate(@RequestParam String date) throws BaseException {
        logger.info("getAllHealthDetailsByDate: Received");
        return new ResponseEntity<>(messageCenterService.retrieveMessagesByDate(date), HttpStatus.OK);
    }
}