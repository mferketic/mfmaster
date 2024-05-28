package mario.ferketic.master.mapper;


import mario.ferketic.master.dto.Dto;
import mario.ferketic.master.entity.MyEntity;


public interface GenericMapper<E extends MyEntity, D extends Dto> {

    E toEntity(D dto);

    D toDto(E entity);
}
