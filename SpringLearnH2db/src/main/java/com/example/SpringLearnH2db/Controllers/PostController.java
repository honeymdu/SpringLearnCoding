package com.example.SpringLearnH2db.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringLearnH2db.Dto.PostDto;
import com.example.SpringLearnH2db.Services.PostService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/getAllPost")
    public ResponseEntity<?> getAllpost() {
       List<PostDto> postDto =  postService.getAllPosts();
        return ResponseEntity.ok(postDto);
    }

    @GetMapping("{postId}")
    public ResponseEntity<?> getPostById(@PathVariable Long postId) {
        return ResponseEntity.ok(postService.getPostById(postId));
    }

    @PostMapping
    public ResponseEntity<?> CreateNewPost(@RequestBody PostDto postDto){
        return new ResponseEntity<PostDto>(postService.CreateNewPost(postDto),HttpStatus.CREATED);
    }


    @PutMapping("/{postId}")
    public ResponseEntity<?> UpdatePostById(@RequestBody PostDto postDto,@PathVariable Long postId){
        return new ResponseEntity<PostDto>(postService.UpdatePostById(postDto,postId),HttpStatus.CREATED);
    }
    
    



}
