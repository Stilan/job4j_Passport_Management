package ru.job4j.job4j_passport_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.job4j.job4j_passport_management.model.Passport;
import ru.job4j.job4j_passport_management.repository.PassportRepository;

import java.util.Date;
import java.util.List;

@Service
public class PassportService {

    @Autowired
    private PassportRepository passportRepository;

     public Passport save(Passport passport) {
      return passportRepository.save(passport);
     }

     public Passport update(int id, Passport passport) {
         Passport passportUpdate = passportRepository.findPassportById(id);
         passportUpdate.setId(id);
         passportUpdate.setName(passport.getName());
         passportUpdate.setLastname(passport.getLastname());
         passportUpdate.setCreated(passport.getCreated());
         return passportRepository.save(passportUpdate);
     }

     public Passport findPassportById(int id) {
         return passportRepository.findPassportById(id);
     }

    public void delete(int id) {
         Passport passport = findPassportById(id);
         passportRepository.delete(passport);
    }

    public List<Passport> findAll() {
         List<Passport> list = (List<Passport>) passportRepository.findAll();
         return list;
    }

    public Passport getPassportSeria(int id) {
         Passport passport = passportRepository.findPassportBySeries(id);
         return passport;
    }

    public List<Passport> getPassportBestBeforeDate() {
        return passportRepository.getPassportBestBeforeDate();
    }

    public List<Passport> getPassportDate() {
         return passportRepository.getPassportDate();
    }
}
