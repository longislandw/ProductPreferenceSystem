package com.annservice.product_preference_api.security.userdetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.annservice.product_preference_api.entity.UserInfo;
import com.annservice.product_preference_api.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

        @Autowired
        private UserRepository userRepository;

        @Override
        public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
                // 查詢使用者
                UserInfo user = userRepository.findById(userName)
                                .orElseThrow(() -> new UsernameNotFoundException("使用者不存在: " + userName));

                return new UserDeatailsImpl(user);
        }
}