package com.manhlee.flight_booking_online.service;

import com.manhlee.flight_booking_online.entities.ImageEntity;
import com.manhlee.flight_booking_online.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public List<ImageEntity> getImagesAirport(int id) {
        return imageRepository.findByAirport_Id(id);
    }

    public List<ImageEntity> getImagesAircraft(int id) {
        return imageRepository.findByAircraft_Id(id);
    }

    public List<ImageEntity> getImagesPromotion(int id) {
        return imageRepository.findByPromotion_Id(id);
    }

    public List<ImageEntity> getImagesService(int id) {
        return imageRepository.findByService_Id(id);
    }

    public void delete(int id) {
        imageRepository.deleteByImageId(id);
    }
}
