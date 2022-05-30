package ru.job4j.job4j_passport_management.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.job4j_passport_management.model.Passport;

import java.util.List;

public interface PassportRepository extends CrudRepository<Passport, Integer> {

     Passport findPassportById(Integer id);
     Passport findPassportBySeries(Integer id);

     @Query(value = "SELECT * from Passport  where created <= current_date", nativeQuery = true)
     List<Passport> getPassportBestBeforeDate();

     @Query(value = "select * from Passport  where created <= current_date + interval '3 mons'", nativeQuery = true)
     List<Passport> getPassportDate();
}
