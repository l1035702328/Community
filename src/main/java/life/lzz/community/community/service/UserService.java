package life.lzz.community.community.service;

import life.lzz.community.community.pojo.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;


public interface UserService {
    int insertUser(User user);
    User findByToken(String token);
    void createOrUpdate(User user);
}
