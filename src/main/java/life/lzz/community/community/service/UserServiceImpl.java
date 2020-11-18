package life.lzz.community.community.service;

import life.lzz.community.community.mapper.UserMapper;
import life.lzz.community.community.pojo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserMapper userMapper;

    @Override
    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }
}
