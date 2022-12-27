package com.sparta.lineclone.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Friend extends Timestamped{
    //필드
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column
    private Long friendId;

    public Friend(User user, Long friendId){
        this.user = user;
        this.friendId = friendId;
    }
}