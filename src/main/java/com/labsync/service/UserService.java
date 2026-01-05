package com.labsync.service;

import com.labsync.dto.UserRequestDTO;
import com.labsync.dto.UserResponseDTO;
import com.labsync.entity.User;
import com.labsync.exception.DuplicateResourceException;
import com.labsync.exception.ResourceNotFoundException;
import com.labsync.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepo;

    public UserResponseDTO createUser(UserRequestDTO dto){
        if(userRepo.existsByEmail(dto.getEmail())){
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setPhone(dto.getPhone());
        user.setRole(dto.getRole());

        User saved = userRepo.save(user);

        return mapToDTO(saved);
    }

    public List<UserResponseDTO> getAllUsers(){
        return userRepo.findAll().
                stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public UserResponseDTO getUserById(Long id){
        User user = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return mapToDTO(user);
    }

    public UserResponseDTO updateUser(Long id, UserRequestDTO dto){
        User user = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: "+ id));

        if(!user.getEmail().equals(dto.getEmail()) &&
            userRepo.existsByEmail(dto.getEmail())){
            throw new DuplicateResourceException("Email already exists");
        }

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());

        if(dto.getPassword() != null && !dto.getPassword().isEmpty()){
            user.setPassword(dto.getPassword());
        }

        user.setPhone(dto.getPhone());
        user.setRole(dto.getRole());

        User updated = userRepo.save(user);

        return mapToDTO(updated);
    }

    public void deleteUser(Long id){
        if(!userRepo.existsById(id)){
            throw new ResourceNotFoundException("User not found with id: "+ id);
        }
        userRepo.deleteById(id);
    }

    public UserResponseDTO mapToDTO(User user){
        UserResponseDTO dto = new UserResponseDTO();

        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setRole(user.getRole());

        return dto;
    }
}
