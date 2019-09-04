package pl.lewarski.mikrojsondb.api.response;

import lombok.Builder;
import lombok.Getter;
import pl.lewarski.mikrojsondb.api.dto.NameDto;

import java.util.List;

@Getter
@Builder
public class ApiResponse {

    private List<NameDto> result;
}
