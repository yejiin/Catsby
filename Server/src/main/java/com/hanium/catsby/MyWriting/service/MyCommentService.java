package com.hanium.catsby.MyWriting.service;

import com.hanium.catsby.MyWriting.domain.MyComment;
import com.hanium.catsby.MyWriting.repository.MyCommentRepository;
import com.hanium.catsby.MyWriting.repository.MyPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MyCommentService {

    @Autowired
    MyCommentRepository myCommentRepository;

    @Transactional(readOnly = true)
    public List listMyComment(Long user_id){
        return myCommentRepository.findByTownComment_User_IdOrBowlComment_User_IdOrderByMyCommentIdDesc(user_id, user_id);
    }

}
