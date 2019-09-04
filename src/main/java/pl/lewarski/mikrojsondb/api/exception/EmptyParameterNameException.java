package pl.lewarski.mikrojsondb.api.exception;

import lombok.Getter;
import pl.lewarski.mikrojsondb.util.ApiErrorMessage;

@Getter
public class EmptyParameterNameException extends RuntimeException {

    private ApiErrorMessage apiErrorMessage = ApiErrorMessage.INVALID_PARAM_NAME;

    private EmptyParameterNameException() {
    }

    public static EmptyParameterNameException create() {
        return new EmptyParameterNameException();
    }

    public String getCode() {
        return apiErrorMessage.getCode();
    }

    public String getDesc() {
        return apiErrorMessage.getDesc();
    }
}
