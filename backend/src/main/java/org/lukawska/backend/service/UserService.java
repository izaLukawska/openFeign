package org.lukawska.backend.service;

import lombok.RequiredArgsConstructor;
import org.lukawska.backend.dto.UserDto;
import org.lukawska.backend.entity.User;
import org.lukawska.backend.exception.ExceptionEnum;
import org.lukawska.backend.exception.RestException;
import org.lukawska.backend.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	public UserDto getUser(Long id){
		return userRepository
				.findById(id)
				.map(this::mapToDto).
				orElseThrow(() -> new RestException(ExceptionEnum.USER_NOT_FOUND));
	}

	public User createUser(User user){
		try {
			return userRepository.save(User.builder()
					.id(user.getId())
					.username(user.getUsername())
					.build());
		} catch (DataIntegrityViolationException e){
			throw new RestException(ExceptionEnum.USER_ALREADY_EXISTS);
		}
	}

	private UserDto mapToDto(User user){
		return UserDto.builder()
				.id(user.getId())
				.username(user.getUsername())
				.build();
	}
}
