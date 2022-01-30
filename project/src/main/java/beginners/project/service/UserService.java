package beginners.project.service;

import beginners.project.domain.User;
import beginners.project.domain.UserDao;
import beginners.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public String login(String userId, String password) {

        if (userRepository.findByUserId(userId) == null) {
            return "존재하지 않는 회원입니다.";
        } else {
            User user = userRepository.findByUserId(userId);
            if (password == user.getPassword()) {
                return user.getUserId() + "님 반갑습니다.";
            } else {
                return "비밀번호 혹은 아이디가 일치하지 않습니다.";
            }
        }
    }
}
