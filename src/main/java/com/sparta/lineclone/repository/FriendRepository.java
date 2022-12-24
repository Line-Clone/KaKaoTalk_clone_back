package com.sparta.lineclone.repository;

import com.sparta.lineclone.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FriendRepository extends JpaRepository<Friend, Long> {

    Optional<Friend> findByIdAndFriendId(Long id,Long friendId);
}
