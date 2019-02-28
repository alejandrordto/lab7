/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.controllers;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.services.CinemaServices;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author cristian
 */
@RestController
@RequestMapping(value = "/cinemas")
public class CinemaAPIController {

    public static ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
    static CinemaServices c = ac.getBean(CinemaServices.class);

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> manejadorGetRecursoXX() {

        try {

            return new ResponseEntity<>(c.getAllCinemas(), HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error bla bla bla", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{cinema}")
    public ResponseEntity<?> getCinema(@PathVariable String cinema) throws CinemaPersistenceException, CinemaException {
        try {

            Cinema a = c.getCinemaByName(cinema);
            return new ResponseEntity<>(a, HttpStatus.ACCEPTED);
        } catch (CinemaPersistenceException ex) {
            Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Cine no encontrado", HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/{cinema}/{date}")
    public ResponseEntity<?> getFuntion(@PathVariable String cinema, @PathVariable String date) throws CinemaPersistenceException, CinemaException {
        try {

            List<CinemaFunction> a = c.getFunctionsbyCinemaAndDate(cinema, date);

            return new ResponseEntity<>(a, HttpStatus.ACCEPTED);
        } catch (CinemaPersistenceException ex) {
            Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("funcion no encontrado", HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/{cinema}/{date}/{moviename}")
    public ResponseEntity<?> getMovie(@PathVariable String cinema, @PathVariable String date, @PathVariable String moviename) throws CinemaPersistenceException, CinemaException {
        try {
            CinemaFunction b = null;

            List<CinemaFunction> a = c.getFunctionsbyCinemaAndDate(cinema, date);
            for (CinemaFunction i : a) {

                if (i.getMovie().getName().equals(moviename)) {
                    b = i;
                }

            }
            if (b == null) {
                throw new CinemaPersistenceException("No se encontro la funcion " + date);
            }
            return new ResponseEntity<>(b, HttpStatus.ACCEPTED);
        } catch (CinemaPersistenceException ex) {
            Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("funcion no encontrado", HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(method = RequestMethod.POST)
     @PostMapping("/{name}")
    public ResponseEntity<?> manejadorPostRecursoXX(@RequestBody CinemaFunction o,@PathVariable String name) {
        try {
            c.getCinemaByName(name).addFunction(o);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception ex) {
            Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error bla bla bla", HttpStatus.FORBIDDEN);
        }

    }
}
