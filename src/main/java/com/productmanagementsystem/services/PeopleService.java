package com.productmanagementsystem.services;


import com.productmanagementsystem.model.User;

import java.util.List;

public interface PeopleService {
    List <User> getAllPeople();
    User getPeopleById(long id);

    void savePeople(User user);
    void deletePeopleById(long id);

}
