package life.lzz.community.community.provider;

import com.alibaba.fastjson.JSON;
import life.lzz.community.community.pojo.AccessToken;
import life.lzz.community.community.pojo.GitHubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {
    public String getAccessToken(AccessToken accessToken) {
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType,JSON.toJSONString(accessToken));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            String string=response.body().string();
            String tokenStr=string.substring(string.indexOf("=")+1,string.indexOf("&"));
            return tokenStr;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public GitHubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        String url="https://api.github.com/user";
        Request request = new Request.Builder()
                .get()
                .url(url)
                .header("Authorization","token "+accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
               return JSON.parseObject(response.body().string(),GitHubUser.class);
            } else {
                throw new IOException("Unexpected code " + response);
            }
        } catch (IOException e) {
            return null;
        }
    }
}
