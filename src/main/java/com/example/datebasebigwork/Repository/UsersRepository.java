package com.example.datebasebigwork.Repository;

import com.example.datebasebigwork.Entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users,Integer> {
    Users findByusername(String username);
    void deleteByusername(String username);
    List<Users> findByusernameLike(String keyword);
}
