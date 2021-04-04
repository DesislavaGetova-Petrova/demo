package desico.project.service.impl;

import desico.project.model.entity.UserRoleEntity;
import desico.project.model.enums.UserRole;
import desico.project.repository.UserRoleRepository;
import desico.project.service.UserRollEntityService;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserRollEntityServiceImpl implements UserRollEntityService {
    private final UserRoleRepository userRoleRepository;

    public UserRollEntityServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public UserRoleEntity findByRole(UserRole role) {
        return userRoleRepository.findByRole(role).orElse(null);
    }
}
