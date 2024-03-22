package com.example.PayRollTracker.service;

import com.example.PayRollTracker.dto.UserDTO;
import com.example.PayRollTracker.dto.UserInfoDTO;
import com.example.PayRollTracker.model.UserEntity;
import com.example.PayRollTracker.model.UserInfo;
import com.example.PayRollTracker.repo.UserInfoRepo;
import com.example.PayRollTracker.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final UserInfoRepo userInfoRepo;
    private final ModelMapper modelMapper;

    //admin functions
    //save user
    // Save user
    public ResponseEntity<?> saveUser(UserDTO userDto) {
        if (userRepo.findByUsername(userDto.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("User with this username already exists");
        }
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class); // Convert UserDTO to UserEntity
        userRepo.save(userEntity);
        return ResponseEntity.ok("User registered successfully");
    }

    // Find by username
    public UserEntity findByUsername(String username) {
        return userRepo.findByUsername(username).orElse(null);
    }

    // Find all users
    public List<UserDTO> findAllUsers() {
        List<UserEntity> userEntities = userRepo.findAll();
        return userEntities.stream()
                .map(userEntity -> modelMapper.map(userEntity, UserDTO.class)) // Convert UserEntity to UserDTO
                .collect(Collectors.toList());
    }
    // update user
    public Optional<UserDTO> updateUser(Long id, UserDTO userDto) {
        Optional<UserEntity> existingUserOptional = userRepo.findById(id);
        if (existingUserOptional.isPresent()) {
            UserEntity existingUser = existingUserOptional.get();

            // Update existingUser with data from userDto
            existingUser.setUsername(userDto.getUsername());
            existingUser.setPassword(userDto.getPassword());
            existingUser.setRole(userDto.getRole());

            // Save the updated user
            UserEntity updatedUser = userRepo.save(existingUser);

            // Convert the updated user back to UserDTO
            UserDTO updatedUserDto = modelMapper.map(updatedUser, UserDTO.class);

            return Optional.of(updatedUserDto);
        } else {
            return Optional.empty();
        }
    }

    //delete user
    public ResponseEntity<?> deleteUser(Long id) {
        if (!userRepo.existsById(id)) {
            throw new IllegalStateException("User with id " + id + " does not exist");
        }
        userRepo.deleteById(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    // user functions
    private UserEntity getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<UserEntity> userOptional = userRepo.findByUsername(username);
        return userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

    // log out
    public void logout() {
        SecurityContextHolder.clearContext();
    }

    // change password
    public ResponseEntity<?> changePassword(String oldPassword, String newPassword) {
        UserEntity user = getCurrentUser();
        if (!user.getPassword().equals(oldPassword)) {
            return ResponseEntity.badRequest().body("Old password is incorrect");
        }
        user.setPassword(newPassword);
        userRepo.save(user);
        return ResponseEntity.ok("Password changed successfully");
    }

    // add user info
    public ResponseEntity<?> addUserInfo(UserInfoDTO userInfoDto) {
        // Get the current user
        UserEntity currentUser = getCurrentUser();

        // Check if the user already has user info
        if (currentUser.getUserInfo() != null) {
            return ResponseEntity.badRequest().body("User information already exists");
        }

        // Convert UserInfoDTO to UserInfo
        UserInfo userInfo = modelMapper.map(userInfoDto, UserInfo.class);

        // Set the user for the user info
        userInfo.setUser(currentUser);

        // Save the user info
        userInfoRepo.save(userInfo);

        return ResponseEntity.ok("User information added successfully");
    }

    // user updates their info
    public ResponseEntity<?> updateUserInfo(UserDTO userDto) {
        // Get the current user
        UserEntity currentUser = getCurrentUser();

        // Check if the user has user info
        if (currentUser.getUserInfo() == null) {
            return ResponseEntity.badRequest().body("User information does not exist");
        }

        // Convert UserDTO to UserInfo
        UserInfo userInfo = modelMapper.map(userDto, UserInfo.class);

        // Set the user for the user info
        userInfo.setUser(currentUser);

        // Save the user info
        userInfoRepo.save(userInfo);

        return ResponseEntity.ok("User information updated successfully");
    }

}
