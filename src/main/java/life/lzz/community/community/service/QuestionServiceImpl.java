package life.lzz.community.community.service;

import life.lzz.community.community.exception.CustomizeErrorCode;
import life.lzz.community.community.exception.CustomizeException;
import life.lzz.community.community.mapper.QuestionMapper;
import life.lzz.community.community.mapper.UserMapper;
import life.lzz.community.community.pojo.PaginationDTO;
import life.lzz.community.community.pojo.QuestionDTO;
import life.lzz.community.community.pojo.model.Question;
import life.lzz.community.community.pojo.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.sasl.SaslServer;
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
        PaginationDTO paginationDTO = new PaginationDTO();//Feny
        Integer totalCount=questionMapper.queryCount();
        Integer totalPage;
        if(totalCount%size==0){
            totalPage=totalCount/size;
            //----------------
            if(totalPage==0){
                totalPage=1;
            }
            //----------------
        }else{
            totalPage=totalCount/size+1;
        }
        paginationDTO.setPagination(totalPage,page);
        if(page<1){
            page=1;
        }
        if(page>paginationDTO.getTotalPage()){
            page=paginationDTO.getTotalPage();
        }
        Integer offset=size*(page-1);
        List<Question> list = questionMapper.questionList(offset,size);
        List<QuestionDTO> questionDTOList=new ArrayList<>();

        for (Question question : list) {
            User user=userMapper.queryUserById(question.getCreator());
            QuestionDTO questionDTO=new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }

    @Override
    public Integer queryCount() {
        return questionMapper.queryCount();
    }

    @Override
    public PaginationDTO questionPagination(Integer userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();//Feny
        Integer totalCount=questionMapper.queryCountByUserId(userId);
        Integer totalPage;
        if(totalCount%size==0){
            totalPage=totalCount/size;
        }else{
            totalPage=totalCount/size+1;
        }
        if(page<1){
            page=1;
        }

        if(page>totalPage){
            page=totalPage;
        }
        paginationDTO.setPagination(totalPage,page);
        Integer offset=size*(page-1);
        List<Question> list = questionMapper.questionList1(userId,offset,size);
        List<QuestionDTO> questionDTOList=new ArrayList<>();

        for (Question question : list) {
            User user=userMapper.queryUserById(question.getCreator());
            QuestionDTO questionDTO=new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;

    }

    @Override
    public QuestionDTO getQuestionDTOById(Integer id) {
        Question question = questionMapper.getById(id);
        if(question==null){
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User user=userMapper.queryUserById(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    @Override
    public Question getById(Integer id) {
        return questionMapper.getById(id);
    }

    @Override
    public void createOrUpdate(Question question) {
        if(question.getId()==null){
            question.setGmtModified(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.insertCreate(question);
        }else{
            //更新
            int updateFlag = questionMapper.update(question);
            if(updateFlag!=1){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }

    @Override
    public void incView(Integer id) {
        Question question = questionMapper.getById(id);
        questionMapper.updateByView(question);
    }

}
