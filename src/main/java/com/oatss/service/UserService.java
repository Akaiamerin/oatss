package com.oatss.service;
import com.oatss.entity.User;
import java.util.List;
public interface UserService {
    int insertUser(User user);
    int deleteUserById(Integer id);
    int updateUserById(User user);
    User selectUserById(Integer id);
    User selectUserByUsername(String username);
    List<User> selectAllUser();
    List<User> selectUserByKeyword(String keyword);
}