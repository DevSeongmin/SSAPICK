package com.ssapick.server.domain.pick.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssapick.server.domain.pick.entity.Pick;

public interface PickRepository extends JpaRepository<Pick, Long> {

	/**
	 * 받은 Pick 조회
	 * @param userId
	 * @retrun {@link List<Pick>} Pick 리스트 반환 (존재하지 않으면, 빈 리스트 반환)
	 */
	@Query("SELECT p FROM Pick p JOIN FETCH p.sender JOIN FETCH p.question WHERE p.receiver.id = :userId")
	List<Pick> findAllByFromUserId(Long userId);

	/**
	 * 보낸 Pick 조회
	 * @param userId
	 * @return {@link List<Pick>} Pick 리스트 반환 (존재하지 않으면, 빈 리스트 반환)
	 */
	@Query("SELECT p FROM Pick p JOIN FETCH p.receiver JOIN FETCH p.question WHERE p.sender.id = :userId")
	List<Pick> findAllByToUserId(Long userId);



	@Query("SELECT p FROM Pick p JOIN FETCH p.hintOpens WHERE p.id = :pickId")
	Optional<Pick> findPickWithHintsById(Long pickId);

}
