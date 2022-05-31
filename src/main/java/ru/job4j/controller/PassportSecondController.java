package ru.job4j.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.model.Passport;
import ru.job4j.service.PassportSecondService;

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
    public List<Passport> findAll() {
        return passportSecondService.findAll();
    }

    @GetMapping("/find/{seria}")
    public List<Passport> findPassportsBySeries(@PathVariable("seria") int id) {
        return passportSecondService.findPassportsBySeries(id);
    }

    @GetMapping("/unavaliabe")
    public List<Passport> getPassportBestBeforeDate() {
        return passportSecondService.findPassportBestBeforeDate();
    }

    @GetMapping("/find-replaceable")
    public List<Passport> getPassportDate() {
        return passportSecondService.findPassportDate();
    }

}
