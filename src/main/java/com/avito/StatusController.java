package com.avito;

import com.DAO.services.CarService;
import com.cars_annot.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/status")
public class StatusController {

    @Autowired
    CarService carService;

    @RequestMapping(method = RequestMethod.POST)
    protected ModelAndView setStatus(@RequestParam(required = false, name = "id") String id, @RequestParam(required = false, name = "status") String status) {
        final Car car = carService.findById(Integer.parseInt(id));
        car.setStatus(Boolean.parseBoolean(status));
        carService.save(car);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("description");
        modelAndView.addObject("car", car);
        return modelAndView;
    }
}
