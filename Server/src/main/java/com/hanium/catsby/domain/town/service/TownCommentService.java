package com.hanium.catsby.domain.town.service;

import com.hanium.catsby.domain.town.model.TownComment;
import com.hanium.catsby.domain.town.model.TownCommunity;
import com.hanium.catsby.domain.town.repository.TownCommentRepository;
import com.hanium.catsby.domain.town.repository.TownCommunityRepository;
import com.hanium.catsby.domain.user.model.MyComment;
import com.hanium.catsby.domain.user.repository.MyCommentRepository;
import com.hanium.catsby.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class TownCommentService {

    @Autowired
    TownCommentRepository townCommentRepository;

    @Autowired
    TownCommunityRepository townCommunityRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MyCommentRepository myCommentRepository;

    public String currentTime(){
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    @Transactional(readOnly = true)
    public List listTownComment(int id){
        return townCommentRepository.findByTownCommunity_Id(id);
    }

    @Transactional
    public TownComment writeTownComment(int id, String uid, TownComment requestTownComment){
        TownCommunity townCommunity = townCommunityRepository.findById(id)
                .orElseThrow(()->{
                    return new IllegalArgumentException("댓글 쓰기 실패 : 게시글 id를 찾을 수 없습니다.");
                }); //영속화 완료

        requestTownComment.setUser(userRepository.findUserByUid(uid));
        requestTownComment.setTownCommunity(townCommunity);
        requestTownComment.setDate(currentTime());
        townCommentRepository.save(requestTownComment);

        //myComment
        MyComment myComment = new MyComment();
        myComment.setTownComment(requestTownComment);
        myCommentRepository.save(myComment);

        TownComment mTownComment = new TownComment();
        mTownComment.setId(requestTownComment.getId());
        mTownComment.setContent(requestTownComment.getContent());
        mTownComment.setDate(requestTownComment.getDate());
        mTownComment.setUser(requestTownComment.getUser());

        return mTownComment;
    }

    @Transactional
    public void deleteTownComment(int commentId){
        //myComment
//        myCommentRepository.deleteByTownComment_Id(commentId);

        townCommentRepository.deleteById(commentId);
    }

    @Transactional
    public void updateTownComment(int townComment_id, TownComment requestTownComment) {//댓글 수정
        TownComment townComment = townCommentRepository.findById(townComment_id)
                .orElseThrow(()->{
                    return new IllegalArgumentException("댓글 찾기 실패 : 댓글 id를 찾을 수 없습니다.");
                }); //영속화 완료

        townComment.setContent(requestTownComment.getContent());
        //해당 함수로 종료시(Service가 종료될 때) 트랜잭션이 종료된다. 이때 더티체킹이 일어남 - 자동 업데이트됨. db쪽으로 flush

        //myComment
//        MyComment myComment = myCommentRepository.findByTownComment_Id(townComment_id);
//        myComment.setTownComment(townComment);

    }
}
