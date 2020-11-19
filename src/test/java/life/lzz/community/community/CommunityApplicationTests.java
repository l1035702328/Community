package life.lzz.community.community;

import life.lzz.community.community.mapper.UserMapper;
import life.lzz.community.community.pojo.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CommunityApplicationTests {
    @Autowired
    UserMapper userMapper;
    @Test
    void contextLoads() {


    }

}
