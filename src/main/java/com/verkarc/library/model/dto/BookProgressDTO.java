package com.verkarc.library.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookProgressDTO {
    private Long id;
    private Integer progressPages;
    private Integer totalPages;
    private Integer rating;
    private String note;
}
