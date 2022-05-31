package ru.job4j.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.job4j.model.Passport;

import java.util.List;

@Service
public class PassportSecondService {

       private String url = "http://localhost:8080/passport";

       private final RestTemplate client;

       public PassportSecondService(RestTemplate client) {
              this.client = client;
       }

       public Passport save(Passport passport) {
              return client.postForEntity(
                      url + "/save", passport, Passport.class
              ).getBody();
       }

       public Passport update(int id, Passport passport) {
           return client.exchange(
                   String.format("%s/update/%s", url, id),
                   HttpMethod.PUT,
                   new HttpEntity<>(passport),
                   Passport.class
           ).getBody();
       }

       public List<Passport> findAll() {
              return client.exchange(url + "/find",
                  HttpMethod.GET, null, new ParameterizedTypeReference<List<Passport>>() {
                   }).getBody();
       }


       public List<Passport> findPassportsBySeries(int id) {
          return client.exchange(url + "/find/" + id,
                  HttpMethod.GET, null, new ParameterizedTypeReference<List<Passport>>() {
                  }).getBody();
       }


    public boolean delete(int id) {
       return client.exchange(
                String.format("%s/delete/%s", url, id),
                HttpMethod.DELETE,
                HttpEntity.EMPTY,
                Void.class
        ).getStatusCode() != HttpStatus.NOT_FOUND;
    }

    public List<Passport> findPassportBestBeforeDate() {
        return client.exchange(url + "/unavaliabe",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Passport>>() {
                }).getBody();
    }

    public List<Passport> findPassportDate() {
        return client.exchange(url + "/find-replaceable",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Passport>>() {
                }).getBody();
    }
}
