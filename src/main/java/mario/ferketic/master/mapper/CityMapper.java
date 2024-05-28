package mario.ferketic.master.mapper;

import mario.ferketic.master.dto.users.details.CityDto;
import mario.ferketic.master.entity.users.details.City;
import org.springframework.stereotype.Component;

@Component
public class CityMapper implements GenericMapper<City, CityDto> {
    @Override
    public City toEntity(CityDto dto) {
        return new City(dto.getPostalcode(), dto.getName());
    }

    @Override
    public CityDto toDto(City entity) {
        return new CityDto(entity.getPostalcode(), entity.getName());
    }
}
