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

    @Override
    public User findByToken(String token) {
        return userMapper.queryUser(token);
    }

    @Override
    public void createOrUpdate(User user) {
        User dbUser=userMapper.findByAccountId(user.getAccountId());
        if(dbUser==null){
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insertUser(user);
        }else{
            dbUser.setGmtModified(System.currentTimeMillis());
            dbUser.setAvatarUrl(user.getAvatarUrl());
            dbUser.setName(user.getName());
            dbUser.setToken(user.getToken());
            userMapper.update(dbUser);
        }
    }


}
