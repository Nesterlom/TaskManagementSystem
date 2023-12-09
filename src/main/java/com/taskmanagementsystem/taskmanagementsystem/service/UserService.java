package com.taskmanagementsystem.taskmanagementsystem.service;

import com.taskmanagementsystem.taskmanagementsystem.dto.UserDTO;
import com.taskmanagementsystem.taskmanagementsystem.dto.response.PageResponse;
import com.taskmanagementsystem.taskmanagementsystem.entity.User;
import com.taskmanagementsystem.taskmanagementsystem.exceptions.NotFoundException;
import com.taskmanagementsystem.taskmanagementsystem.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.expression.ParseException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{

    private final ModelMapper modelMapper;
    private final UserRepo userRepo;
    private final BCryptPasswordEncoder encoder;

    public PageResponse<UserDTO> getAllUsers(UserDTO userDto , Pageable pageable) {
        ExampleMatcher customExampleMatcher = ExampleMatcher.matching()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("surname", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("email", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

        User exampleUser = new User();
        exampleUser.setEmail(userDto.getEmail());
        exampleUser.setName(userDto.getName());
        exampleUser.setSurname(userDto.getSurname());

        Example<User> example = Example.of(exampleUser, customExampleMatcher);
        Page<User> result = userRepo.findAll(example, pageable);

        return new PageResponse<UserDTO>(
                result.getContent().stream().map(this::convertToDto).toList(),
                result.getSize(),
                result.getNumber(),
                result.getTotalElements()
        );
    }

    public UserDTO findById(Long userId) {
        return convertToDto(userRepo.findById(userId).orElseThrow(() -> new NotFoundException("Cant find user with such id")));
    }

    public UserDTO updateUser(UserDTO userDto, Principal principal) {
        User user = userRepo.findByEmail(principal.getName()).orElseThrow(() -> new NotFoundException("Cant find user"));
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        return convertToDto(userRepo.save(user));
    }

    public UserDTO updateUserEmail(UserDTO userDto, Principal principal) {
        User user = userRepo.findByEmail(principal.getName()).orElseThrow(() -> new NotFoundException("Cant find user"));
        user.setEmail(userDto.getEmail());

        return convertToDto(userRepo.save(user));
    }

    public UserDTO updateUserPassword(UserDTO userDto, Principal principal) {
        User user = userRepo.findByEmail(principal.getName()).orElseThrow(() -> new NotFoundException("Cant find user"));
        user.setPassword(encoder.encode(userDto.getPassword()));
        return convertToDto(userRepo.save(user));
    }

    public void deleteUser(Long userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new NotFoundException("User with such id cant be found"));
        userRepo.delete(user);
    }

    public void deleteCurrentUser(Principal principal) {
        User user = userRepo.findByEmail(principal.getName()).orElseThrow(() -> new NotFoundException("User with such login cant be found"));
        userRepo.delete(user);
    }

    private UserDTO convertToDto(User user) throws ParseException {
        return modelMapper.map(user, UserDTO.class);
    }
}
