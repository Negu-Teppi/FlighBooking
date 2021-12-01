package com.manhlee.flight_booking_online.controller;

import com.manhlee.flight_booking_online.entities.ImageEntity;
import com.manhlee.flight_booking_online.entities.PromotionEntity;
import com.manhlee.flight_booking_online.enums.PromotionStatusEnum;
import com.manhlee.flight_booking_online.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/manager/promotion")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;


    @RequestMapping("/view")
    public String viewPromotion(Model model){
        model.addAttribute("promotions", promotionService.getPromotions());
        return "manager/manage/promotion/view-promotion";
    }
    @RequestMapping("/add-promotion")
    public String addPromotion(Model model){
        model.addAttribute("promotion", new PromotionEntity());
        model.addAttribute("status", PromotionStatusEnum.values());
        model.addAttribute("action", "add");
        return "manager/manage/promotion/edit-promotion";
    }

    @RequestMapping(value = "/result", method = RequestMethod.POST)
    public String resultPromotion(@ModelAttribute("promotion") PromotionEntity promotion,
                                  MultipartFile[] files, HttpServletRequest request){

        Date date = new Date();
        List<ImageEntity> images = new ArrayList<>();
        for (int i=0; i<files.length; i++) {
            MultipartFile file = files[i];
            try {
                byte[] bytes = file.getBytes();

                ServletContext context = request.getServletContext();
                String pathUrl = context.getRealPath("/images");

                int index = pathUrl.indexOf("target");
                String pathFolder = pathUrl.substring(0, index) + "src\\main\\webapp\\resources-management\\image\\promotion\\";
                Path path= Paths.get(pathFolder+file.getOriginalFilename());
                Files.write(path, bytes);

                // get file name
                String name = file.getOriginalFilename();

                if( name == null){
                    name= "new-image" + name;
                }

                ImageEntity image = new ImageEntity();
                image.setName(name);
                image.setPromotion(promotion);
                images.add(image);


            } catch (IOException e) {
                e.printStackTrace();
            }
            promotion.setCreateDate(date);
            promotion.setImages(images);
            promotionService.save(promotion);
        }
        return "redirect:/manager/promotion/view";
    }

    @RequestMapping(value = "edit/{id}")
    public String editPromotion(Model model, @PathVariable("id") int id){

        model.addAttribute("promotion", promotionService.getPromotion(id));
        model.addAttribute("status", PromotionStatusEnum.values());
        model.addAttribute("action", "update");
        return "manager/manage/promotion/edit-promotion";
    }
}
