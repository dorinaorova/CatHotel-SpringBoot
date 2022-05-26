package com.cathotel.cathotel.service;

import com.cathotel.cathotel.model.Registration;
import com.cathotel.cathotel.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistrationService {
    private final RegistrationRepository repository;
    @Autowired
    public RegistrationService(RegistrationRepository userRepo){
        repository=userRepo;
    }

    public Registration addRegistration(Registration registration){
        return repository.save(registration);
    }

    public List<Registration> findAllRegistrations(){
        return repository.findAll();
    }

    public Registration updateRegistration(Registration registration){
        return repository.save(registration);
    }

    public void deleteRegistration(Long id){
        repository.deleteById(id);
    }

    public Optional<Registration> findRegistrationById(Long id){
        return repository.findById(id);
    }

}
