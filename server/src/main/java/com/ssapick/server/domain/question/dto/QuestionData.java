package com.ssapick.server.domain.question.dto;

import com.ssapick.server.domain.question.entity.Question;
import com.ssapick.server.domain.user.entity.User;
import lombok.Data;

import java.util.Objects;

public class QuestionData {

    @Data
    public static class Search {
        private Long questionId;
        private Integer banCount;
        private Long questionCategoyId;
        private String questionCategoryName;
        private Long authorId;
        private String author;
        private String content;

        public static Search fromEntity(Question question) {
            Search search = new Search();
            search.questionId = question.getId();
            search.banCount = question.getBanCount();
//			search.questionCategoyId = question.getQuestionCategory().getId();
//			search.questionCategoryName = question.getQuestionCategory().getName();
            search.authorId = question.getAuthor().getId();
            search.author = question.getAuthor().getName();
            search.content = question.getContent();
            return search;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            Search search = (Search) o;
            return Objects.equals(questionId, search.questionId) && Objects.equals(banCount,
                    search.banCount) && Objects.equals(questionCategoyId, search.questionCategoyId)
                    && Objects.equals(questionCategoryName, search.questionCategoryName) && Objects.equals(
                    authorId, search.authorId) && Objects.equals(author, search.author) && Objects.equals(
                    content, search.content);
        }

        @Override
        public int hashCode() {
            return Objects.hash(questionId, banCount, questionCategoyId, questionCategoryName, authorId, author,
                    content);
        }
    }

    @Data
    public static class AddRequest {
        private User user;
        private Long categoryId;
        private String content;
    }

}
