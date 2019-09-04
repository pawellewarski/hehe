package pl.lewarski.mikrojsondb.api;

import lombok.Getter;
import pl.lewarski.mikrojsondb.util.ApiErrorMessage;
import pl.lewarski.mikrojsondb.api.exception.EmptyParameterNameException;

@Getter
public class ApiError {

    private String code;
    private String desc;

    private ApiError() {
    }

    private ApiError(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static ApiError fromException(EmptyParameterNameException exception) {
        return new ApiError(exception.getCode(), exception.getDesc());
    }

    public static ApiError fromErrorMessage(ApiErrorMessage apiErrorMessage) {
        return new ApiError(apiErrorMessage.getCode(), apiErrorMessage.getDesc());
    }
}
