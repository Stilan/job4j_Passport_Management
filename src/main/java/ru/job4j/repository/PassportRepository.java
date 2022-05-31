package ru.job4j.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.model.Passport;

import java.util.List;

public interface PassportRepository extends CrudRepository<Passport, Integer> {

     Passport findPassportById(Integer id);
     Passport findPassportBySeries(Integer id);

     @Query(value = "SELECT * from Passport  where created <= current_date", nativeQuery = true)
     List<Passport> findPassportBestBeforeDate();

     @Query(value = "select * from Passport  where created <= current_date + interval '3 mons'", nativeQuery = true)
     List<Passport> findPassportDate();

     List<Passport> findPassportsBySeries(int series);
}
