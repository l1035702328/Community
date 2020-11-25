package life.lzz.community.community.mapper;

import life.lzz.community.community.pojo.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    int insertUser(User user);
    User queryUser(@Param("token") String token);
    User queryUserById(@Param("id") Integer id);
    User findByAccountId(@Param("accountId") String accountId);
    void update(User user);
}
