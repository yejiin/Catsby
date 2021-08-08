package com.hanium.catsby.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor //빈생성자
@AllArgsConstructor //전체 생성자
@Builder
@Entity
@Table(name = "Town_Community")
public class TownCommunity {

    @Id //PK지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int townCommunity_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")//use_id라는 컬럼이 만들어짐
    private User user;

    //    @Lob//대용량 데이터
    private String image;

    private String content;

    private String title;

    //    @CreationTimestamp//insert시 시간 자동 저장
    private String date;

    //하나의 게시글에 여러개의 댓글이 존재한다. 1:N 관계 -> OneToMany

    @OneToMany(mappedBy = "town_community", fetch = FetchType.LAZY) //mappedBy : 난 연관관계의 주인이 아니다 (난 FK가 아니에요) DB에 칼럼을 만들지 마세요.
    private List<TownComment> town_comment;
    @OneToMany(mappedBy = "townCommunity", fetch = FetchType.LAZY) //mappedBy : 난 연관관계의 주인이 아니다 (난 FK가 아니에요) DB에 칼럼을 만들지 마세요.
    @JsonIgnoreProperties({"townCommunity"}) //무한참조 방지
    @OrderBy("id desc")
    private List<TownComment> townComment;

    //하나의 게시글에 여러개의 좋아요가 존재한다. 1:N 관계 -> OneToMany
    @OneToMany(mappedBy = "townCommunity", fetch = FetchType.LAZY) //mappedBy : 난 연관관계의 주인이 아니다 (난 FK가 아니에요) DB에 칼럼을 만들지 마세요.
    private List<TownLike> townlike;

    private String created_time;

    private String updated_time;

}