package com.itnews.controllers;

import com.itnews.models.user.AuthToken;
import com.itnews.models.user.LoginUser;
import com.itnews.models.user.User;
import com.itnews.security.JwtTokenUtil;
import com.itnews.servers.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

/**
 * @author f.brishtan
 * @since 17.10.18.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/token")
public class AccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @RequestMapping(value = "/generate-token", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody LoginUser loginUser) throws AuthenticationException {

        UsernamePasswordAuthenticationToken usToken = new UsernamePasswordAuthenticationToken(
                loginUser.getUsername(),
                loginUser.getPassword()
        );

        try {
            final Authentication authentication = authenticationManager.authenticate(usToken
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        final User user = userService.findOne(loginUser.getUsername());
        if (user.isBlocked()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
        final String token = jwtTokenUtil.generateToken(user);
        return ResponseEntity.ok(new AuthToken(token));
    }

    @RequestMapping(value = "/getUserInfo", method = RequestMethod.POST)
    public User getUser(@RequestBody String token) {
        return userService.findOne(jwtTokenUtil.getUsernameFromToken(token));
    }


//    @RequestMapping(value = "/signup", method = RequestMethod.POST)
//    public ResponseEntity<?> createUser(@RequestBody User user) {
//        if (userService.findById(user.getId()) != null) {
//
//        }
//
//        user.setRole("USER");
//
//        return new ResponseEntity<User>(userService.createOrUpdate(user), HttpStatus.CREATED);
//
//    }

//    @RequestMapping(value = "/userList", method = RequestMethod.GET)
//    public List<User> findAll() {
//        return userService.findAll();
//    }

//    // this is the login api/service
//    @PostMapping("/login")
//    public Principal user(Principal principal) {
//        return principal;
//    }
}
