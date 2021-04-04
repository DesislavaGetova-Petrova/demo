package desico.project.service;

import desico.project.model.entity.ChapterNameEntity;
import desico.project.model.entity.UserEntity;
import desico.project.model.entity.UserRoleEntity;
import desico.project.model.entity.VideoEntity;
import desico.project.model.enums.UserRole;
import desico.project.repository.UserRepository;
import desico.project.repository.UserRoleRepository;
import desico.project.service.impl.ProjectUserService;
import desico.project.service.impl.UserServiceImpl;
import desico.project.service.impl.VideoServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    private UserServiceImpl userService;

    @Mock
    UserRoleRepository mockUserRoleRepository;
    @Mock
    UserRepository mockUserRepository;
    @Mock
    PasswordEncoder mockPasswordEncoder;
    @Mock
    PasswordEncoder mockEncoder;
    @Mock
    ModelMapper mockModelMapper;
    @Mock
    ProjectUserService mockProjectUserService;

    @BeforeEach
    public  void init() {
        mockModelMapper = new ModelMapper();
        userService=new UserServiceImpl(mockUserRoleRepository,mockUserRepository,mockPasswordEncoder,mockEncoder,mockModelMapper,mockProjectUserService);
    }


    @Test
    void userNameExistTest(){
        UserRoleEntity userRoleEntity=new UserRoleEntity();
        userRoleEntity.setRole(UserRole.USER);
        UserEntity user=new UserEntity();
        user.setPassword("123456").setEmail("d@d").setUsername("pesho").setRoles(List.of(userRoleEntity));
        assertEquals(false,userService.userNameExists("desi"));
    }
    @Test
    void findAllUserNamesTest(){
        UserRoleEntity userRoleEntity=new UserRoleEntity();
        userRoleEntity.setRole(UserRole.USER);
        UserEntity user=new UserEntity();
        user.setPassword("123456").setEmail("d@d").setUsername("pesho").setRoles(List.of(userRoleEntity));
        Mockito.when(mockUserRepository.findAllUserNames()).thenReturn(List.of(user.getUsername()));
        assertEquals(1,userService.findAllUserNames().size());
    }
    @Test
    void changeRoleTest(){
        UserRoleEntity userRoleEntity1=new UserRoleEntity();
        userRoleEntity1.setRole(UserRole.USER);
        UserEntity user=new UserEntity();
        UserRoleEntity userRoleEntity2=new UserRoleEntity();
        userRoleEntity2.setRole(UserRole.ADMIN);
        user.setPassword("123456").setEmail("d@d").setUsername("pesho").setRoles(List.of(userRoleEntity1,userRoleEntity2));
        Mockito.when(mockUserRepository.findByUsername("pesho")).thenReturn(Optional.of(user));
        userService.changeRole(user.getUsername(),UserRole.ADMIN.name());
        Mockito.verify(mockUserRepository,Mockito.times(0)).save(user);
    }

    @Test
    void deleteRoleTest(){
        UserRoleEntity userRoleEntity1=new UserRoleEntity();
        userRoleEntity1.setRole(UserRole.USER);
        UserRoleEntity userRoleEntity2=new UserRoleEntity();
        userRoleEntity2.setRole(UserRole.ADMIN);
        UserEntity user=new UserEntity();
        user.setPassword("123456").setEmail("d@d").setUsername("pesho").setRoles(List.of(userRoleEntity1,userRoleEntity2));
        Mockito.when(mockUserRepository.findByUsername("pesho")).thenReturn(Optional.of(user));
        userService.deleteRole(user.getUsername(),UserRole.ADMIN.name());
        Mockito.verify(mockUserRepository).save(user);
    }

}
