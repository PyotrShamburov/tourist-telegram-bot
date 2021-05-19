package by.free.home.touristtelegrambot.service.impl;

import by.free.home.touristtelegrambot.entity.Admin;
import by.free.home.touristtelegrambot.entity.XToken;
import by.free.home.touristtelegrambot.repository.XTokenRepository;
import by.free.home.touristtelegrambot.service.AdminService;
import by.free.home.touristtelegrambot.service.XTokenService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class XTokenServiceImplTest {
    @Autowired
    private XTokenService xTokenService;
    @Autowired
    private XTokenRepository xTokenRepository;
    @Autowired
    private AdminService adminService;


    @Test
    @Order(1)
    void isAdmin() {
        Admin admin = new Admin(1, "Test", "Test2021");
        adminService.addNewAdminToDatabase(admin);
        String actualToken = (String) xTokenService.addToRepository(admin.getId());
        boolean actualIsAdminCheck = (boolean) xTokenService.isAdmin(actualToken);
        assertTrue(actualIsAdminCheck);
    }

    @Test
    @Order(2)
    void addToRepository() {
        long adminId = 2;
        String actualToken = (String) xTokenService.addToRepository(adminId);
        XToken expectedToken = new XToken(3, adminId, actualToken);
        Optional<XToken> optionalXToken = (Optional<XToken>) xTokenRepository.findByToken(actualToken);
        XToken actualXToken = (XToken) optionalXToken.get();
        assertEquals(expectedToken, actualXToken);
    }
}