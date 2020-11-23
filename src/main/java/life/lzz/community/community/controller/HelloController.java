package life.lzz.community.community.controller;

import life.lzz.community.community.pojo.PaginationDTO;
import life.lzz.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
    @Autowired
    QuestionService questionService;

    @RequestMapping({"/","/index"})
    public String index(Model model,
                        @RequestParam(name = "page",defaultValue = "1")Integer page,
                        @RequestParam(name = "size",defaultValue = "5")Integer size){
        PaginationDTO pagination=questionService.questionPagination(page,size);
        model.addAttribute("pagination",pagination);
        return "index";
    }
}
