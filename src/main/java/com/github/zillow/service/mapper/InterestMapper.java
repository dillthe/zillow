package com.github.zillow.service.mapper;

import com.github.zillow.repository.entity.InterestEntity;
import com.github.zillow.web.dto.InterestBody;
import com.github.zillow.web.dto.InterestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InterestMapper {
    InterestMapper INSTANCE = Mappers.getMapper(InterestMapper.class);
    @Mapping(target="memberEntity.memberId", source="interestBody.memberId")
    @Mapping(target="listingEntity.listingId", source="interestBody.listingId")
    InterestEntity idAndInterestBodyToInterestEntity(Integer id, InterestBody interestBody);

//    @Mapping(target="memberId", source="memberEntity.memberId")
//    InterestDTO interestEntityToInterestDTO(InterestEntity interestEntity);

}
