package ru.job4j.job4j_passport_management.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.job4j_passport_management.model.Passport;
import ru.job4j.job4j_passport_management.service.PassportSecondService;

import java.util.List;

@RestController
@RequestMapping("/second")
public class PassportSecondController {

    private final PassportSecondService passportSecondService;

    public PassportSecondController(PassportSecondService passportSecondService) {
        this.passportSecondService = passportSecondService;
    }

    @PostMapping("/save")
    public ResponseEntity<Passport> create(@RequestBody Passport passport) {
        return new ResponseEntity<>(
                this.passportSecondService.save(passport),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Passport> updatePassport(@PathVariable("id") int id, @RequestBody Passport passport) {
        passportSecondService.update(id, passport);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id) {
        passportSecondService.delete(id);
        return new ResponseEntity<>("Passport was deleted", HttpStatus.OK);
    }

    @GetMapping("/find")
    public List<Passport> getAll() {
        return passportSecondService.findAll();
    }

    @GetMapping("/find/{seria}")
    public ResponseEntity<Passport> getPassportSeria(@PathVariable("seria") int id) {
        passportSecondService.getPassportSeria(id);
        return new ResponseEntity<>(
                this.passportSecondService.getPassportSeria(id),
                HttpStatus.CREATED);
    }

    @GetMapping("/unavaliabe")
    public List<Passport> getPassportBestBeforeDate() {
        return passportSecondService.getPassportBestBeforeDate();
    }

    @GetMapping("/find-replaceable")
    public List<Passport> getPassportDate() {
        return passportSecondService.getPassportDate();
    }

}
