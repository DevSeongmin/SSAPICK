package com.ssapick.server.core.support;

import com.ssapick.server.domain.user.entity.Campus;
import com.ssapick.server.domain.user.entity.Profile;
import com.ssapick.server.domain.auth.dto.MattermostData;
import com.ssapick.server.domain.user.entity.ProviderType;
import com.ssapick.server.domain.user.entity.User;
import com.ssapick.server.domain.user.repository.UserRepository;
import com.ssapick.server.domain.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@Import(UserService.class)
public abstract class AuthenticatedSupport {

	protected MattermostData.Request createMattermostRequest() {
		return new MattermostData.Request("test", "123456");
	}

	protected MattermostData.Response createMattermostResponseOne() {
		MattermostData.Response response = new MattermostData.Response();
		response.setNickname("이인준[광주_2반]");
		return response;
	}

	protected MattermostData.Response createMattermostResponseTwo() {
		MattermostData.Response response = new MattermostData.Response();
		response.setNickname("이인준[광주_2반_C211]");
		return response;
	}
    @MockBean
    private UserRepository userRepository;

    private AtomicLong atomicLong = new AtomicLong(1);

    protected User createUser() {
        User user = spy(User.createUser("test", "테스트 유저", 'M', ProviderType.KAKAO, "123456"));
        Profile profile = Profile.createProfile(user, (short) 1, createCampus(), "https://test-profile.com");
        when(user.getProfile()).thenReturn(profile);
        when(user.getId()).thenReturn(atomicLong.incrementAndGet());
        return user;
    }

    protected User createUser(String name) {
        User user = spy(User.createUser(name, name, 'M', ProviderType.KAKAO, "123456"));
        Profile profile = Profile.createProfile(user, (short) 1, createCampus(), "https://test-profile.com");
        when(user.getProfile()).thenReturn(profile);
        when(user.getId()).thenReturn(atomicLong.incrementAndGet());
        return user;
    }

    protected Campus createCampus() {
        return Campus.createCampus("광주", (short) 1, "자바 전공");
    }

    @BeforeEach
    public void setUp() {
        User user = createUser("test-user");
        atomicLong = new AtomicLong(1);
        when(userRepository.findByUsername(any())).thenReturn(Optional.of(user));
    }
}
