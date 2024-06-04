package com.oatss.service.impl;
import com.oatss.mapper.UserMapper;
import com.oatss.entity.User;
import com.oatss.service.UserService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }
    @Override
    public int deleteUserById(Integer id) {
        return userMapper.deleteUserById(id);
    }
    @Override
    public int updateUserById(User user) {
        return userMapper.updateUserById(user);
    }
    @Override
    public User selectUserById(Integer id) {
        return userMapper.selectUserById(id);
    }
    @Override
    public User selectUserByUsername(String username) {
        return userMapper.selectUserByUsername(username);
    }
    @Override
    public List<User> selectAllUser() {
        return userMapper.selectAllUser();
    }
    @Override
    public List<User> selectUserByKeyword(String keyword) {
        return userMapper.selectUserByKeyword(keyword);
    }
}