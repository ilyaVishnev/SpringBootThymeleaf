package com.DAO.services;

import com.DAO.repositories.EngineRepository;
import com.cars_annot.Engine;
import com.cars_annot.Model;
import com.cars_annot.Year;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EngineService {

    @Autowired
    private EngineRepository engineRepository;

    public List<Engine> findAll(){
        return engineRepository.findAll();
    }

    public List<Engine> findByModel(Model model){return engineRepository.findAllByModel(model);}

    public List<Engine> findByModelAndYear(Model model, Year year) {
        return engineRepository.findAllByModelAndYear(model, year.getYear());
    }
}
