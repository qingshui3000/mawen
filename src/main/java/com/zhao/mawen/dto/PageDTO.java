package com.zhao.mawen.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageDTO {
    private List<QuestionDTO> questions;
    private Boolean showPrevious;
    private Boolean showFirstPage;
    private Boolean showNext;
    private Boolean showEndPage;
    private Integer page;
    private Integer totalPage;
    private List<Integer> pages = new ArrayList<>();

    public void setPagenation(Integer totalCount, Integer page, Integer size) {
        Integer totalPage;
        if(totalCount % size == 0){
            totalPage = totalCount / size;
        }else {
            totalPage = totalCount / size + 1;
        }
        this.totalPage = totalPage;
        if(page > totalPage){
            page = totalPage;
        }

        this.page = page;
        pages.add(page);
        for(int i = 1;i <= 3;i++){
            if(page - i > 0){
                pages.add(0,page - i);
            }
            if(page + i <= totalPage){
                pages.add(page + i);
            }
        }
        showPrevious = !(page == 1);
        showFirstPage = !(pages.contains(1));
        showNext = !(page == totalPage);
        showEndPage = !(pages.contains(totalPage));
    }
}
