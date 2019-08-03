package com.alex.che.useractivities.service;

import com.alex.che.useractivities.dto.UserDTO;

import java.util.Date;
import java.util.List;

public interface UserService {
    List<UserDTO> findAllUsers();

    UserDTO findById(Long id);

    UserDTO saveUser(UserDTO userDTO);

    void deleteUser(UserDTO userDTO);

    List<UserDTO> findByUserIdAndUserActivityLogEntitiesActivityDateBetween(Date start, Date end);
}
