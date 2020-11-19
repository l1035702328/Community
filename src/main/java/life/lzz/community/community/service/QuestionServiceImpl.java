package life.lzz.community.community.service;

import life.lzz.community.community.mapper.QuestionMapper;
import life.lzz.community.community.mapper.UserMapper;
import life.lzz.community.community.pojo.PaginationDTO;
import life.lzz.community.community.pojo.QuestionDTO;
import life.lzz.community.community.pojo.model.Question;
import life.lzz.community.community.pojo.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public int insertCreate(Question question) {
        return questionMapper.insertCreate(question);
    }

    @Override
    public PaginationDTO questionPagination(Integer page, Integer size) {
        Integer offset=size*(page-1);
        List<Question> list = questionMapper.questionList(offset,size);
        List<QuestionDTO> questionDTOList=new ArrayList<>();
        PaginationDTO paginationDTO = new PaginationDTO();//Feny
        for (Question question : list) {
            User user=userMapper.queryUserById(question.getCreator());
            QuestionDTO questionDTO=new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        Integer totalCount=questionMapper.queryCount();
        paginationDTO.setPagination(totalCount,page,size);
        return paginationDTO;
    }

    @Override
    public Integer queryCount() {
        return questionMapper.queryCount();
    }
}
