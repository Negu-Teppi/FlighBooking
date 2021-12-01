package com.manhlee.flight_booking_online.controller;

import com.manhlee.flight_booking_online.entities.ImageEntity;
import com.manhlee.flight_booking_online.entities.ServiceEntity;
import com.manhlee.flight_booking_online.service.Services;
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
import java.util.List;

@Controller
@RequestMapping("/manager/service")
public class ServiceController {

    @Autowired
    private Services services;

    @RequestMapping("/view")
    public String viewService(Model model){
        model.addAttribute("services", services.getServices());
        return "manager/manage/service/view-service";
    }
    @RequestMapping("/add-service")
    public String addService(Model model){
        model.addAttribute("service", new ServiceEntity());
        model.addAttribute("action", "add");
        return "manager/manage/service/edit-service";
    }

    @RequestMapping(value = "/result", method = RequestMethod.POST)
    public String resultService(@ModelAttribute("service") ServiceEntity service,
                                  MultipartFile[] files, HttpServletRequest request){

        List<ImageEntity> images = new ArrayList<>();
        for (int i=0; i<files.length; i++) {
            MultipartFile file = files[i];
            try {
                byte[] bytes = file.getBytes();

                ServletContext context = request.getServletContext();
                String pathUrl = context.getRealPath("/images");

                int index = pathUrl.indexOf("target");
                String pathFolder = pathUrl.substring(0, index) + "src\\main\\webapp\\resources-management\\image\\service\\";
                Path path= Paths.get(pathFolder+file.getOriginalFilename());
                Files.write(path, bytes);

                // get file name
                String name = file.getOriginalFilename();

                if( name == null){
                    name= "new-image" + name;
                }

                ImageEntity image = new ImageEntity();
                image.setName(name);
                image.setService(service);
                images.add(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
            service.setImages(images);
            services.save(service);
        }
        return "redirect:/manager/service/view";
    }

    @RequestMapping(value = "edit/{id}")
    public String editService(Model model, @PathVariable("id") int id){

        model.addAttribute("service", services.getService(id));
        model.addAttribute("action", "update");
        return "manager/manage/service/edit-service";
    }
}
