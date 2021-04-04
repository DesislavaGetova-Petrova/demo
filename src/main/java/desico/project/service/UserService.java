package desico.project.service;

import desico.project.model.entity.UserEntity;
import desico.project.model.enums.UserRole;
import desico.project.model.service.UserRegistrationServiceModel;

import java.util.List;

public interface UserService {

    void seedUsers();

    void registerAndLoginUser(UserRegistrationServiceModel serviceModel);

    boolean userNameExists(String username);

    List<String> findAllUserNames();

    void changeRole(String username, String role);
    void deleteRole(String username, String role);
    UserEntity findByName(String username);

}