package com.guava.authms.service;

import com.guava.authms.dto.UserRequestDto;
import com.guava.authms.dto.UserResponseDto;
import com.guava.authms.entity.UserEntity;
import com.guava.authms.mapper.UserMapper;
import com.guava.authms.repository.DeliveryUserRepo;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeliveryAppUserService implements UserDetailsService {

    private final DeliveryUserRepo userRepo;
    private final UserMapper userMapper;

    public UserResponseDto addUser(UserRequestDto userDto) {

        UserEntity createdUser = userRepo.save(userMapper.dtoToEntity(userDto));
        log.info("User created successfully. User Id: " + createdUser.getUserid());
        return userMapper.entityToDto(createdUser);
    }

    public Optional<UserEntity> getUserResponseDto(String userName) {
        return userRepo.findUserEntityByUsername(userName);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUserResponseDto(username)
                .map(userMapper::appUserToUserDetails)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format("Username %s not found", username)));
    }
}
