package ru.job4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.model.Passport;
import ru.job4j.repository.PassportRepository;

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

    public List<Passport> findPassportsBySeries(int seria) {
       return passportRepository.findPassportsBySeries(seria);

    }

    public List<Passport> findPassportBestBeforeDate() {
        return passportRepository.findPassportBestBeforeDate();
    }

    public List<Passport> findPassportDate() {
         return passportRepository.findPassportDate();
    }
}
