package mario.ferketic.master.service.impl;

import lombok.RequiredArgsConstructor;
import mario.ferketic.master.entity.users.details.City;
import mario.ferketic.master.repository.users.details.CityRepository;
import mario.ferketic.master.service.CityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    @Override
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

}
