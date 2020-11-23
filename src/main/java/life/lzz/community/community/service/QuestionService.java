package life.lzz.community.community.service;

import life.lzz.community.community.pojo.PaginationDTO;
import life.lzz.community.community.pojo.QuestionDTO;
import life.lzz.community.community.pojo.model.Question;

import java.util.List;

public interface QuestionService {
    int insertCreate(Question question);
    PaginationDTO questionPagination(Integer page, Integer size);
    Integer queryCount();
    PaginationDTO questionPagination(Integer id, Integer page, Integer size);
    QuestionDTO getQuestionDTOById(Integer id);
}
