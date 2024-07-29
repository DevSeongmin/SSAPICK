package com.ssapick.server.domain.question.service;

import com.ssapick.server.core.exception.BaseException;
import com.ssapick.server.core.exception.ErrorCode;
import com.ssapick.server.domain.question.dto.QuestionData;
import com.ssapick.server.domain.question.entity.Question;
import com.ssapick.server.domain.question.entity.QuestionBan;
import com.ssapick.server.domain.question.entity.QuestionCategory;
import com.ssapick.server.domain.question.entity.QuestionRegistration;
import com.ssapick.server.domain.question.repository.QuestionBanRepository;
import com.ssapick.server.domain.question.repository.QuestionCategoryRepository;
import com.ssapick.server.domain.question.repository.QuestionRegistrationRepository;
import com.ssapick.server.domain.question.repository.QuestionRepository;
import com.ssapick.server.domain.user.entity.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionService {
    private final EntityManager em;
    private final QuestionRepository questionRepository;
    private final QuestionRegistrationRepository questionRegistrationRepository;
    private final QuestionBanRepository questionBanRepository;
    private final QuestionCategoryRepository questionCategoryRepository;

    /**
     * 모든질문 조회
     *
     * @return
     */
    public List<QuestionData.Search> searchQuestions() {
        List<Question> all = questionRepository.findAll();
        return all.stream()
                .map(QuestionData.Search::fromEntity)
                .toList();
    }

    /**
     * 카테고리별 질문 조회
     *
     * @param questionCategoryId
     * @return
     */
    public List<QuestionData.Search> searchQuestionsByCategory(Long questionCategoryId) {
        return questionRepository.findQuestionsByCategory_Id(questionCategoryId)
            .stream().map(QuestionData.Search::fromEntity)
            .toList();
    }

    /**
     * 질문 ID로 질문 조회
     *
     * @param questionId
     * @return
     */
    public QuestionData.Search searchQuestionByQuestionId(Long questionId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUD_QUESTION));

        if (question.isDeleted()) {
            throw new BaseException(ErrorCode.DELETED_QUESTION);
        }
        return QuestionData.Search.fromEntity(question);
    }

    /**
     * 질문 생성 요청
     *
     * @param create
     */
    public void createQuestion(User user, QuestionData.Create create) {

        QuestionCategory category = questionCategoryRepository.findById(create.getCategoryId())
                .orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND_QUESTION_CATEGORY));

        questionRegistrationRepository.save(QuestionRegistration.of(user, category, create.getContent()));
    }

    /**
     * 질문 차단
     *
     * @param user
     * @param questionId
     */
    public void banQuestion(User user, Long questionId) {

        Question question = questionRepository.findById(questionId)
            .orElseThrow(() ->
                new BaseException(ErrorCode.NOT_FOUD_QUESTION)
            );

        questionBanRepository.findQByUserIdAndQuestionId(user.getId(), questionId)
            .ifPresent(q -> {
                throw new BaseException(ErrorCode.EXIST_QUESTION_BAN);
            });

        questionBanRepository.save(QuestionBan.of(user, question));
    }

    /**
     * 내가 차단한 질문 조회
     *
     * @param userId
     * @return
     */
    public List<QuestionData.Search> searchBanQuestions(Long userId) {
        return questionBanRepository.findQuestionBanByUserId(userId)
                .stream()
                .map(QuestionData.Search::fromEntity)
                .toList();

    }

    /**
     * 내가 지목받은 픽에 대한 질문 랭킹
     *
     * @param userId
     * @return
     */
    public List<QuestionData.Search> searchQuestionsRank(Long userId) {
        return questionRepository.findRanking(userId)
                .stream()
                .map(QuestionData.Search::fromEntity)
                .toList();
    }

    /**
     * 사용자에게 뿌려줄 질문 리스트 (사용자가 차단하지 않은)
     * @param user
     * @return
     */
    public List<QuestionData.Search> searchQeustionList(User user) {

        List<QuestionData.Search> searches = new ArrayList<>(this.searchQuestions()); // 변경 가능하도록 ArrayList로 변환
        List<QuestionData.Search> banQuestions = this.searchBanQuestions(user.getId());

        searches.removeAll(banQuestions);

        return searches;
    }
}
