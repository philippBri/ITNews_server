package com.itnews.servers.impl;

import com.google.common.hash.Hashing;
import com.itnews.DAO.RoleRepository;
import com.itnews.DAO.UserDao;
import com.itnews.DAO.UserRepository;
import com.itnews.models.user.PublicUser;
import com.itnews.models.user.User;
import com.itnews.models.user.UserDTO;
import com.itnews.servers.UserService;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Service(value = "userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user.getRoles().get(0).getName()));   //change
    }

    private List<SimpleGrantedAuthority> getAuthority(String role) {
        return Arrays.asList(new SimpleGrantedAuthority(role));
    }

    @Override
    public User create(UserDTO user) {
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        newUser.setEmail(user.getEmail());
        newUser.setRole(user.getRole());
        newUser.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
        return userDao.save(newUser);
    }

    @Override
    public User delete(int id) {
        User user = findById(id);
        if (user != null) {
//            userDao.delete(user);

            user.setEmail(hashUserInfo(user.getEmail()));
            user.setFirstname(hashUserInfo(user.getFirstname()));
            user.setLastname(hashUserInfo(user.getLastname()));
            user.setCity(hashUserInfo(user.getCity()));
            user.setCountry(hashUserInfo(user.getCountry()));
            user.setDeleted(true);
            userDao.save(user);
        }
        return user;
    }

    private String hashUserInfo(String originalValue) {
        if (!StringUtils.isNullOrEmpty(originalValue)) {
            return Hashing.sha256()
                    .hashString(originalValue, StandardCharsets.UTF_8)
                    .toString();
        }
        return originalValue;
    }

    @Override
    public User blockUser(int id) {
        User user = findById(id);
        if (user != null) {
            user.setBlocked(!user.isBlocked());
            userDao.save(user);
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public PublicUser findByIdPublicUser(int id) {
        User user = userRepository.findById(id);
        PublicUser publicUser = new PublicUser();
        if (user != null) {
            publicUser.setUsername(user.getUsername());
            publicUser.setFirstname(user.getFirstname());
            publicUser.setLastname(user.getLastname());
            publicUser.setEmail(user.getEmail());
            publicUser.setCity(user.getCity());
            publicUser.setCountry(user.getCountry());
            publicUser.setRole(user.getRole());
        }
        return publicUser;
    }

    @Override
    public User findOne(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User update(UserDTO user) {
        User currentUser = userDao.findByUsername(user.getUsername());
        currentUser.setFirstname(user.getFirstname());
        currentUser.setLastname(user.getLastname());
        currentUser.setCity(user.getCity());
        currentUser.setCountry(user.getCountry());
        currentUser.setUsername(user.getUsername());
//        currentUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        currentUser.setEmail(user.getEmail());
        currentUser.setRole(user.getRole());
        return userDao.save(currentUser);
    }


}
