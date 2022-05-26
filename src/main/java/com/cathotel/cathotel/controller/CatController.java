package com.cathotel.cathotel.controller;

import com.cathotel.cathotel.model.Cat;
import com.cathotel.cathotel.model.Registration;
import com.cathotel.cathotel.model.User;
import com.cathotel.cathotel.service.CatService;
import com.cathotel.cathotel.service.RegistrationService;
import com.cathotel.cathotel.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cat")
public class CatController {
    private final CatService catService;
    private final RegistrationService regService;
    private final UserService userService;

    public CatController(CatService catService, RegistrationService regService, UserService userService) {
        this.catService=catService;
        this.regService=regService;
        this.userService=userService;
    }
    @GetMapping("/all")
    public ResponseEntity<List<Cat>> getAllCat(){
        List<Cat> cats = catService.findAllCats();
        return new ResponseEntity<>(cats, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<Cat>> getCatById(@PathVariable("id") Long id){
        Optional<Cat> cat = catService.findCatById(id);
        return new ResponseEntity<>(cat, HttpStatus.OK);
    }

    @PostMapping("/add/{id}")
    public ResponseEntity<Cat> addCat(@RequestBody Cat newCat, @PathVariable("id") Long userId){
        User user = userService.findUserById(userId).get();
        if(user!=null) {
            newCat.setUser_id(userId);
            Cat cat = catService.addCat(newCat);
            user.addCat(cat);
            userService.updateUser(user);
            return new ResponseEntity<>(cat, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Cat> updateCat(@RequestBody Cat updateCat, @PathVariable("id") Long id){
        Cat cat = catService.findCatById(id).get();
        if(updateCat.getColour()!=null)  cat.setColour(updateCat.getColour());
        if(updateCat.getName()!=null) cat.setName(updateCat.getName());
        if(updateCat.isRegistered()) cat.setRegistered(updateCat.isRegistered());
        Cat updatedCat = catService.updateCat(cat);
        return new ResponseEntity<>(updatedCat, HttpStatus.OK);
    }

    @PutMapping("/regCat/{id}")
    public ResponseEntity<Cat> registrateCat(@RequestBody Registration registration, @PathVariable("id") Long id){
        Cat cat = catService.findCatById(id).get();
        Registration reg = new Registration(id, registration.getStart(), registration.getEnd());
        Registration result = regService.addRegistration(reg);

        User user= userService.findUserById(cat.getUser_id()).get();
        user.addReg(result);
        userService.updateUser(user);

        cat.setReg(result);
        Cat updatedCat = catService.updateCat(cat);
        return new ResponseEntity<>(updatedCat, HttpStatus.OK);
    }

    @PutMapping("/deleteReg/{id}")
    public ResponseEntity<Cat> deleteRegCat(@RequestBody Long reg_id, @PathVariable("id") Long id){
        Cat cat = catService.findCatById(id).get();
        cat.setRegistered(false);

        User user= userService.findUserById(cat.getUser_id()).get();
        user.removeReg(reg_id);
        userService.updateUser(user);

        regService.deleteRegistration(reg_id);
        cat.setReg(null);
        Cat updatedCat = catService.updateCat(cat);

        return new ResponseEntity<>(updatedCat, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCat(@PathVariable("id") Long id){
        catService.deleteCat(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
