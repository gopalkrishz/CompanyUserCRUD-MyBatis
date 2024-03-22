package com.company.init.mapper;

import com.company.init.DTO.UserDTO;
import com.company.init.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserDTOMapper {

    UserDTO modelToDTO(User user);
    User dtoToModel(UserDTO userDTO);
}
