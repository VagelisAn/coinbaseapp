package com.server.coinbase.service.internal;


import com.server.coinbase.dto.UserDTO;


import java.util.List;

public interface UserService {
    public List<UserDTO> getAllUsers();

    public UserDTO getUserById(Long id);

    public UserDTO createUser(UserDTO userDTO);

    public UserDTO updateUser(Long id, UserDTO userDTO);

    public void deleteUser(Long id);
}
