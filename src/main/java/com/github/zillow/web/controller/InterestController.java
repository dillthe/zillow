package com.github.zillow.web.controller;

import com.github.zillow.repository.entity.InterestEntity;
import com.github.zillow.service.InterestService;
import com.github.zillow.web.dto.InterestBody;
import com.github.zillow.web.dto.InterestDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
public class InterestController implements ApiController{
    private final InterestService interestService;

    @Operation(summary = "관심있는 부동산 정보 추가")
    @PostMapping("/interest/add")
    public ResponseEntity<String> addToInterest(@RequestBody InterestBody interestBody){
      //  interestBody.setUserId(RequestUtil.getUserId());
        interestService.addToInterest(interestBody);
        return ResponseEntity.ok("관심 부동산에 추가되었습니다.");
    }

    @Operation(summary="사용자별 관심있는 부동산 리스트 조회")
    @GetMapping("/interest/list")
    public ResponseEntity<List<InterestEntity>> getInterestList(@RequestParam int userId){
        List<InterestEntity> interestEntityList = interestService.getInterestList(userId);
        return ResponseEntity.ok(interestEntityList);
    }

    @Operation(summary = "관심있는 부동산 정보 삭제")
    @DeleteMapping("/interest/delete/{interestId}")
    public ResponseEntity<String> deleteInterest(@PathVariable String interestId){
        interestService.deleteInterest(interestId);
        return ResponseEntity.ok("해당 부동산은 관심 부동산 리스트에서 삭제되었습니다.");
    }



}
