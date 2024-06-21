package com.github.zillow.service;

import com.github.zillow.repository.entity.InterestEntity;
import com.github.zillow.repository.entity.ListingEntity;
import com.github.zillow.repository.entity.UserEntity;
import com.github.zillow.repository.interest.InterestRepository;
import com.github.zillow.repository.listing.ListingRepository;
import com.github.zillow.repository.user.UserRepository;
import com.github.zillow.service.exception.DatabaseException;
import com.github.zillow.service.exception.InvalidValueException;
import com.github.zillow.service.exception.NotAcceptException;
import com.github.zillow.service.exception.NotFoundException;
import com.github.zillow.service.mapper.InterestMapper;
import com.github.zillow.web.dto.InterestBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class InterestService {

    private final ListingRepository listingRepository;
    private final InterestRepository interestRepository;
    private final UserRepository userRepository;

    public Integer addToInterest(InterestBody interestBody) {
        ListingEntity listingEntity = listingRepository.findById(interestBody.getListingId())
                .orElseThrow(() -> new NotFoundException("부동산 정보를 찾을 수 없습니다."));
        InterestEntity interestEntity = InterestMapper.INSTANCE.idAndInterestBodyToInterestEntity(null,interestBody);
        interestEntity.setAddress(listingEntity.getAddress());
        interestEntity.setCity(listingEntity.getCity());
        interestEntity.setState(listingEntity.getState());
        interestEntity.setBedrooms(listingEntity.getBedrooms());
        interestEntity.setBathrooms(listingEntity.getBathrooms());
        interestEntity.setPrice(listingEntity.getPrice());
        interestEntity.setZipcode(listingEntity.getZipcode());
        interestEntity.setSqft(listingEntity.getSqft());
        interestEntity.setHomeStatus(listingEntity.getHomeStatus());
        interestEntity.setHomeType(listingEntity.getHomeType());

       InterestEntity interestEntityCreated;
        try {
            interestEntityCreated = interestRepository.save(interestEntity);
        } //여기서 왜 에러문 메세지가 출력이 안되고,2024-04-16T15:42:27.853-04:00 ERROR 33048 --- [nio-8080-exec-1] o.h.engine.jdbc.spi.SqlExceptionHelper   : Duplicate entry '1-10213017' for key 'interest.user_id 이런식으로 에러메세지만 뜬다.
        catch (DataIntegrityViolationException e) {
            //e.printStackTrace();
            throw new NotAcceptException("관심 부동산 리스트에 이미 저장되어 있습니다.");
        } catch (RuntimeException e) {
            throw new InvalidValueException("관심 부동산 저장하는 도중 예기치 않은 오류가 발생했습니다.");
        }
            return interestEntityCreated.getInterestId();
    }

    @Transactional
    public List<InterestEntity> getInterestList(int userId){
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("부동산 정보를 찾을 수 없습니다."));
        List<InterestEntity> interestEntityList = interestRepository.findByUserEntity(userEntity);
        return interestEntityList;
    }


    public void deleteInterest(String interestId) {
       try{
         Integer interestIdInt = Integer.valueOf(interestId);
         interestRepository.deleteById(interestIdInt);
       }catch (NumberFormatException e) {
           throw new InvalidValueException("Id 형식이 올바르지 않습니다.");
       } catch (DataAccessException e) {
           throw new DatabaseException("데이터베이스 오류가 발생했습니다. 다시 시도해 주세요.");
       }

//       catch (RuntimeException e) {
//            throw new InvalidValueException("Id 형식이 올바르지 않습니다.");
//       }
    }


}
