package com.example.demo.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/*@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private UserRepository underTest;

    @Test
    void itShouldCheckIfUserEmailExists() {
        //given
        String email = "aidatynybekkyzy@gmail.com";
        User user = new User(
                "Aida",
                "Tynybek kyzy",
                "password",
                email,
                "@AidaTyn",
                "Osh",
                LocalDateTime.now());
        underTest.save(user);
        //when
        boolean expected = underTest.selectExistsEmail(email);

        //then
        assertThat(expected).isTrue();
    }
}*/