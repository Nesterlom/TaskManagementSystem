package com.taskmanagementsystem.taskmanagementsystem.service;

import com.taskmanagementsystem.taskmanagementsystem.dto.CommentDTO;
import com.taskmanagementsystem.taskmanagementsystem.dto.response.PageResponse;
import com.taskmanagementsystem.taskmanagementsystem.entity.Comment;
import com.taskmanagementsystem.taskmanagementsystem.exception.exceptions.NotFoundException;
import com.taskmanagementsystem.taskmanagementsystem.interfaces.ICommentService;
import com.taskmanagementsystem.taskmanagementsystem.repository.CommentRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService implements ICommentService {
    private final ModelMapper modelMapper;

    private final CommentRepo commentRepo;

    public PageResponse<CommentDTO> getAllComments(CommentDTO commentDto , Pageable pageable) {
        ExampleMatcher customExampleMatcher = ExampleMatcher.matching()
                .withMatcher("text", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

        Comment exampleComment = new Comment();
        if(commentDto != null) {
            exampleComment.setText(commentDto.getText());
        }

        Example<Comment> example = Example.of(exampleComment, customExampleMatcher);
        Page<Comment> result = commentRepo.findAll(example, pageable);

        return new PageResponse<CommentDTO>(
                result.getContent().stream().map(this::convertToDto).toList(),
                result.getSize(),
                result.getNumber(),
                result.getTotalElements()
        );
    }

    public CommentDTO getCommentById(Long commentId) {
        return convertToDto(commentRepo.getCommentById(commentId).orElseThrow(() -> new NotFoundException("not found comment")));
    }

    public CommentDTO addComment(CommentDTO commentDto) {
        Comment comment = convertToEntity(commentDto);

        return convertToDto(commentRepo.save(comment));
    }

    public CommentDTO updateComment(CommentDTO commentDto){
        Comment comment = convertToEntity(commentDto);
        if(commentRepo.findById(comment.getId()).isEmpty()){
            throw new NotFoundException("Cant find task with such id or you haven't passed any id");
        }

        return convertToDto(commentRepo.save(convertToEntity(commentDto)));
    }

    public void deleteComment(Long commentId) {
        Comment comment = commentRepo.findById(commentId).orElseThrow(() -> new NotFoundException("Task with such id cant be found"));
        commentRepo.delete(comment);
    }

    private Comment convertToEntity(CommentDTO commentDto) throws ParseException {
        return modelMapper.map(commentDto, Comment.class);
    }

    private CommentDTO convertToDto(Comment comment) throws ParseException {
        return modelMapper.map(comment, CommentDTO.class);
    }
}
