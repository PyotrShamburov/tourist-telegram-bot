package by.free.home.touristtelegrambot.resource;

import by.free.home.touristtelegrambot.entity.Admin;
import by.free.home.touristtelegrambot.entity.AdminDTO;
import by.free.home.touristtelegrambot.service.AdminService;
import by.free.home.touristtelegrambot.service.XTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/admin")
@Slf4j
public class AdminResource {
    @Autowired
    private AdminService adminService;
    @Autowired
    private XTokenService xTokenService;

    @PostMapping(path = "/auth")
    public ResponseEntity<String> authorization(@Valid @RequestBody AdminDTO adminDTO) {
        log.info("Authorization start with input data "+adminDTO+".");
        if (adminService.authCheck(adminDTO)) {
            long adminId = (long) adminService.getAdminByAdminName(adminDTO.getAdminName()).getId();
            String xToken = (String) xTokenService.addToRepository(adminId);
            return new ResponseEntity<>(xToken, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/add")
    public ResponseEntity<Admin> createNewAdmin(@Valid @RequestBody Admin admin) {
        Admin newSavedAdmin = (Admin) adminService.addNewAdminToDatabase(admin);
        log.info("Request to add new admin to database "+admin+".");
        return new ResponseEntity<>(newSavedAdmin, HttpStatus.CREATED);
    }

    @PutMapping(path = "/update/{adminName}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable String adminName, @Valid @RequestBody Admin newAdmin) {
        Admin updatedAdmin = (Admin) adminService.updateAdmin(adminName, newAdmin);
        log.info("Request for update existing admin in database by name!" +
                " AdminName ["+adminName+"].");
        return new ResponseEntity<>(updatedAdmin, HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{adminName}")
    public ResponseEntity<String> deleteAdmin(@PathVariable String adminName) {
        String regex = "^[A-Za-z]{3,15}[0-9]{0,5}$";
        if (adminName.matches(regex)) {
            adminService.deleteAdminByAdminName(adminName);
            log.info("Request for delete admin in database by name! AdminName ["+adminName+"].");
            return new ResponseEntity<>(adminName.toUpperCase() + " - DELETED!", HttpStatus.OK);
        } else {
            log.error("Name is not valid! AdminName ["+adminName+"].");
            return new ResponseEntity<>("Invalid name supplied!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/show/{adminName}")
    public ResponseEntity<Admin> getAdminByName(@PathVariable String adminName) {
        String regex = "^[A-Za-z]{3,15}[0-9]{0,5}$";
        if (adminName.matches(regex)) {
            Admin adminByAdminName = (Admin) adminService.getAdminByAdminName(adminName);
            log.info("Request for find admin in database by name! AdminName ["+adminName+"].");
            return new ResponseEntity<>(adminByAdminName, HttpStatus.OK);
        } else {
            log.error("AdminName is not valid! Name ["+adminName+"].");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
