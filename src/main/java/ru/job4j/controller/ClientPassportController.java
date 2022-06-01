package ru.job4j.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.model.Passport;
import ru.job4j.service.ClientPassportService;

import java.util.List;

@RestController
@RequestMapping("/second")
public class ClientPassportController {

    private final ClientPassportService clientPassportService;

    public ClientPassportController(ClientPassportService passportSecondService) {
        this.clientPassportService = passportSecondService;
    }

    @PostMapping("/save")
    public ResponseEntity<Passport> create(@RequestBody Passport passport) {
        return new ResponseEntity<>(
                this.clientPassportService.save(passport),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Passport> updatePassport(@PathVariable("id") int id, @RequestBody Passport passport) {
        clientPassportService.update(id, passport);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id) {
        clientPassportService.delete(id);
        return new ResponseEntity<>("Passport was deleted", HttpStatus.OK);
    }

    @GetMapping("/find")
    public List<Passport> findAll() {
        return clientPassportService.findAll();
    }

    @GetMapping("/find/{seria}")
    public List<Passport> findPassportsBySeries(@PathVariable("seria") int seria) {
        return clientPassportService.findPassportsBySeries(seria);
    }

    @GetMapping("/unavaliabe")
    public List<Passport> getPassportBestBeforeDate() {
        return clientPassportService.findPassportBestBeforeDate();
    }

    @GetMapping("/find-replaceable")
    public List<Passport> findReplacablePassport() {
        return clientPassportService.findReplacablePassport();
    }

}
