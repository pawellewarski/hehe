package pl.lewarski.mikrojsondb.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.lewarski.mikrojsondb.api.dto.NameDto;
import pl.lewarski.mikrojsondb.api.dto.ResponseDto;
import pl.lewarski.mikrojsondb.api.exception.EmptyParameterNameException;
import pl.lewarski.mikrojsondb.db.entity.NameEntity;
import pl.lewarski.mikrojsondb.db.repository.NameRepository;
import pl.lewarski.mikrojsondb.util.Mappings;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;

@Slf4j
@RestController
public class ApiController {

    private NameRepository nameRepository;

    @GetMapping(Mappings.LIST_ALL_NAMES)
    public ResponseEntity<ResponseDto> listAll() {
        log.info("GET /{}", Mappings.LIST_ALL_NAMES);
        List<NameDto> nameDtoList = new ArrayList<>();
        nameRepository.findAll().forEach(e ->
                nameDtoList.add(NameDto.fromEntity(e)));
        return ResponseEntity.ok()
                .body(ResponseDto.builder().result(nameDtoList).build());
    }

    @PostMapping(Mappings.ADD_NEW_NAME)
    public ResponseEntity<NameDto> addNewName(@RequestBody NameDto nameDto) {
        log.info("POST /{}, content: {}", Mappings.ADD_NEW_NAME, nameDto.toString());
        if (isEmpty(nameDto.getName())) {
            throw EmptyParameterNameException.create();
        }
        NameEntity nameEntity = new NameEntity();
        nameEntity.setName(nameDto.getName());

        nameRepository.save(nameEntity);
        return ResponseEntity.ok()
                .body(NameDto.fromEntity(nameEntity));
    }

    @Autowired
    public void setNameRepository(NameRepository nameRepository) {
        this.nameRepository = nameRepository;
    }
}
