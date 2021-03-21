package com.example.datebasebigwork.Service;

import com.example.datebasebigwork.Entity.Users;
import com.example.datebasebigwork.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class serviceImpl implements service{
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public void save(Users users) {
        usersRepository.save(users);
    }

    @Override
    public Users findByusername(String username){
        return usersRepository.findByusername(username);
    }
    @Override
    public void updateInfoByUsername(String username,String password,String userPic,String address,String email,
                                     String phoneNumber,String truename,String introduce)
    {
        Users user = findByusername(username);
        System.out.println(password.equals(""));
        if (!password.equals("")&&!user.getUserPassword().equals(password)){
            user.setUserPassword(password);
        }
        if (userPic!=null){
            user.setUserPic(username+".png");
        }
        user.setAddress(address);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setTruename(truename);
        user.setUserIntroduction(introduce);

        usersRepository.saveAndFlush(user);
    }
    @Override
    public Page<Users> findAllUsers(int size,Integer page){
        if (page == null)
            page = 1;
        Sort sort=Sort.by(Sort.Direction.DESC,"username");
        Page<Users> usersPage = (Page<Users>)usersRepository.findAll(PageRequest.of(page-1,size,sort));
        return usersPage;
    }
    @Override
    public int numPage(int size){
        Sort sort=Sort.by(Sort.Direction.DESC,"username");
        Page<Users> usersPage = (Page<Users>)usersRepository.findAll(PageRequest.of(0,size,sort));
        return usersPage.getTotalPages();

    }
    @Override
    @Transactional
    public void deleteByusername(String username){

        usersRepository.deleteByusername(username);

    }
    @Override
    public List<Users> searchUser(String keyword){


        List<Users> usersList = usersRepository.findByusernameLike("%"+keyword+"%");
        return usersList;
    }

}
