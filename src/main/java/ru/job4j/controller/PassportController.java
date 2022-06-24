package ru.job4j.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import ru.job4j.model.Passport;
import ru.job4j.service.PassportService;

import java.util.List;

@RestController
@RequestMapping("/passport")
public class PassportController {

  private final PassportService passportService;

    @Autowired
    private KafkaTemplate<Integer, String> template;

    public PassportController(PassportService passportService) {
        this.passportService = passportService;
    }

    @PostMapping("/save")
    public ResponseEntity<Passport> create(@RequestBody Passport passport) {
        return new ResponseEntity<>(
                this.passportService.save(passport),
                HttpStatus.CREATED
        );
    }

   @PutMapping("/update/{id}")
    public ResponseEntity<Passport> updatePassport(@PathVariable("id") int id, @RequestBody Passport passport) {
      passportService.update(id, passport);
      return ResponseEntity.ok().build();
   }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id) {
       passportService.delete(id);
       return new ResponseEntity<>("Passport was deleted", HttpStatus.OK);
    }

    @GetMapping("/find")
    public List<Passport> findAll() {
        return passportService.findAll();
    }

    @GetMapping("/find/{seria}")
    public List<Passport> findPassportsBySeries(@PathVariable("seria") int seria) {
        return passportService.findPassportsBySeries(seria);
    }

    @GetMapping("/unavaliabe")
    @Scheduled(fixedDelay = 6000)
    public List<Passport> getPassportBestBeforeDate() {
        List<Passport> passports = passportService.findPassportBestBeforeDate();
        for (Passport passport : passports) {
            template.send("massage", passport.getId(), "срок паспорта заканчивается");
        }
        return passports;
    }

    @GetMapping("/find-replaceable")
    @Scheduled(fixedDelay = 6000)
    public List<Passport> getPassportDate() {
        List<Passport> passports = passportService.findReplacablePassport();
        for (Passport passport : passports) {
            template.send("massage", passport.getId(), "паспорт просрочен");
        }
        return passports;
    }
}
