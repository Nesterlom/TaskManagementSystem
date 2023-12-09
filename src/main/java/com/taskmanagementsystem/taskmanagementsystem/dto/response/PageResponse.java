package com.taskmanagementsystem.taskmanagementsystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse<T> {
    List<T> content;
    int pageSize;
    int pageNumber;
    long numberOfElements;
}