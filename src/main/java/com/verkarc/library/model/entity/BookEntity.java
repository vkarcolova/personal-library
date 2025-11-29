package com.verkarc.library.model.entity;

import com.verkarc.library.model.enums.BookStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
@Builder
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_id_seq")
    private Long id;

    private String title;

    @ManyToOne(cascade = CascadeType.ALL) //kazda zmena pre autora zmeni aj knihy
    @JoinColumn(name = "author_id")
    private AuthorEntity author;

    @OneToOne
    @JoinColumn(name = "genre_id")
    private GenreEntity genre;

    @Enumerated(EnumType.STRING)
    private BookStatus status;

    private String coverUrl;

    @OneToOne
    @JoinColumn(name = "book_progress_id")
    private BookProgressEntity bookProgress;

}


