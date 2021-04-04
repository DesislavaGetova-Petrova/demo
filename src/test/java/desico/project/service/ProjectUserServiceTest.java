package desico.project.service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import desico.project.model.entity.UserEntity;
import desico.project.model.entity.UserRoleEntity;
import desico.project.model.enums.UserRole;
import desico.project.repository.UserRepository;
import desico.project.service.impl.ProjectUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@ExtendWith(MockitoExtension.class)
public class ProjectUserServiceTest {

        private ProjectUserService serviceToTest;


        @Mock
        UserRepository mockUserRepository;

        @BeforeEach
        public void setUp() {
            serviceToTest = new ProjectUserService(mockUserRepository);
        }
        @Test
        void testUserNotFound() {
            Assertions.assertThrows(
                    UsernameNotFoundException.class, () -> {
                        serviceToTest.loadUserByUsername("user_does_not_exits");
                    }
            );
        }

        @Test
        void testExistingUser() {

            UserEntity userEntity = new UserEntity();
            userEntity.setUsername("luchob");
            userEntity.setPassword("xyz");


            UserRoleEntity roleUser = new UserRoleEntity();
            roleUser.setRole(UserRole.USER);
            UserRoleEntity roleAdmin = new UserRoleEntity();
            roleAdmin.setRole(UserRole.ADMIN);

            userEntity.setRoles(List.of(roleUser, roleAdmin));


            Mockito.when(mockUserRepository.findByUsername("luchob")).
                    thenReturn(Optional.of(userEntity));


            UserDetails userDetails = serviceToTest.loadUserByUsername("luchob");

            Assertions.assertEquals(userEntity.getUsername(), userDetails.getUsername());
            Assertions.assertEquals(2, userDetails.getAuthorities().size());

            List<String> authorities = userDetails.
                    getAuthorities().
                    stream().
                    map(GrantedAuthority::getAuthority).
                    collect(Collectors.toList());

            Assertions.assertTrue(authorities.contains("ROLE_ADMIN"));
            Assertions.assertTrue(authorities.contains("ROLE_USER"));
        }

    }


