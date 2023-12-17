package com.productmanagementsystem.services;


import com.productmanagementsystem.model.User;
import com.productmanagementsystem.repository.PeopleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeopleServiceImpl implements PeopleService {
    @Autowired
    private PeopleRepo peopleRepo;
    @Override
    public List<User> getAllPeople() {
        return peopleRepo.findAll();
    }

    @Override
    public User getPeopleById(long id) {
        Optional< User > optional = peopleRepo.findById((int) id);
        User user = null;
        if (optional.isPresent()) {
            user = optional.get();
        } else {
            throw new RuntimeException(" Employee not found for id :: " + id);
        }
        return user;
    }

    @Override
    public void savePeople(User user) {
        this.peopleRepo.save(user);
    }

    @Override
    public void deletePeopleById(long id) {
        this.peopleRepo.deleteById((int) id);
    }
}
