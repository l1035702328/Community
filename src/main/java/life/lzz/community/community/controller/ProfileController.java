package life.lzz.community.community.controller;

import life.lzz.community.community.pojo.PaginationDTO;
import life.lzz.community.community.pojo.model.User;
import life.lzz.community.community.service.QuestionService;
import life.lzz.community.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {
    @Autowired
    QuestionService questionService;
    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action")String action,
                          Model model,
                          HttpServletRequest request,
                          @RequestParam(name = "page",defaultValue = "1")Integer page,
                          @RequestParam(name = "size",defaultValue = "5")Integer size
                          ){
        User user = (User)request.getSession().getAttribute("user");
        if (user==null){
            return "index";
        }
        if("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
        }else if("reply".equals(action)){
            model.addAttribute("section","replys");
            model.addAttribute("sectionName","最新回复");
        }
        PaginationDTO paginationDTO=questionService.questionPagination(user.getId(),page,size);
        model.addAttribute("pagination",paginationDTO);
        return "profile";

    }
}
