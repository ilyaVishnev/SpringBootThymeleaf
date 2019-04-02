package com.avito;


import com.DAO.services.*;
import com.cars_annot.Model;
import com.fasterxml.jackson.annotation.JsonView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/*import org.springframework.web.bind.annotation.GetMapping;*/


@RestController
@RequestMapping("/cars")
public class CarsController {

    @Autowired
    private ModelService modelService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private GearboxService gearboxService;
    @Autowired
    private EngineService engineService;
    @Autowired
    private CarBodyService carBodyService;
    @Autowired
    private YearService yearService;

    @JsonView(Views.Public.class)
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    protected JSONObject doGet(@RequestBody(required = false) String text) throws Exception {
        HashMap<String, String> map = new Gson().fromJson(text, new TypeToken<HashMap<String, String>>() {
        }.getType());
        String brand = map.get("selbrand");
        String model = map.get("selmodel");
        String year = map.get("selyear");
        JSONObject send = new JSONObject();
        model = modelFromBrand(model, brand) ? model : "off";
        if (!brand.equals("off")) {
            send.put("models", model.equals("off") ? modelService.findAllByBrand(brandService.getBrandById(brand)) : null);
            send.put("years", year.equals("off") ? yearService.findAll() : null);
            if (!model.equals("off")) {
                if (!year.equals("off")) {
                    send.put("gearboxes", gearboxService.findByModelAndYear(modelService.getModelById(model), yearService.findYearById(year)));
                    send.put("carBodies", carBodyService.findByModelAndYear(modelService.getModelById(model), yearService.findYearById(year)));
                    send.put("engines", engineService.findByModelAndYear(modelService.getModelById(model), yearService.findYearById(year)));
                } else {
                    send.put("gearboxes", gearboxService.findByModel(modelService.getModelById(model)));
                    send.put("carBodies", carBodyService.findByModel(modelService.getModelById(model)));
                    send.put("engines", engineService.findByModel(modelService.getModelById(model)));
                }
            }
        }
        return send;
    }

    protected boolean modelFromBrand(String model, String brand) {
        if (brand.equals("off") || model.equals("off")) {
            return true;
        }
        List<Model> models = modelService.findAllByBrand(brandService.getBrandById(brand));
        Model mymodel = modelService.getModelById(model);
        for (Model m : models) {
            if (m.equals(mymodel)) {
                return true;
            }
        }
        return false;
    }
}
