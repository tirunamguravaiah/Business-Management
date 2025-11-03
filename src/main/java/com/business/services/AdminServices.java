package com.business.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.business.entities.Admin;
import com.business.repositories.AdminRepository;

@Service
public class AdminServices {

    @Autowired
    private AdminRepository adminRepository;

    // ✅ Get All Admins
    public List<Admin> getAll() {
        return adminRepository.findAll();
    }

    // ✅ Get Single Admin by ID
    public Admin getAdmin(String id) {
        return adminRepository.findById(id).orElse(null);
    }

    // ✅ Update Admin
    public boolean update(Admin admin, String id) {
        if (adminRepository.existsById(id)) {
            admin.setAdminId(id); // preserve ID consistency
            adminRepository.save(admin);
            return true;
        }
        return false;
    }

    // ✅ Delete Admin by ID
    public boolean delete(String id) {
        if (adminRepository.existsById(id)) {
            adminRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // ✅ Add New Admin
    public Admin addAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    // ✅ Validate Admin Login
    public boolean validateAdminCredentials(String email, String password) {
        Admin admin = adminRepository.findByAdminEmail(email);
        return admin != null && admin.getAdminPassword().equals(password);
    }
}
