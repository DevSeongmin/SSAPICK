package com.ssapick.server.domain.auth.controller;

import static com.epages.restdocs.apispec.ResourceDocumentation.*;
import static com.ssapick.server.core.constants.AuthConst.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.ResultActions;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.ssapick.server.core.configuration.SecurityConfig;
import com.ssapick.server.core.filter.JWTFilter;
import com.ssapick.server.core.properties.JwtProperties;
import com.ssapick.server.core.support.RestDocsSupport;
import com.ssapick.server.domain.auth.service.AuthService;

import jakarta.servlet.http.Cookie;

@DisplayName("인증 컨트롤러 테스트")
@WebMvcTest(
	value = AuthController.class,
	excludeFilters = {
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class),
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = JWTFilter.class),
	}
)
@Import({JwtProperties.class})
class AuthControllerTest extends RestDocsSupport {
	@MockBean
	private AuthService authService;

	@Test
	@DisplayName("로그아웃 정상 테스트")
	void 로그아웃_정상_테스트() throws Exception {
		// * GIVEN: 이런게 주어졌을 때
		String accessToken = "accessToken";
		String refreshToken = "refreshToken";

		// * WHEN: 이걸 실행하면
		ResultActions action = this.mockMvc.perform(post("/api/v1/auth/sign-out")
			.header("Authorization", "Bearer " + accessToken)
			.cookie(new Cookie(REFRESH_TOKEN, refreshToken))
		);
	}

	@Test
	@DisplayName("회원탈퇴에 성공하면 성공 응답 반환")
	void successDeleteUser() throws Exception {
		// * GIVEN: 이런게 주어졌을 때
		// * WHEN: 이걸 실행하면
		ResultActions action = this.mockMvc.perform(delete("/api/v1/auth"));

		// * THEN: 이런 결과가 나와야 한다
		action.andExpect(status().isNoContent())
			.andDo(restDocs.document(resource(
				ResourceSnippetParameters.builder()
					.tag("deleteUser")
					.summary("회원 탈퇴 API")
					.description("회원을 삭제한다.")
					.responseFields(empty())
					.build()
			)));

		verify(authService).deleteUser(any());
	}

}