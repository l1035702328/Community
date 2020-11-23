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
    private Integer totalPage;

    public void setPagination(Integer totalPage, Integer page) {
        this.page=page;
        this.totalPage=totalPage;
        pages.add(page);
        for(int i=1;i<=3;i++){
            if(page-i>0){
                pages.add(0,page-i);
            }
            if(page+i<=totalPage){
                pages.add(page+i);
            }
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
