package pl.lewarski.mikrojsondb.util;

import lombok.Getter;

@Getter
public enum ApiErrorMessage {

    INTERNAL_SERVER_ERROR("000", "Wystąpił nieoczekiwany błąd servera."),
    INVALID_PARAM_NAME("001", "Niepoprawne dane wejścniowe. Pole \"name\" nie może być puste"),
    INVALID_REQUEST("002", "Niepoprawne dane wejściowe.");

    private String code;
    private String desc;


    ApiErrorMessage(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
