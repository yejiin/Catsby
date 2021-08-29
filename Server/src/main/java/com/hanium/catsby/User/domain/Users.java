package com.hanium.catsby.User.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hanium.catsby.BowlCommunity.domain.BowlComment;
import com.hanium.catsby.BowlCommunity.domain.BowlCommunity;
import com.hanium.catsby.BowlCommunity.domain.BowlLike;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Users {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String nickname;
    private String email;
    private String address;

    @Column(name = "created_time")
    private LocalDateTime createDate;

    @Column(name = "updated_time")
    private LocalDateTime updateDate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<BowlLike> bowlLikes = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<BowlCommunity> bowlCommunities = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<BowlComment> bowlComments = new ArrayList<>();

    public void setCreateDate(){
        this.createDate =LocalDateTime.now();
    }

    public void setUpdateDate(){
        this.updateDate = LocalDateTime.now();
    }

}