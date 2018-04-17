package com.example.demo;

import com.example.demo.Model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
@Autowired
    PersonRepository personRepo;

@RequestMapping("/")
    public String showIndex(Model model){
    model.addAttribute("party", personRepo.findAll());
    return "index";
}
@RequestMapping("/add")
    public String addContact(Model model){
    model.addAttribute("person", new Person());
    return "addcontact";
}
@RequestMapping("/savecontact")
    public String saveContact(@ModelAttribute("person")Person toSave, BindingResult result){
    if (result.hasErrors()){
        return "addcontact";
    }
    personRepo.save(toSave);
    return "redirect:/";
}
@RequestMapping("/changestatus/{id}")
        public String inviteReturn(@PathVariable("id") long id){
    Person thisPerson = personRepo.findById(id).get();
    thisPerson.setInvited(!thisPerson.isInvited());
    personRepo.save(thisPerson);
    return "redirect:/";
}

}
