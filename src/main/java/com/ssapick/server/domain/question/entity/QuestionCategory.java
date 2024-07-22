package com.ssapick.server.domain.question.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "question_category")
public class QuestionCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "question_category_id", nullable = false)
	private Long id;

	@Column(nullable = false)
	private String name;

	private String thumbnail;

	@Builder
	public QuestionCategory(Long id, String name) {
		this.id = id;
		this.name = name;
	}
}
