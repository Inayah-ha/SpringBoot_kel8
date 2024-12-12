package com.domain.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.models.repos.CategoryRepo;

import jakarta.transaction.TransactionScoped;

@Service
@TransactionScoped
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo; 

    public com.domain.models.entity.Category save(com.domain.models.entity.Category category){
        return categoryRepo.save(category);
    }
    
    public com.domain.models.entity.Category findOne(Long id){
        Optional<com.domain.models.entity.Category> category = categoryRepo.findById(id);
        if(!category.isPresent()){
            return null;
        }
        return category.get();
    }
    
    public Iterable<com.domain.models.entity.Category> findAll(){
        return categoryRepo.findAll();
    }

    public void removeOne(Long id){
        categoryRepo.deleteById(id);
    }
}

