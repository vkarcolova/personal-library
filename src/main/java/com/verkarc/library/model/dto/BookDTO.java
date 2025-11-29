package com.verkarc.library.model.dto;

import com.verkarc.library.model.enums.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDTO {
    private Long id;
    private String title;
    private AuthorDTO author;
    private GenreDTO genre;
    private BookStatus status;
    private String coverUrl;
    private Integer progressPages;
    private Integer totalPages;
    private Integer rating;
    private String note;
}
