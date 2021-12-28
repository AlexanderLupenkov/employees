package com.altech.employees.domain.mapper;

import com.altech.employees.domain.dto.StatusDto;
import com.altech.employees.domain.dto.UserDto;
import com.altech.employees.domain.entity.Status;
import com.altech.employees.domain.entity.User;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UserMapper {
    private final ModelMapper mapper;

    public UserDto toDto(User user) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
        return mapper.map(user, UserDto.class);
    }

    public User toEntity(UserDto userDto) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
        return mapper.map(userDto, User.class);
    }

    public List<UserDto> toDtoList(Iterable<User> products) {
        return ((List<User>) products).stream().map(this::toDto).collect(Collectors.toList());
    }
}
