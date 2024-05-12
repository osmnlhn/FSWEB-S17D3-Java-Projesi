package com.workintech.s17d3.validation;
import com.workintech.s17d3.entity.Kangaroo;
import com.workintech.s17d3.entity.Koala;
import com.workintech.s17d3.exceptions.ZooException;
import org.springframework.http.HttpStatus;

import java.util.Map;


public class ZooValidation {
    public static void isIdValid(Integer id) {
        if (id == null || id < 0) {
            throw new ZooException("id is not valid: ID: " + id, HttpStatus.BAD_REQUEST);
        }
    }

    public static void checKangarooExistence(Map<Integer, Kangaroo> kangaroos, Integer id, boolean shouldBeExist) {
        if (shouldBeExist) {
            if (!kangaroos.containsKey(id)) {
                throw new ZooException("record is not exist with given id:" + id, HttpStatus.NOT_FOUND);
            }
        } else {
            if (kangaroos.containsKey(id)) {
                throw new ZooException("record is already exist with given id:" + id, HttpStatus.BAD_REQUEST);
            }
        }
    }


    public static void checkWeight(Double weight){
        if (weight== null || weight<=0){
            throw new ZooException("Weight is not valid! Weight: " + weight,HttpStatus.BAD_REQUEST);
        }
    }


    public static void checkKoalaExistence(Map<Integer, Koala> koalas, Integer id, boolean b){
            if (b) {
                if (!koalas.containsKey(id)) {
                    throw new ZooException("record is not exist with given id:" + id, HttpStatus.NOT_FOUND);
                }
            } else {
                if (koalas.containsKey(id)) {
                    throw new ZooException("record is already exist with given id:" + id, HttpStatus.BAD_REQUEST);
                }
            }
        }
}


