package life.lzz.community.community.service;

import life.lzz.community.community.mapper.QuestionMapper;
import life.lzz.community.community.pojo.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionMapper questionMapper;
    @Override
    public int insertCreate(Question question) {
        return questionMapper.insertCreate(question);
    }
}
