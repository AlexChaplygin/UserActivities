package com.alex.che.useractivities.service;

import com.alex.che.useractivities.dto.UserDTO;
import com.alex.che.useractivities.entity.UserEntity;
import com.alex.che.useractivities.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<UserDTO> findAllUsers() {
        List<UserEntity> userEntities = new ArrayList<>(userRepository.findAll());
        return mapper.map(userEntities, new TypeToken<List<UserDTO>>() {
        }.getType());
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public UserDTO findById(Long id) {
        UserEntity userEntity =
                userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
        return mapper.map(userEntity, UserDTO.class);
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        UserEntity userEntity = userRepository.save(mapper.map(userDTO, UserEntity.class));
        return mapper.map(userEntity, UserDTO.class);
    }

    @Override
    public void deleteUser(UserDTO userDTO) {
        userRepository.delete(mapper.map(userDTO, UserEntity.class));
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<UserDTO> findByUserIdAndUserActivityLogEntitiesActivityDateBetween(Date start, Date end) {
        List<UserEntity> userEntities = userRepository.findByUserIdAndUserActivityLogEntitiesActivityDateBetween(start, end);
        List<UserDTO> userDTOS = new ArrayList<>();

        mapper.map(userEntities, userDTOS);

        return userDTOS;
    }
}
