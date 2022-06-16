package ru.job4j.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.model.Passport;

import java.util.List;
import java.util.Optional;

public interface PassportRepository extends CrudRepository<Passport, Integer> {

     Passport findPassportById(Integer id);

     @Query(value = "SELECT * from Passport  where expiration_date <= current_date", nativeQuery = true)
     List<Passport> findPassportBestBeforeDate();

     @Query(value = "select * from Passport  where expiration_date >= current_date and expiration_date <= current_date + interval '3 mons'", nativeQuery = true)
     List<Passport> findReplacablePassport();

     List<Passport> findPassportsBySeries(int series);

     List<Passport> findPassportsByNumber(int number);

    Optional<Passport> findPassportBySeriesAndNumber(int series, int number);
}
