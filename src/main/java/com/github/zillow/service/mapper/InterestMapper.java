package com.github.zillow.service.mapper;

import com.github.zillow.repository.entity.InterestEntity;
import com.github.zillow.web.dto.InterestBody;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InterestMapper {
    InterestMapper INSTANCE = Mappers.getMapper(InterestMapper.class);
    @Mapping(target="userEntity.userId", source="interestBody.userId")
    @Mapping(target="listingEntity.listingId", source="interestBody.listingId")
    InterestEntity idAndInterestBodyToInterestEntity(Integer id, InterestBody interestBody);

//    @Mapping(target="userId", source="userEntity.userId")
//    InterestDTO interestEntityToInterestDTO(InterestEntity interestEntity);

}
