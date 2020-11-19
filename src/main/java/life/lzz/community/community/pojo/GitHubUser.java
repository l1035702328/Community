package life.lzz.community.community.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GitHubUser {
    private String name;
    private Long id;
    private String bio;
    private String avatarUrl;

}
