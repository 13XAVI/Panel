//package SmartPannel;
//
//import com.SmartPannel.Repositories.userRepository;
//import com.SmartPannel.userData.Model.Roles;
//import com.SmartPannel.userData.Model.Users;
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.test.annotation.Rollback;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Rollback(value = false)
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//public class UserTest {
//    @Autowired
//    userRepository repository;
//    @Test
//    @Order(1)
//    public void testCreateUser(){
//        Users user = new Users();
//        user.setName("Hakimi");
//        user.setEmail("Ashraf@gmail.com");
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String rawPasword = "pass123";
//        String encoded = encoder.encode(rawPasword);
//        user.setPassword(encoded);
//
//        Users savedUser = repository.save(user);
//        assertEquals("Hakimi", savedUser.getName());
//
//
//    }
//
//    @Test
//    public void assignRoleToUsers() {
//        Long roleId = 2L;
//        Long userId = 3L;
//        Users user = repository.findById(userId)
//                .orElseThrow(() -> new IllegalArgumentException("User not found"));
//
//
//        user.addRole(new Roles(3L));
//        user.addRole(new Roles(2L));
//        Users updatedUser = repository.save(user);
//
//        assertEquals(2, updatedUser.getRoles().size());
//
//    }
//}
