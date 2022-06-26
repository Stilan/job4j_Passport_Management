package ru.job4j.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.model.Passport;
import ru.job4j.service.PassportService;

import java.util.List;

public class KafkaController {

    private final PassportService passportService;

    @Autowired
    private KafkaTemplate<Integer, String> template;

    public KafkaController(PassportService passportService) {
        this.passportService = passportService;
    }

    @Scheduled(fixedDelay = 6000)
    public void sendPassportBestBeforeDate() {
        List<Passport> passports = passportService.findPassportBestBeforeDate();
        for (Passport passport : passports) {
            template.send("massage", passport.getId(), "срок паспорта заканчивается");
        }

    }

    @Scheduled(fixedDelay = 6000)
    public void sendPassportDate() {
        List<Passport> passports = passportService.findReplacablePassport();
        for (Passport passport : passports) {
            template.send("massage", passport.getId(), "паспорт просрочен");
        }
    }
}
