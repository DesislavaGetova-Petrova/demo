package desico.project.service.impl;

import desico.project.model.entity.LogEntity;
import desico.project.model.entity.UserEntity;
import desico.project.model.service.LogServiceModel;
import desico.project.repository.LogRepository;
import desico.project.service.LogService;
import desico.project.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public LogServiceImpl(LogRepository logRepository, UserService userService, ModelMapper modelMapper) {
        this.logRepository = logRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    @Override
    public void createLog(String action) {


        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        String username = authentication.getName();
        UserEntity userEntity = userService.findByName(username);

        LogEntity logEntity = new LogEntity()
                .setUserEntity(userEntity)
                .setAction(action)
                .setDateTime(LocalDateTime.now());
        logRepository.save(logEntity);

    }


    @Scheduled(cron = "${log.delete-cron}")
    public void deleteLogs() {
        logRepository.deleteAll();
    }



    @Override
    public List<LogServiceModel> findAllLogs() {
        return logRepository
                .findAll()
                .stream()
                .map(logEntity -> {
                    LogServiceModel logServiceModel = modelMapper
                            .map(logEntity, LogServiceModel.class);
                    logServiceModel.setUser(logEntity.getUserEntity().getUsername());
                    return logServiceModel;
                })
                .collect(Collectors.toList());
    }
}

