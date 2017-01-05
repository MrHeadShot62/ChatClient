package org.arcticsoft.bluebearlive.core.logic;

import org.arcticsoft.bluebearlive.core.aLogic.AErrorCore;

/**
 * Created by DmitriyRoot on 05.01.2017.
 */

public class Error extends AErrorCore {

    private final String ERRORNAME;
    private final int ERRORCODE;
    private final User ERROUSER;
    private final ErrorType ERRORTYPE;

    private String errorData;

    public Error(int errorcode, User errouser, ErrorType errortype) {
        ERRORNAME = ErrorCode.ErrorsCodeInProject.get(errorcode);
        ERRORCODE = errorcode;
        ERROUSER = errouser;
        ERRORTYPE = errortype;
    }

    public Error(int errorcode, User errouser, ErrorType errortype, String errordate) {
        this(errorcode, errouser, errortype);
        errorData = errordate;
        sendDataError();
    }

    @Override
    public int getErrorCode() {
        return ERRORCODE;
    }

    @Override
    public String getErrorCodeString() {
        return ERRORNAME;
    }

    @Override
    public void sendDataError() {
        // TODO add send error on server
    }

    @Override
    public User getUserThrowExeption() {
        return ERROUSER;
    }

    @Override
    public ErrorType getErrorType() {
        return ERRORTYPE;
    }

    @Override
    public String getErrorDate() {
        return errorData;
    }
}
