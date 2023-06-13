package com.example.ATSproject.Services;

import com.example.ATSproject.Modals.Actors.Person;
import com.example.ATSproject.Repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

//    public Person signUp(Person person) {
//        person.setPassword(passwordEncoder.encode(person.getPassword()));
//        return userRepository.save(person);
//    }
//
//    public Person signUpWithLinkedIn(String linkedInId, String email) {
//        Person person = new Person();
//        person.setLinkedInId(linkedInId);
//        person.setEmail(email);
//        return userRepository.save(person);
//
//    }
}
