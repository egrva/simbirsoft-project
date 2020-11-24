package ru.aegorova.simbirsoftproject.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.aegorova.simbirsoftproject.dto.SignUpDto;
import ru.aegorova.simbirsoftproject.models.Person;
import ru.aegorova.simbirsoftproject.models.User;
import ru.aegorova.simbirsoftproject.repositories.UserRepository;
import ru.aegorova.simbirsoftproject.security.Role;

@Service
public class SignUpServiceImpl implements SignUpService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public SignUpServiceImpl(PasswordEncoder passwordEncoder
            , UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public void signUp(SignUpDto signUpDto) {
        Person person = new Person() {{
            setBirthDate(signUpDto.getBirthDate());
            setFirstName(signUpDto.getFirstName());
            setLastName(signUpDto.getLastName());
            setMiddleName(signUpDto.getMiddleName());
        }};
        User user = User.builder()
                .login(signUpDto.getLogin())
                .password(passwordEncoder.encode(signUpDto.getPassword()))
                .role(Role.valueOf(signUpDto.getRole()))
                .person(person)
                .build();
        userRepository.save(user);
    }
}
