package com.verkarc.library;

import com.verkarc.library.model.entity.AuthorEntity;
import com.verkarc.library.model.entity.BookEntity;

public final class TestDataUtil {
    private TestDataUtil(){
    }

    public static AuthorEntity createTestAuthorA() {
        return AuthorEntity.builder()
                .name("Abigail Rose")
                .build();
    }

    public static AuthorEntity createTestAuthorB() {
        return AuthorEntity.builder()
                .name("Thomas Cronin")
                .build();
    }

    public static AuthorEntity createTestAuthorC() {
        return AuthorEntity.builder()
                .name("Jesse A Casey")
                .build();
    }

    public static BookEntity createTestBookA() {
        return BookEntity.builder()
                .title("The Shadow in the Attic")
                .author(createTestAuthorA())
                .build();
    }

    public static BookEntity createTestBookB() {
        return BookEntity.builder()
                .title("Beyond the Horizon")
                .author(createTestAuthorB())
                .build();
    }

    public static BookEntity createTestBookC() {
        return BookEntity.builder()
                .title("The Last Ember")
                .author(createTestAuthorC())
                .build();
    }
}