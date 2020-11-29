package life.lzz.community.community.service;

import life.lzz.community.community.pojo.PaginationDTO;
import life.lzz.community.community.pojo.QuestionDTO;
import life.lzz.community.community.pojo.model.Question;
import life.lzz.community.community.pojo.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionService {
    int insertCreate(Question question);
    PaginationDTO questionPagination(Integer page, Integer size);
    Integer queryCount();
    PaginationDTO questionPagination(Integer id, Integer page, Integer size);
    QuestionDTO getQuestionDTOById(Integer id);
    Question getById(Integer id);
    void createOrUpdate(Question question);
    void incView(Integer id);
}
