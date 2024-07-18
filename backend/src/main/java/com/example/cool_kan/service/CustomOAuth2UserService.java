package com.example.cool_kan.service;

import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import com.example.cool_kan.model.User;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import com.example.cool_kan.repository.UserRepository;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauth2User = super.loadUser(userRequest);

        String email = oauth2User.getAttribute("email");
        String name = oauth2User.getAttribute("name");
        String pictureUrl = oauth2User.getAttribute("picture");
        User user = userRepository.findByEmail(email).orElseGet(() -> new User(email, name, pictureUrl));
        user.setName(name);
        user.setPictureUrl(pictureUrl);

        userRepository.save(user);

        return oauth2User;
    }
}
