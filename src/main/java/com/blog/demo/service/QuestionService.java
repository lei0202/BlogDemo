package com.blog.demo.service;

import com.blog.demo.dto.PaginationDTO;

public interface QuestionService {

    public PaginationDTO list(Integer page, Integer size);

}
