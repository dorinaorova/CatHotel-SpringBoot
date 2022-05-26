package com.cathotel.cathotel.service;

import com.cathotel.cathotel.model.Cat;
import com.cathotel.cathotel.repository.CatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CatService {
    private final CatRepository catRepo;

    @Autowired
    public CatService(CatRepository catRepo){
        this.catRepo=catRepo;
    }

    public Cat addCat(Cat newCat){
        return catRepo.save(newCat);
    }

    public List<Cat> findAllCats(){
        return catRepo.findAll();
    }

    public Cat updateCat(Cat cat){
        return catRepo.save(cat);
    }
    public void deleteCat(Long id){
        catRepo.deleteById(id);
    }

    public Optional<Cat> findCatById(Long id){
        return catRepo.findById(id);
    }

}
