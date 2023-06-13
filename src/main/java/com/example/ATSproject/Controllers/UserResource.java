package com.example.ATSproject.Controllers;

import com.example.ATSproject.Modals.Actors.Person;
import com.example.ATSproject.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserResource {
//    @Autowired
//    private UserService userService;
//
//    @PostMapping("/signup")
//    public Person signUp(@RequestBody Person person) {
//        return userService.signUp(person);
//    }
//
//    @GetMapping("/oauth2/authorization/linkedin")
//    public String getLinkedInUserProfile(OAuth2AuthenticationToken auth) {
//        // Retrieve user attributes from the authentication token
//        Map<String, Object> userAttributes = auth.getPrincipal().getAttributes();
//        // Create a new user with the LinkedIn profile data
//        Person person = userService.signUpWithLinkedIn(userAttributes.get("id").toString(), userAttributes.get("email").toString());
//        // Create a session or return a JWT
//        return "redirect:/";

}
