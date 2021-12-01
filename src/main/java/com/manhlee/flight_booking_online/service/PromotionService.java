package com.manhlee.flight_booking_online.service;

import com.manhlee.flight_booking_online.entities.PromotionEntity;
import com.manhlee.flight_booking_online.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PromotionService {

    @Autowired
    private PromotionRepository promotionRepository;

    public List<PromotionEntity> getPromotions(){
        return promotionRepository.getPromotions();
    }

    public PromotionEntity getPromotion(int id){
        Optional<PromotionEntity> optional = promotionRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    public void save(PromotionEntity promotionEntity){
        promotionRepository.save(promotionEntity);
    }
}
