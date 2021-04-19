package com.example.clase2.controllers;

import com.example.clase2.entitys.RegionEntity;
import com.example.clase2.repositorys.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class RegionController {

    @Autowired
    RegionRepository regionRepository;

    @GetMapping("/listRegions")
    public String listRegions(Model model){
        model.addAttribute("listRegions", regionRepository.findAll());
        return "region/list";
    }

    @GetMapping("/newRegion")
    public String newRegion(){
        return "region/newRegionForm";
    }

    @PostMapping("/saveRegion")
    public String saveRegion(RegionEntity region,
                             @RequestParam("newRegion") boolean newRegion,
                             RedirectAttributes ratt){
        if(newRegion==true){
            Optional<RegionEntity> optRegion = regionRepository.findById(region.getRegionid());
            if(optRegion.isPresent()){
                ratt.addFlashAttribute("dangerMsg", "A region with "+region.getRegionid() +
                        " as Id was already created");
            }else{
                regionRepository.save(region);
                ratt.addFlashAttribute("msg", region.getRegionid() +" - "+
                        region.getRegiondescription()+"was created successfully!");
            }
        }else{
            regionRepository.save(region);
            ratt.addFlashAttribute("msg", region.getRegionid() +" - "+
                    region.getRegiondescription()+"was edited successfully!");
        }
        return "redirect:/listRegions";
    }
    
    @GetMapping("/editRegion")
    public String editRegion(@RequestParam("id") int redionId, Model model){
        Optional<RegionEntity> optRegion = regionRepository.findById(redionId);

        if(optRegion.isPresent()){
            model.addAttribute("region", optRegion.get());
            return "region/editRegion";
        }else{
            return "redirect:/listRegions";
        }
    }

    @GetMapping("/deleteRegion")
    public String deleteRegion(@RequestParam("id") int id, RedirectAttributes ratt){
        Optional<RegionEntity> optRegion = regionRepository.findById(id);
        if(optRegion.isPresent()){
            regionRepository.delete(optRegion.get());
            ratt.addFlashAttribute("dangerMsg", optRegion.get().getRegionid() +" - "+
                    optRegion.get().getRegiondescription()+"was deleted successfully!");
        }
        return "redirect:/listRegions";
    }
}
