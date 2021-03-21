package com.example.datebasebigwork.Service;

import com.example.datebasebigwork.Entity.Users;
import org.springframework.data.domain.Page;

import java.util.List;

public interface service {
    void save(Users users);
    Users findByusername(String username);
    void updateInfoByUsername(String username,String password,String userPic,String address,String email,
                                     String phoneNumber,String truename,String introduce);
    Page<Users> findAllUsers(int size, Integer page);
    int numPage(int size);
    void deleteByusername(String username);
    List<Users> searchUser(String keyword);
}
