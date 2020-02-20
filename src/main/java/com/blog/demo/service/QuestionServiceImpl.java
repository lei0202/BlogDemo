package com.blog.demo.service;

import com.blog.demo.dto.PaginationDTO;
import com.blog.demo.dto.QuestionDTO;
import com.blog.demo.mapper.QuestionMapper;
import com.blog.demo.mapper.UserMapper;
import com.blog.demo.model.Question;
import com.blog.demo.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @SuppressWarnings("all")
    @Autowired
    private UserMapper userMapper;
    @Autowired
    @SuppressWarnings("all")
    private QuestionMapper questionMapper;

    @Override
    public PaginationDTO list(int page, int size) {

        Integer offset = size*(page-1);
        List<Question> questions = questionMapper.list(offset,size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        PaginationDTO paginationDTO = new PaginationDTO();
        for (Question q:questions
             ) {
            User user = userMapper.findById(q.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(q,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);

        }
        paginationDTO.setQuestionDTOS(questionDTOList);
        Integer pageTotalCount = questionMapper.count();
        paginationDTO.setPagination(pageTotalCount,page,size);
        return paginationDTO;
    }
}
