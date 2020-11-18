package life.lzz.community.community.mapper;

import life.lzz.community.community.pojo.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    public int insertUser(User user);
    public User queryUser(int id);
}
