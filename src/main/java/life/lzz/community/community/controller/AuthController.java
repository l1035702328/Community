package life.lzz.community.community.controller;

import life.lzz.community.community.mapper.UserMapper;
import life.lzz.community.community.pojo.AccessToken;
import life.lzz.community.community.pojo.GitHubUser;
import life.lzz.community.community.pojo.model.User;
import life.lzz.community.community.provider.GithubProvider;
import life.lzz.community.community.service.UserService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.url}")
    private String redirectUrl;

    @Autowired
    private UserService userService;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code")String code,
                           @RequestParam(name = "state")String state,
                           HttpServletResponse response
                           ){

        AccessToken accessToken = new AccessToken();
        accessToken.setCode(code);
        accessToken.setClient_secret(clientSecret);
        accessToken.setClient_id(clientId);
        accessToken.setRedirect_url(redirectUrl);
        accessToken.setState(state);
        String AccToken=githubProvider.getAccessToken(accessToken);
        GitHubUser gitHubUser=githubProvider.getUser(AccToken);
        if(gitHubUser!=null){
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(gitHubUser.getName());
            user.setAccountId(String.valueOf(gitHubUser.getId()));
            user.setAvatarUrl(gitHubUser.getAvatarUrl());
            userService.createOrUpdate(user);
//            userService.insertUser(user);
            response.addCookie(new Cookie("token",token));
            return "redirect:/index";
            //登录成功,写cookie 和session
        }else {
            //登录失败,重新登录
            return "redirect:/index";
        }
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request,HttpServletResponse response){
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }
}
