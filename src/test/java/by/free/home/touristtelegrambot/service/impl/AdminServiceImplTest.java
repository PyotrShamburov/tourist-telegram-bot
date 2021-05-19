package by.free.home.touristtelegrambot.service.impl;

import by.free.home.touristtelegrambot.entity.Admin;
import by.free.home.touristtelegrambot.entity.AdminDTO;
import by.free.home.touristtelegrambot.repository.AdminRepository;
import by.free.home.touristtelegrambot.service.AdminService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AdminServiceImplTest {

    @Autowired
    private AdminService adminService;
    @Autowired
    private AdminRepository adminRepository;
    private Admin admin;

    @BeforeEach
    void setUp() {
        admin = new Admin(1, "admin", "Admin2021" );
    }

    @Test
    @Order(1)
    void addNewAdminToDatabase() {
        Admin actualAdmin = (Admin) adminService.addNewAdminToDatabase(admin);
        assertEquals(admin, actualAdmin);
    }

    @Test
    @Order(2)
    void getAdminByAdminName() {
        Admin actualAdminByName = (Admin) adminService.getAdminByAdminName(admin.getAdminName());
        assertEquals(admin, actualAdminByName);
    }

    @Test
    @Order(3)
    void updateAdmin() {
        String oldAdminName = admin.getAdminName();
        admin.setAdminName("NewAdmin");
        admin.setPassword("NewAdmin2021");
        Admin updatedActualAdmin = (Admin) adminService.updateAdmin(oldAdminName, admin);
        assertEquals(admin, updatedActualAdmin);
    }
    @Test
    @Order(4)
    void deleteAdminByAdminName() {
        String adminName = "NewAdmin";
        adminService.deleteAdminByAdminName(adminName);
        Admin actualAdmin = (Admin) adminRepository.getByAdminName(adminName);
        assertNull(actualAdmin);
    }

    @Test
    @Order(5)
    void getAdminById() {
        long adminActualId = 2;
        adminService.addNewAdminToDatabase(admin);
        Optional<Admin> actualAdmin = (Optional<Admin>) adminService.getAdminById(adminActualId);
        boolean actualPresent = (boolean) actualAdmin.isPresent();
        assertTrue(actualPresent);
    }

    @Test
    @Order(6)
    void contains() {
        boolean actualContains = (boolean) adminService.contains(admin);
        assertTrue(actualContains);
    }

    @Test
    @Order(7)
    void authCheck() {
        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setAdminName(admin.getAdminName());
        adminDTO.setPassword(admin.getPassword());
        boolean actualResult = (boolean) adminService.authCheck(adminDTO);
        assertTrue(actualResult);
    }


}