package com.example.clase2.controllers;

import com.example.clase2.entitys.TerritorieEntity;
import com.example.clase2.repositorys.RegionRepository;
import com.example.clase2.repositorys.TerritorieRepository;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class TerritorieController {

    @Autowired
    TerritorieRepository territorieRepository;

    @Autowired
    RegionRepository regionRepository;

    @GetMapping("/listTerritories")
    public String listTerritories(Model model) {
        model.addAttribute("listTerritories", territorieRepository.findAll());
        return "listTerritories";
    }

    @GetMapping("/newTerritorie")
    public String newTerritorie(Model model){
        model.addAttribute("listRegions", regionRepository.findAll());
        return "territorieForm";
    }

    @PostMapping("/saveTerritorie")
    public String saveTerritorie(TerritorieEntity territory,
                                 RedirectAttributes ratt,
                                 @RequestParam("newTerritory") boolean newTerritory){
        if(newTerritory==true){
            Optional<TerritorieEntity> optTerritory = territorieRepository.findById(territory.getTerritoryid());
            if(optTerritory.isPresent()){
                ratt.addFlashAttribute("dangerMsg","A territory with "+
                        territory.getTerritoryid()+" as Id was already created!");
            }else{
                territorieRepository.save(territory);
                ratt.addFlashAttribute("msg",
                        territory.getTerritoryid()+" - "+ territory.getTerritorydescription() +
                                " was added succesfully!");
            }
        }else{
            territorieRepository.save(territory);
            ratt.addFlashAttribute("msg",
                    territory.getTerritoryid()+" - "+ territory.getTerritorydescription() +" was edited succesfully!");
        }
        return "redirect:/listTerritories";
    }

    @GetMapping("/editTerritorie")
    public String editTerritorie(@RequestParam("id") String id, Model model) {
        Optional<TerritorieEntity> territory = territorieRepository.findById(id);
        if(territory.isPresent()){
            model.addAttribute("territory", territory.get());
            model.addAttribute("listRegions", regionRepository.findAll());
            return "editTerritory";
        }else{
            return "redirect:/listTerritories";
        }
    }

    @GetMapping("/deleteTerritorie")
    public String deleteTerritorie(@RequestParam("id") String id, RedirectAttributes ratt) {
        Optional<TerritorieEntity> territory = territorieRepository.findById(id);
        if(territory.isPresent()){
            territorieRepository.deleteById(id);
            ratt.addFlashAttribute("dangerMsg",
                    territory.get().getTerritoryid()+" - "+ territory.get().getTerritorydescription()+" was deleted");
        }
        return "redirect:/listTerritories";
    }

    @PostMapping("/searhSomething")
    public String searchText(@RequestParam("textSearch") String textSearch, Model model){
        //model.addAttribute("listTerritories", territorieRepository.buscarDescription2(textSearch));
        model.addAttribute("listTerritories", territorieRepository.findByTerritorydescription(textSearch));
        model.addAttribute("textSearch", textSearch);
        return "listTerritories";
    }

}
