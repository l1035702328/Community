package life.lzz.community.community.mapper;

import life.lzz.community.community.pojo.model.Question;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface QuestionMapper {
    int insertCreate(Question question);
}
