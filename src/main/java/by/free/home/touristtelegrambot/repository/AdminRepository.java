package by.free.home.touristtelegrambot.repository;

import by.free.home.touristtelegrambot.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin getByAdminName(String adminName);
    @Override
    void delete(Admin admin);
    boolean existsByAdminName(String adminName);
}
