package life.lzz.community.community.controller;

import life.lzz.community.community.pojo.AccessToken;
import life.lzz.community.community.pojo.GitHubUser;
import life.lzz.community.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.BindException;
import java.sql.SQLOutput;

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

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code")String code,
                           @RequestParam(name = "state")String state){

        AccessToken accessToken = new AccessToken();
        accessToken.setCode(code);
        accessToken.setClient_secret(clientSecret);
        accessToken.setClient_id(clientId);
        accessToken.setRedirect_url(redirectUrl);
        accessToken.setState(state);
        String token=githubProvider.getAccessToken(accessToken);
        GitHubUser gitHubUser=githubProvider.getUser(token);
        System.out.println(gitHubUser.getName());
        return "index";
    }
}
