package com.hanium.catsby.Town.domain;

import com.hanium.catsby.User.domain.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor //빈생성자
@AllArgsConstructor //전체 생성자
@Builder
@Entity
@Table(name = "Town_Comment")
public class TownComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "townComment_id")
    private int id;

    @ManyToOne	(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")//user_id라는 컬럼이 만들어짐
    private Users user;

    @ManyToOne	(fetch = FetchType.LAZY)
    @JoinColumn(name = "townCommunity_id")//town_community_id라는 컬럼이 만들어짐
    private TownCommunity townCommunity;

    private String content;

    //    @CreationTimestamp//insert시 시간 자동 저장
    private String date;

    private String created_time;

    private String updated_time;

}