package com.eazybytes.sboot.controller;

import com.eazybytes.sboot.model.Contact;
import com.eazybytes.sboot.service.ContactService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Slf4j
@Controller
public class ContactController {

    public final ContactService contactService;
    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @RequestMapping("/contact")
    public String dispalyContactPage(Model model){
        model.addAttribute("contact", new Contact());
        return "contact.html";
    }

//    @RequestMapping(value="/saveMsg",method = POST)
//    public ModelAndView saveMessage(@RequestParam String name,@RequestParam String mobileNum, @RequestParam String email,
//                                    @RequestParam String subject,@RequestParam String message){
//
//        log.info("name :"+ name);
//        log.info("mobileNum :"+ mobileNum);
//        log.info("email :"+ email);
//        log.info("subject :"+ subject);
//        log.info("message :"+ message);return new ModelAndView("redirect:/contact");
//    }

    @PostMapping(value="/saveMsg")
    public String  saveMessage(@Valid @ModelAttribute("contact") Contact contact, Errors errors){
        if (errors.hasErrors()){
            log.error("Contact from validation due to : " + errors.toString());
            return "contact.html";
        }
        contactService.saveMessageDetails(contact);
        return "redirect:/contact";
    }

    @RequestMapping("/displayMessages")
    public ModelAndView displayMessages(Model model){
        List<Contact> contactMsgs = contactService.findMsgsWithOpenStatus();
        ModelAndView modelAndView = new ModelAndView("messages.html");
        modelAndView.addObject("contactMsgs",contactMsgs);
        return modelAndView;
    }

    @RequestMapping(value = "/closeMsg",method = GET)
    public String closeMsg(@RequestParam int id, Authentication authentication) {
        contactService.updateMsgStatus(id,authentication.getName());
        return "redirect:/displayMessages";
    }
}
