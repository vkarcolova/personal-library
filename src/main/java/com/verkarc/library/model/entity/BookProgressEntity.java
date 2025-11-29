package com.verkarc.library.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bookProgresses")
@Builder
public class BookProgressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_progress_id_seq")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    private BookEntity book;

    @Column(columnDefinition = "TEXT")
    private String note;

    private Integer progressPages;

    private Integer totalPages;

    private Integer rating;

}


