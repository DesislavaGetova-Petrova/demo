package desico.project.service;

import desico.project.model.service.LogServiceModel;

import java.util.List;

public interface LogService {

    void createLog(String action);

    List<LogServiceModel> findAllLogs();
}