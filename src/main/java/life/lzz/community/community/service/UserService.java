package life.lzz.community.community.service;

import life.lzz.community.community.pojo.model.User;
import org.springframework.stereotype.Service;


public interface UserService {
    public int insertUser(User user);
    User findByToken(String token);
}
