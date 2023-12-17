package com.productmanagementsystem.controller;


import com.productmanagementsystem.model.User;
import com.productmanagementsystem.repository.PeopleRepo;
import com.productmanagementsystem.services.PeoplePDFService;
import com.productmanagementsystem.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Controller
public class PeopleController {
    @Autowired
    private PeopleService peopleService;

    @GetMapping("/viewPeople")
    public String viewPeoplePage(Model model){
        model.addAttribute("listPeople", peopleService.getAllPeople());
        return "people";
    }

    @GetMapping("/showNewPeopleForm")
    public String showNewPeopleForm(Model model) {
        // create model attribute to bind form data
        User people = new User();
        model.addAttribute("people", people);
        return "new_employee";
    }
    @PostMapping("/savePeople")
    public String saveEmployee(@ModelAttribute("people") User user) {
        // save employee to database
        peopleService.savePeople(user);
        return "redirect:/viewPeople";
    }

    @GetMapping("/showPeopleForUpdate/{id}")
    public String showFormForUpdate(@PathVariable( value = "id") long id, Model model) {

        // get employee from the service
        User user = peopleService.getPeopleById(id);

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("user", user);
        return "updatePeople";
    }

    @GetMapping("/deletePeople/{id}")
    public String deletePeople(@PathVariable (value = "id") long id) {

        // call delete employee method
        this.peopleService.deletePeopleById(id);
        return "redirect:/viewPeople";
    }

    @Autowired
    PeopleRepo peopleRepo;


    @GetMapping ("/searchPeople")
    public String searchMethod(Model model){
        model.addAttribute("search",new User());
        return "searchPeople";
    }

    @PostMapping("/searchPeople")
    public String getEmployee(@ModelAttribute("search") User user, Model model){
        User user1=peopleService.getPeopleById(user.getId());
        if (user1!=null) {
            model.addAttribute("user1",user1);
            return "searchPeople";
        }else {
            model.addAttribute("error","He/She is not found");
            return "searchPeople";
        }
    }
}
