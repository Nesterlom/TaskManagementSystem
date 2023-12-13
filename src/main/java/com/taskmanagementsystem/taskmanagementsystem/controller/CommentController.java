package com.taskmanagementsystem.taskmanagementsystem.controller;

import com.taskmanagementsystem.taskmanagementsystem.dto.CommentDTO;
import com.taskmanagementsystem.taskmanagementsystem.dto.response.PageResponse;
import com.taskmanagementsystem.taskmanagementsystem.interfaces.ICommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
@Tag(name = "Comments API")
public class CommentController {
    private final ICommentService commentService;

    @Operation(summary = "This endpoint allow us to paginate all comments.Also you can use filtration and sorting")
    @GetMapping()
    public ResponseEntity<PageResponse<CommentDTO>> getAllComments(
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable,
            @RequestBody CommentDTO commentDTO) {
        PageResponse<CommentDTO> result = commentService.getAllComments(commentDTO, pageable);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Operation(summary = "This endpoint allow us to get information about one Comment")
    @GetMapping("/{commentId}")
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable Long commentId){
        return ResponseEntity.ok(commentService.getCommentById(commentId));
    }

    @Operation(summary = "This endpoint allow us to add new Comment")
    @PostMapping()
    public ResponseEntity<CommentDTO> addComment(@RequestBody CommentDTO commentDto){
        return ResponseEntity.ok(commentService.addComment(commentDto));
    }

    @Operation(summary = "This endpoint allow us to update Comment information")
    @PutMapping()
    public ResponseEntity<CommentDTO> updateComment(@RequestBody CommentDTO commentDto){
        return ResponseEntity.ok(commentService.updateComment(commentDto));
    }

    @Operation(summary = "This endpoint allow Admin to delete any Comment")
    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteComment(@PathVariable Long commentId){
        commentService.deleteComment(commentId);
    }
}
