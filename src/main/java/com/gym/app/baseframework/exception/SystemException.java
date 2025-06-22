package com.gym.app.baseframework.exception;

import com.gym.app.baseframework.exception.enums.ApiErrors;

/**
 * Created by Manjunath Reddy
 */
public class SystemException extends BaseException {
    public SystemException(ApiErrors apiErrorsEnum) {
        super(apiErrorsEnum.getHttpStatus(), apiErrorsEnum.getErrorCode(), apiErrorsEnum.getErrorDescription());
    }

}
