package com.example.SpringLearnH2db.Repositories;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SpringLearnH2db.Entitys.PostEntity;

@Repository
public interface PostRepository extends JpaRepository<PostEntity,Long> {

    Optional <PostEntity> findById(Long postId);



}
