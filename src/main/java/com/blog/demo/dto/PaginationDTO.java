package com.blog.demo.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {
    private List<QuestionDTO> questionDTOS;
    private boolean hasPrevious;
    private boolean hasNext;
    private Integer currentPage;
    private Integer totalPage = 0;
    private List<Integer> pages = new ArrayList<>();
    public void setPagination(Integer TotalCount, Integer page, Integer size) {
        currentPage = page;
        if(TotalCount%size == 0){
            totalPage = TotalCount/size;
        }
        else {
            totalPage = TotalCount/size + 1;
        }

        if(currentPage == 1) {
            hasPrevious =false;
        }
        else {
            hasPrevious = true;
        }
        if(currentPage < totalPage) {
            hasNext = true;
        }

//         当前页面currentPage，总共显示5页内容；
        if(totalPage <= 5) {
            for (Integer i = 1; i <= totalPage; i++) {
                pages.add(i);
            }
        }
        else {
            if((currentPage - 1) < 2) {
                for (Integer i = 1; i <= 5; i++) {
                    pages.add(i);
                }
            }
            else if((totalPage - currentPage) < 2) {
                for (Integer i = totalPage-4; i <= totalPage; i++) {
                    pages.add(i);
                }
            }

            else {
                for (Integer i = currentPage-2; i <= currentPage+2; i++) {
                    pages.add(i);
                }
            }
        }

    }
}
