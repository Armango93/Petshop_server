package com.petshop.petshop.controller;

import com.petshop.petshop.model.Pet;
import com.petshop.petshop.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor (onConstructor = @_(@Autowired))
public class MainController {
    @Autowired
    PetService petService;

//    @GetMapping("/")
//    public String main(Model model){
//        Pet pet = petService.getPet();
//        model.addAttribute("petObject", "new Pet()");
//        model.addAttribute("pet", petService.getPet());
//        System.out.println(petService.getPet());
//        return "index";
//    }

    @PostMapping("/add")
    public String save(@ModelAttribute("pet") Pet pet, Model model){
        petService.savePet(pet);
        return "redirect:/";
    }

    @GetMapping("/")
    public String list(Model model){
       model.addAttribute("pet", new Pet());
       model.addAttribute("petList", petService.petList());
       return "index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        petService.deletePet(id);
       return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable int id, Model model){
        model.addAttribute("pet", petService.getPetbyId(id));
        return "updated";
    }


}
