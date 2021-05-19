package by.free.home.touristtelegrambot.service.impl;

import by.free.home.touristtelegrambot.entity.Admin;
import by.free.home.touristtelegrambot.entity.XToken;
import by.free.home.touristtelegrambot.repository.XTokenRepository;
import by.free.home.touristtelegrambot.service.AdminService;
import by.free.home.touristtelegrambot.service.XTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class XTokenServiceImpl implements XTokenService {
    @Autowired
    private XTokenRepository tokenRepository;
    @Autowired
    private AdminService adminService;

    @Override
    public String generateToken() {
        UUID uuid = UUID.randomUUID();
        log.info("New XToken has been generated "+uuid.toString()+".");
        return uuid.toString();
    }

    @Override
    public String addToRepository(long adminId) {
        XToken xToken = new XToken();
        String key = generateToken();
        xToken.setAdminId(adminId);
        xToken.setToken(key);
        XToken savedXToken = (XToken) tokenRepository.save(xToken);
        log.info("XToken has been added to database. XToken: ["+savedXToken+"].");
        return key;
    }

    @Override
    public boolean isAdmin(String token) {
        log.info("Is admin check by XToken! XToken = "+token+".");
        Optional<XToken> xToken = tokenRepository.findByToken(token);
        if (xToken.isPresent()) {
            log.info("XToken "+token+" is exists!");
            long adminId = (long) xToken.get().getAdminId();
            Optional<Admin> adminById = adminService.getAdminById(adminId);
            if (adminById.isPresent()) {
                log.info("Admin with id ["+adminId+"] exists!");
                return true;
            }
        }
        log.error("XToken not found! Token = "+token+".");
        return false;
    }
}
