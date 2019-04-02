package com.DAO.services;

import com.DAO.repositories.GearboxRepository;
import com.cars_annot.Gearbox;
import com.cars_annot.Model;
import com.cars_annot.Year;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GearboxService {
    @Autowired
    private GearboxRepository gearboxRepository;

    public List<Gearbox> findAll() {
        return gearboxRepository.findAll();
    }

    public List<Gearbox> findByModel(Model model) {
        return gearboxRepository.findAllByModel(model);
    }

    public List<Gearbox> findByModelAndYear(Model model, Year year) {
        return gearboxRepository.findAllByModelAndYear(model, year.getYear());
    }
}
