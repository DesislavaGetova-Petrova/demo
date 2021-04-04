package desico.project.service;

import desico.project.model.entity.UserRoleEntity;
import desico.project.model.enums.UserRole;

import java.util.Optional;

public interface UserRollEntityService {
    UserRoleEntity findByRole(UserRole role);
}
