package mario.ferketic.master.controller.details;


import lombok.RequiredArgsConstructor;
import mario.ferketic.master.dto.users.details.CityDto;
import mario.ferketic.master.mapper.CityMapper;
import mario.ferketic.master.service.CityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cities")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;
    private final CityMapper cityMapper;

    @GetMapping
    public ResponseEntity<List<CityDto>> findAll() {
        return ResponseEntity.ok().body(cityService.getAllCities().stream()
                .map(cityMapper::toDto)
                .collect(Collectors.toList()));
    }
}
