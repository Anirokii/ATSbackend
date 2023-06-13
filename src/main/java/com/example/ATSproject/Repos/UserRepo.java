package com.example.ATSproject.Repos;

import com.example.ATSproject.Modals.Actors.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Person,Long> {
    Person findByEmail(String email);
    Person findByLinkedInId(String linkedInId);
}
