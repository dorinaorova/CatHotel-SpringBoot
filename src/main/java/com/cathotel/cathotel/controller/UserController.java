package com.cathotel.cathotel.controller;

import com.cathotel.cathotel.model.AuthRequest;
import com.cathotel.cathotel.model.Cat;
import com.cathotel.cathotel.model.Registration;
import com.cathotel.cathotel.model.User;
import com.cathotel.cathotel.service.CatService;
import com.cathotel.cathotel.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    private final UserService service;
    private final CatService catService;
    public UserController(UserService userService, CatService catService){
        service=userService;
        this.catService = catService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users= service.findAllUser();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable("id") Long id){
       Optional<User> user= service.findUserById(id);
       return  new ResponseEntity<>(user, HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User newUser){
        if(newUser.getRoles().equals("")) newUser.setRoles("user");
        User user = service.addUser(newUser);
        return  new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        User updatedUser = service.updateUser(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @PutMapping("/addcat/{id}")
    public ResponseEntity<User> addCatToUser(@RequestBody Cat newCat, @PathVariable("id") Long id){
        Optional<User> user= service.findUserById(id);
            //newCat.setOwner(user.get());
            user.get().addCat(newCat);
            catService.addCat(newCat);
            User updatedUser = service.updateUser(user.get());
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @PostMapping("/auth")
    public ResponseEntity<User> authUser(@RequestBody AuthRequest authReq){
        User user = service.findUserByEmail(authReq.getEmail());
        if(user.getPassword().equals(authReq.getPassword())) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        else return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("findCatForUser/{id}")
    public ResponseEntity<List<Cat>> findCatForUser(@PathVariable("id") Long id){
        List<Cat> cats=  service.findUserById(id).get().getCats();
        return new ResponseEntity<>(cats, HttpStatus.OK);
    }

    @GetMapping("findRegsForUser/{id}")
    public ResponseEntity<List<Registration>> findRegsForUser(@PathVariable("id") Long id){
        List<Registration> regs=  service.findUserById(id).get().getRegs();
        return new ResponseEntity<>(regs, HttpStatus.OK);
    }


}
