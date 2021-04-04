package desico.project.service;

import desico.project.model.entity.UserRoleEntity;
import desico.project.model.enums.UserRole;
import desico.project.repository.UserRoleRepository;
import desico.project.repository.VideoRepository;
import desico.project.service.impl.UserRollEntityServiceImpl;
import desico.project.service.impl.VideoServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserRollEntityServiceTest {
    private UserRollEntityServiceImpl userRollEntityService;
    @Mock
    UserRoleRepository mockUserRoleRepository;

    @BeforeEach
    public  void init() {
        userRollEntityService=new UserRollEntityServiceImpl(mockUserRoleRepository);
    }
    @Test
    void findByRoleTest() {
        UserRoleEntity userRoleEntity=new UserRoleEntity();
        userRoleEntity.setRole(UserRole.USER);
        Mockito.when(mockUserRoleRepository.findByRole(UserRole.USER)).thenReturn(Optional.of(userRoleEntity));
        Assertions.assertEquals(userRoleEntity, userRollEntityService.findByRole(UserRole.USER));

    }

}
