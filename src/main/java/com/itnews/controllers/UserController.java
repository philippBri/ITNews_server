package com.itnews.controllers;

import com.itnews.models.user.PublicUser;
import com.itnews.models.user.User;
import com.itnews.models.user.UserDTO;
import com.itnews.servers.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600) //http://localhost:4200
@RestController
//@RequestMapping({"/"})
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public User registration(@RequestBody UserDTO user) {
        if (userService.findOne(user.getUsername()) == null) {
            return userService.create(user);
        }
        return new User();
    }

//    @RequestMapping(value = "/getUserInfo", method = RequestMethod.POST)
//    public User getUser(@RequestBody String username) {
//        return userService.findOne(username);
//    }


    @RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
    public User updateProfile(@RequestBody UserDTO user) {
        return userService.update(user);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/userList")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/getPublicUserInfo/{id}")
    public PublicUser getPublicUserInfo(@PathVariable("id") int id) {
        return userService.findByIdPublicUser(id);
    }

    @GetMapping("/userId")
    public String getUsernameById(@RequestBody int id) {
        return userService.findById(id).getUsername();
    }

    @DeleteMapping(path = {"/userList/{id}"})
    public User delete(@PathVariable("id") int id) {
        return userService.delete(id);
    }

    @RequestMapping(value = "/userBlock", method = RequestMethod.POST)
    public User block(@RequestBody int id) {
        return userService.blockUser(id);
    }

    @RequestMapping(value = "/userCheck", method = RequestMethod.POST)
    public boolean isUserExist(@RequestBody String username) {
        return userService.findOne(username) != null;
    }

//    @PostMapping
//    public User createOrUpdate(@RequestBody User user) {
//        return userService.createOrUpdate(user);
//    }
//
//    @PostMapping("/signup")
//    public User logIn(@RequestBody User user) {
//        return userService.createOrUpdate(user);
//    }
//
//    @GetMapping("/login")
//    public boolean login(@RequestBody User user) {
//        return user.getUsername().equals("user") && user.getPassword().equals("password");
//    }
//
//    @GetMapping("/user")
//    public Principal user(HttpServletRequest request) {
//        String authToken = request.getHeader("Authorization")
//                .substring("Basic".length()).trim();
//        return () ->  new String(Base64.getDecoder()
//                .decode(authToken)).split(":")[0];
//    }
//
//    @GetMapping(path = {"/{id}"})
//    public User findOne(@PathVariable("id") int id) {
//        return userService.findById(id);
//    }
//
//    @PutMapping(path = {"/{id}"})
//    public User update(@PathVariable("id") int id, @RequestBody User user) {
//        user.setId(id);
//        return userService.update(user);
//    }
//


}
