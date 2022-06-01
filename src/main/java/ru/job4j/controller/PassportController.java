package ru.job4j.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.model.Passport;
import ru.job4j.service.PassportService;

import java.util.List;

@RestController
@RequestMapping("/passport")
public class PassportController {

  private final PassportService passportService;

    public PassportController(PassportService passportService) {
        this.passportService = passportService;
    }

    @PostMapping("/save")
    public ResponseEntity<Passport> create(@RequestBody Passport passport) {
        if (passportService.findPassportsBySeries(passport.getSeries()) != null
                || passportService.findPassportsByNumber(passport.getNumber()) != null) {
            return new ResponseEntity<>(
                    new Passport(),
                    HttpStatus.BAD_REQUEST
                    );
        }
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

        if (passportService.findPassportById(id) == null) {
            return new ResponseEntity<>("No records found for id" + id, HttpStatus.NOT_FOUND);

        }
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
    public List<Passport> getPassportBestBeforeDate() {
      return passportService.findPassportBestBeforeDate();
    }

    @GetMapping("/find-replaceable")
    public List<Passport> getPassportDate() {
        return passportService.findReplacablePassport();
    }
}
