//package SmartPannel;
//
//import SmartPanel.panel.Reservation.SmartPanel.Model.PanelRoles;
//import SmartPanel.panel.Reservation.SmartPanel.Repositories.PanelRepository;
//import SmartPanel.panel.Reservation.SmartPanel.Repositories.RoleRepository;
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.SpringBootConfiguration;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.ContextConfiguration;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//@DataJpaTest
//@ContextConfiguration
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Rollback(value = false)
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//public class RoleRepoTest {
//    @Autowired
//    private RoleRepository Rolerepo;
//
//
//
//
//    @Test
//    public void TestCreateRole(){
//        PanelRoles admin = new PanelRoles("ROLE_ADMIN");
//        PanelRoles customer = new PanelRoles("ROLE_CUSTOMER");
//        PanelRoles Distributor = new PanelRoles ("ROLE_DISTRIBUTOR");
//
//        Rolerepo.saveAll(List.of(admin,customer,Distributor));
//        Long numberOfRows = Rolerepo.count();
//
//        assertEquals(3,numberOfRows);
//
//    }
//    @Test
//    public  void  testListRole(){
//      List <PanelRoles> ListRole= Rolerepo.findAll();
//        assertNotNull(ListRole);
//        assertNotNull(ListRole);
//        Long numberOfRows = Rolerepo.count();
//        assertEquals(3,numberOfRows);
//    }
//
//
//
//}
