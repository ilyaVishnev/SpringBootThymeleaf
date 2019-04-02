package com.DAO.services;

import com.DAO.repositories.CarBodyRepository;
import com.cars_annot.CarBody;
import com.cars_annot.Model;
import com.cars_annot.Year;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarBodyService {
    @Autowired
    private CarBodyRepository carBodyRepository;

    public List<CarBody> findAll(){
        return carBodyRepository.findAll();
    }

    public List<CarBody> findByModel(Model model){return carBodyRepository.findAllByModel(model);}

    public List<CarBody> findByModelAndYear(Model model, Year year) {
        return carBodyRepository.findAllByModelAndYear(model, year.getYear());
    }
}
