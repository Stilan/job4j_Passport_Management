package ru.job4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.job4j.model.Passport;
import ru.job4j.repository.PassportRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PassportService {

    @Autowired
    private PassportRepository passportRepository;

     public Passport save(Passport passport) {
         if (!findPassportsBySeries(passport.getSeries()).isEmpty()
                 || findPassportsByNumber(passport.getNumber()).isEmpty()) {
             throw new IllegalArgumentException();
         }
      return passportRepository.save(passport);
     }

     public Passport update(int id, Passport passport) {
         Passport passportUpdate = passportRepository.findPassportById(id);
         if (passportUpdate == null) {
             throw new NoSuchElementException();
         }
         passportUpdate.setId(id);
         passportUpdate.setName(passport.getName());
         passportUpdate.setLastname(passport.getLastname());
         passportUpdate.setExpirationDate(passport.getExpirationDate());
         return passportRepository.save(passportUpdate);
     }

     public Passport findPassportById(int id) {
         return passportRepository.findPassportById(id);
     }

    public void delete(int id) {
        if (findPassportById(id) == null) {
            throw new NoSuchElementException();
        }
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

    public List<Passport> findPassportsByNumber(int number) {
         return passportRepository.findPassportsByNumber(number);
    }

    public List<Passport> findPassportBestBeforeDate() {
        return passportRepository.findPassportBestBeforeDate();
    }

    public List<Passport> findReplacablePassport() {
         return passportRepository.findReplacablePassport();
    }
}
