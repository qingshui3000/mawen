package com.zhao.mawen.controller.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageDTO {
    private Integer page;
    private Integer total;
    private List<QuestionDTO> questions;
    private List<Integer> pages = new ArrayList<>();

    public void initPages(){
        page = page <= 0 ? 1 : page;
        page = page > total ? total : page;
        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                pages.add(0, page - i);
            }

            if (page + i <= total) {
                pages.add(page + i);
            }
        }
    }
}
