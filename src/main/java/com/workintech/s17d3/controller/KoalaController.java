package com.workintech.s17d3.controller;


import com.workintech.s17d3.entity.Koala;
import com.workintech.s17d3.validation.ZooValidation;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/koalas")
public class KoalaController {
    private Map<Integer, Koala> koalas;
    @PostConstruct
    public void init(){
        koalas = new HashMap<>();
        koalas.put(1, new Koala(1,"koalet",6d,7d,"female"));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)

    public List<Koala>findAll(){
        return this.koalas.values().stream().toList();
    }

    @GetMapping("/{id}")

    public Koala find(@PathVariable Integer id){
        ZooValidation.isIdValid(id);
        ZooValidation.checkKoalaExistence(koalas,id,true);
        return koalas.get(id);

    }

    @PostMapping
    public Koala save(@RequestBody Koala koala){
        ZooValidation.isIdValid(koala.getId());
        ZooValidation.checkKoalaExistence(koalas, koala.getId(),false);
        koalas.put(koala.getId(), koala);
        return koalas.get(koala.getId());
    }

    @PutMapping("/{id}")

    public Koala uptade(@PathVariable Integer id,@RequestBody Koala koala){
         ZooValidation.isIdValid(id);
         koala.setId(id);
         if(koalas.containsKey(id)){
             koalas.put(id,koala);
           return koalas.get(id);
         } else {
             return save(koala);
         }

    }

    @DeleteMapping("/id")

    public Koala delete(@PathVariable Integer id){
        ZooValidation.isIdValid(id);
        ZooValidation.checkKoalaExistence(koalas,id,true);
        return koalas.remove(id);
    }

}
