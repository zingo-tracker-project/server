package com.tracker.server;

import com.tracker.server.dto.user.req.UserUpdateReqDTO;
import com.tracker.server.dto.user.res.UserInfoResDTO;
import com.tracker.server.entity.user.User;
import com.tracker.server.repository.user.UserRepository;
import com.tracker.server.service.user.UserService;
import com.tracker.server.utils.CustomException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class UserTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void updateUser_성공_테스트() {
        // given
        UserUpdateReqDTO updateDto = new UserUpdateReqDTO();
        updateDto.setUserId("1234");
        updateDto.setUserNm("가가가");

        User updatedUser = new User();
        updatedUser.setUserId("1234");
        updatedUser.setUserNm("가가가");

        // stub
        Mockito.when(userRepository.updateUserCustom(updateDto))
                .thenReturn(Optional.of(updatedUser));

        // when
        UserInfoResDTO result = userService.updateUser(updateDto);

        // then
        assertThat(result.getUserNm()).isEqualTo("가가가");
    }

    @Test
    void updateUser_회원없음_예외발생() {
        // given
        UserUpdateReqDTO updateDto = new UserUpdateReqDTO();
        updateDto.setUserId("9999");

        Mockito.when(userRepository.updateUserCustom(updateDto))
                .thenReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> userService.updateUser(updateDto))
                .isInstanceOf(CustomException.class)
                .hasMessage("없는 회원입니다.");
    }
}
