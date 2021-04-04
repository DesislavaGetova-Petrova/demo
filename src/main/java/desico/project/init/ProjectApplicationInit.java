package desico.project.init;

import desico.project.service.UnitNameService;
import desico.project.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ProjectApplicationInit implements CommandLineRunner {
    private  final UserService userService;
    private final UnitNameService unitNameService;

    public ProjectApplicationInit(UserService userService, UnitNameService unitNameService) {
        this.userService = userService;
        this.unitNameService = unitNameService;
    }

    @Override
    public void run(String... args) throws Exception {
        userService.seedUsers();
        unitNameService.seedUnitNames();

    }
}
