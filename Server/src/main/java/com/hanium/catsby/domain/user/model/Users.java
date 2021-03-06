package com.hanium.catsby.domain.user.model;

import com.hanium.catsby.domain.common.model.BaseTimeEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Users extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String uid;
    private String nickname;
    private String address;

    @Column(name = "fcm_token")
    private String fcmToken;

    private String image;

}
