package desico.project.service;

import desico.project.model.entity.*;
import desico.project.model.enums.UserRole;
import desico.project.model.service.CommentServiceModel;
import desico.project.model.service.LogServiceModel;
import desico.project.model.view.CommentViewModel;
import desico.project.model.view.VideoViewModel;
import desico.project.repository.LogRepository;
import desico.project.service.impl.CommentServiceImpl;
import desico.project.service.impl.LogServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class LogServiceTest {
    private LogServiceImpl logService;

    @Mock
    LogRepository mockLogRepository;
    @Mock
    UserService mockUserService;
    @Mock
    ModelMapper mockModelMapper;

    @BeforeEach
    public  void init() {
        mockModelMapper=new ModelMapper();
        logService=new LogServiceImpl(mockLogRepository,mockUserService,mockModelMapper);
    }
    @Test
    public void createLogTest() {
        UserRoleEntity userRoleEntity=new UserRoleEntity();
        userRoleEntity.setRole(UserRole.USER);
        UserEntity userEntity=new UserEntity();
        userEntity.setRoles(List.of(userRoleEntity)).setUsername("Pesho").setEmail("d@d").setPassword("123456");
        LogEntity logEntity = new LogEntity()
                .setUserEntity(userEntity)
                .setAction("viewAll")
                .setDateTime(LocalDateTime.now());
        Assertions.assertThrows(
                NullPointerException.class, () -> {
                   logService.createLog("viewAll");
                }
        );
    }
    @Test
    public void findAllLogsTest() {
        UserRoleEntity userRoleEntity=new UserRoleEntity();
        userRoleEntity.setRole(UserRole.USER);
        UserEntity userEntity=new UserEntity();
        userEntity.setRoles(List.of(userRoleEntity)).setUsername("Pesho").setEmail("d@d").setPassword("123456");
        LogEntity logEntity = new LogEntity()
                .setUserEntity(userEntity)
                .setAction("viewAll")
                .setDateTime(LocalDateTime.now());

        List<LogServiceModel> logServiceModels = new ArrayList<>();
        logServiceModels.add(this.mockModelMapper.map(logEntity, LogServiceModel.class));
        Mockito.when(mockLogRepository.findAll()).thenReturn(List.of(logEntity));
        Assertions.assertEquals(1,logService.findAllLogs().size());

    }
    @Test
    void deleteLogTest(){
        UserRoleEntity userRoleEntity=new UserRoleEntity();
        userRoleEntity.setRole(UserRole.USER);
        UserEntity userEntity=new UserEntity();
        userEntity.setRoles(List.of(userRoleEntity)).setUsername("Pesho").setEmail("d@d").setPassword("123456");
        LogEntity logEntity = new LogEntity()
                .setUserEntity(userEntity)
                .setAction("viewAll")
                .setDateTime(LocalDateTime.now());
        logService.deleteLogs();
        Mockito.verify(mockLogRepository).deleteAll();
    }
}

