package com.quitco.googlecontactsapi.controller;

import com.google.api.services.people.v1.model.Person;
import com.quitco.googlecontactsapi.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping("/getContacts")
    public String listContacts(Model model) throws IOException {
        List<Person> contacts = contactService.listContacts();
        model.addAttribute("contacts", contacts);

        return "contacts";
    }

    @GetMapping("/testGetContacts")
    @ResponseBody
    public List<Person> getContactsTest() throws IOException {
        return contactService.listContacts();
    }

    @GetMapping("/getContact/{resourceName}")
    public Person getContact(@PathVariable String resourceName) throws IOException {
        return contactService.getContact(resourceName);
    }

    @PostMapping("/createContact")
    public String createContact(@RequestBody Person contact) throws IOException {
        contactService.createContact(contact);
        return "redirect:/contacts/getContacts";
    }

    @PutMapping("/updateContact/{resourceName}")
    public Person updateContact(@PathVariable String resourceName, @RequestBody Person contact) throws IOException {
        return contactService.updateContact(resourceName, contact);
    }

    @RequestMapping(value="/deleteContact/people/{resourceName}", method= RequestMethod.POST)
    public String deleteContact(@PathVariable String resourceName) throws IOException {
        contactService.deleteContact("people/" + resourceName);
        return "redirect:/contacts/getContacts";
    }
}