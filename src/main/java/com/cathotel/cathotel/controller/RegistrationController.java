package com.cathotel.cathotel.controller;

import com.cathotel.cathotel.model.Registration;
import com.cathotel.cathotel.service.CatService;
import com.cathotel.cathotel.service.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reg")
public class RegistrationController {
    private final RegistrationService service;
    private final CatService catservice;
    public  RegistrationController(RegistrationService service, CatService catservice){
        this.service=service;
        this.catservice=catservice;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Registration>> getAllRegistrations(){
        List<Registration> regs = service.findAllRegistrations();
        return new ResponseEntity<>(regs, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<Registration>> getRegistrationById(@PathVariable("id") Long id){
        Optional<Registration> reg = service.findRegistrationById(id);
        return  new ResponseEntity<>(reg, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteReg(@PathVariable("id") Long id){
        service.deleteRegistration(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
