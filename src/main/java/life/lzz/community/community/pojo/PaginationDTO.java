package life.lzz.community.community.pojo;

import life.lzz.community.community.pojo.model.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private boolean showPrevious;
    private boolean showFirstPage; //>>
    private boolean showNext;
    private boolean showEndPage;   //<<
    private Integer page;
    private List<Integer> pages=new ArrayList<>();  //页码

    public void setPagination(Integer totalCount, Integer page, Integer size) {
        Integer totalPage;
        if(totalCount%size==0){
            totalPage=totalCount/size;
        }else{
            totalPage=totalCount/size+1;
        }
        if(page==1){
            showPrevious=false;
        }else{
            showPrevious=true;
        }
        if(page==totalPage){
            showNext=false;
        }else{
            showNext=true;
        }
        if(pages.contains(1)){
            showFirstPage=false;
        }else {
            showFirstPage=true;
        }
        if(pages.contains(totalPage)){
            showEndPage=false;
        }else{
            showEndPage=true;
        }
    }
}
