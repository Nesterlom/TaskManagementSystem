package com.taskmanagementsystem.taskmanagementsystem.service;

import com.taskmanagementsystem.taskmanagementsystem.dto.CommentDTO;
import com.taskmanagementsystem.taskmanagementsystem.dto.TaskDTO;
import com.taskmanagementsystem.taskmanagementsystem.dto.response.PageResponse;
import org.springframework.data.domain.Pageable;

public interface ICommentService {
    PageResponse<CommentDTO> getAllComments(CommentDTO taskDto, Pageable pageable);

    CommentDTO getCommentById(Long commentId);

    CommentDTO addComment(CommentDTO commentDto);

    CommentDTO updateComment(CommentDTO commentDto);

    void deleteComment(Long commentId);
}
