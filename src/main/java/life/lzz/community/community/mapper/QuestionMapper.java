package life.lzz.community.community.mapper;

import life.lzz.community.community.pojo.QuestionDTO;
import life.lzz.community.community.pojo.model.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface QuestionMapper {
    int insertCreate(Question question);
    List<Question> questionList(@Param("offset") Integer offset, @Param("size") Integer size);
    Integer queryCount();
    List<Question> questionList1(@Param("userId") Integer userId, @Param("offset") Integer offset, @Param("size") Integer size);
    Integer queryCountByUserId(@Param("userId") Integer userId);
    Question getById(@Param("id") Integer id);
    int update(Question question);
}
