package org.arcticsoft.bluebearlive.core.logic;

/**
 * Created by DmitriyRoot on 05.01.2017.
 */

public class Error{

    private final String ERRORNAME;
    private final int ERRORCODE;
    private final User ERROUSER;
    private final ErrorType ERRORTYPE;

    private String errorData;

    public Error(int errorcode, User errouser, ErrorType errortype) {
        ERRORNAME = ErrorCode.getInstance().ErrorsCodeInProject.get(errorcode);
        ERRORCODE = errorcode;
        ERROUSER = errouser;
        ERRORTYPE = errortype;
    }

    public Error(int errorcode, User errouser, ErrorType errortype, String errordate) {
        this(errorcode, errouser, errortype);
        errorData = errordate;
        sendDataError();
    }

    public int getErrorCode() {
        return ERRORCODE;
    }

    public String getErrorCodeString() {
        return ERRORNAME;
    }

    public void sendDataError() {
        // TODO add send error on server
    }

    public User getUserThrowExeption() {
        return ERROUSER;
    }

    public ErrorType getErrorType() {
        return ERRORTYPE;
    }

    public String getErrorDate() {
        return errorData;
    }
}
