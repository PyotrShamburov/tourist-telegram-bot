package by.free.home.touristtelegrambot.service.impl;

import by.free.home.touristtelegrambot.entity.Admin;
import by.free.home.touristtelegrambot.entity.AdminDTO;
import by.free.home.touristtelegrambot.entity.exception.EntityAlreadyExistException;
import by.free.home.touristtelegrambot.entity.exception.EntityNotFoundException;
import by.free.home.touristtelegrambot.repository.AdminRepository;
import by.free.home.touristtelegrambot.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Admin addNewAdminToDatabase(Admin admin) {
        if (!contains(admin)) {
            log.info(admin+" added to database!");
            return adminRepository.save(admin);
        }
        log.error(admin+" didn't add to database! Admin with this name already exist!");
        throw new EntityAlreadyExistException("Admin with this name already exists!");
    }

    @Override
    public Admin getAdminByAdminName(String adminName) {
        if (adminRepository.existsByAdminName(adminName)) {
            log.info("Request to database for get admin by name - "+adminName);
            return adminRepository.getByAdminName(adminName);
        }
        log.error("Admin with name ["+adminName+"] not found!");
        throw new EntityNotFoundException("Admin with this name not found!");
    }

    @Override
    public Admin updateAdmin(String adminName, Admin newAdmin) {
        Admin adminByName = (Admin) getAdminByAdminName(adminName);
        newAdmin.setId(adminByName.getId());
        log.info("Admin with name ["+adminName+"] updated to "+newAdmin+".");
        return adminRepository.save(newAdmin);
    }

    @Override
    public void deleteAdminByAdminName(String adminName) {
        Admin byAdminName = (Admin) adminRepository.getByAdminName(adminName);
        if (byAdminName != null) {
            log.info("Admin with name ["+adminName+"] has been deleted!");
            adminRepository.delete(byAdminName);
        } else {
            log.error("Admin with name ["+adminName+"] not found!");
            throw new EntityNotFoundException("Admin with this name not found!");
        }

    }

    @Override
    public Optional<Admin> getAdminById(long id) {
        log.info("Request to database for get admin by Id - "+id+".");
        return adminRepository.findById(id);
    }

    @Override
    public boolean contains(Admin admin) {
        log.warn("Check admin for contains in database!");
        return adminRepository.existsByAdminName(admin.getAdminName());
    }

    @Override
    public boolean authCheck(AdminDTO adminDTO) {
        log.info("Authorization check has been started!");
        Admin byName = (Admin) adminRepository.getByAdminName(adminDTO.getAdminName());
        if (byName != null) {
            return byName.getPassword().equals(adminDTO.getPassword());
        }
        log.error("Authorization has been failed!");
        return false;
    }
}
