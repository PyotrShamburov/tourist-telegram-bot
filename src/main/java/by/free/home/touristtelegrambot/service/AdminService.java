package by.free.home.touristtelegrambot.service;

import by.free.home.touristtelegrambot.entity.Admin;
import by.free.home.touristtelegrambot.entity.AdminDTO;

import java.util.Optional;

public interface AdminService {
    Admin addNewAdminToDatabase(Admin admin);
    Admin getAdminByAdminName(String adminName);
    Admin updateAdmin(String adminName, Admin newAdmin);
    void deleteAdminByAdminName(String adminName);
    Optional<Admin> getAdminById(long id);
    boolean contains(Admin admin);
    boolean authCheck(AdminDTO adminDTO);
}
