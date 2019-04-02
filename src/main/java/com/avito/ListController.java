package com.avito;

import com.DAO.services.BrandService;
import com.DAO.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListController {


    @Autowired
    private CarService carService;

    @Autowired
    private BrandService brandService;

    @GetMapping(value = {"/", "/list"})
    protected ModelAndView sendToList(@RequestParam(required = false, name = "brands") String brands, @RequestParam(required = false, name = "photo") String photo, @RequestParam(required = false, name = "today") String today) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("list");
        modelAndView.addObject("brands", brandService.findAll());
        modelAndView.addObject("selbrand", brands != null && !brands.equals("off") ? Integer.parseInt(brands) : null);
        modelAndView.addObject("photo", photo != null && !photo.equals("off") ? "checked" : null);
        modelAndView.addObject("today", today != null && !today.equals("off") ? "checked" : null);
        String filter = "nothing";
        filter += brands != null && !brands.equals("off") ? "brand" : "";
        filter += photo != null && !photo.equals("off") ? "photo" : "";
        filter += today != null && !today.equals("off") ? "today" : "";
        return getModelAndView(modelAndView, filter, brands);
    }

    public ModelAndView getModelAndView(ModelAndView modelAndView, String filter, String brands) {
        switch (filter) {
            case ("nothing"):
                modelAndView.addObject("cars", carService.findAll());
                break;
            case ("nothingbrandphototoday"):
                modelAndView.addObject("cars", carService.findAllByDateAndPhotoAndBrand(Integer.parseInt(brands)));
                break;
            case ("nothingbrandphoto"):
                modelAndView.addObject("cars", carService.findAllByBrandAndPhoto(Integer.parseInt(brands)));
                break;
            case ("nothingbrandtoday"):
                modelAndView.addObject("cars", carService.findAllByBrandAndDate(Integer.parseInt(brands)));
                break;
            case ("nothingphototoday"):
                modelAndView.addObject("cars", carService.findAllByDateAndPhoto());
                break;
            case ("nothingbrand"):
                modelAndView.addObject("cars", carService.findAllByBrand(Integer.parseInt(brands)));
                break;
            case ("nothingphoto"):
                modelAndView.addObject("cars", carService.findAllByPhoto());
                break;
            case ("nothingtoday"):
                modelAndView.addObject("cars", carService.findAllByDate());
                break;
        }
        return modelAndView;
    }
}
