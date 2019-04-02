package com.avito;

import com.DAO.services.CarService;
import com.DAO.services.HolderService;
import com.cars_annot.Car;
import com.cars_annot.Holder;
import com.cars_annot.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/desc")
public class DescriptionController {

    @Autowired
    CarService carService;

    @Autowired
    private HolderService holderService;

    private Car car;

    @RequestMapping(method = RequestMethod.GET)
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    protected ModelAndView getDesc(@RequestParam(required = false, name = "carId") String carId, HttpSession session) throws Exception {
        final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        Holder holder = holderService.findByLogin(currentUserName);
        session.setAttribute("user", holder);
        session.setAttribute("admin", isAdmin(holder.getRoles()));
        car = carService.findById(Integer.parseInt(carId));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("description");
        modelAndView.addObject("car", car);
        return modelAndView;
    }

    private Boolean isAdmin(List<Role> roles) {
        Boolean admin = false;
        for (Role role : roles) {
            if (role.getRole().equals("ROLE_ADMIN")) {
                admin = true;
            }
        }
        return admin;
    }
}
