/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.persistence.impl;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.persistence.CinemaPersitence;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

/**
 *
 * @author cristian
 */

@Service
public class InMemoryCinemaPersistence implements CinemaPersitence{
    
    private final Map<String,Cinema> cinemas=new HashMap<>();

    public InMemoryCinemaPersistence() {
        //load stub data
        String functionDate = "2018-12-18 15:30";
        List<CinemaFunction> functions= new ArrayList<>();
        CinemaFunction funct1 = new CinemaFunction(new Movie("SuperHeroes Movie","Action"),functionDate);
        CinemaFunction funct2 = new CinemaFunction(new Movie("The Night","Horror"),functionDate);
       
        
        functions.add(funct1);
        functions.add(funct2);
        Cinema c=new Cinema("cinemaX",functions);
        cinemas.put("cinemaX", c);
        
        String functionDate1 = "2018-12-18 15:30";
        List<CinemaFunction> functions1= new ArrayList<>();
        CinemaFunction funct3 = new CinemaFunction(new Movie("Dracula","Horror"),functionDate1);
        CinemaFunction funct4 = new CinemaFunction(new Movie("Rambo","Accion"),functionDate1);
        functions1.add(funct3);
        functions1.add(funct4);
        Cinema c2=new Cinema("cinemaY",functions1);
        cinemas.put("cinemaY", c2);
        
        String functionDate2 = "2018-12-18 15:30";
        List<CinemaFunction> functions2= new ArrayList<>();
        CinemaFunction funct5 = new CinemaFunction(new Movie("Nemo","Animacion"),functionDate2);
        CinemaFunction funct6 = new CinemaFunction(new Movie("The Night","Animacion"),functionDate2);
        functions2.add(funct5);
        functions2.add(funct6);
        Cinema c1=new Cinema("cinemaZ",functions2);
        cinemas.put("cinemaZ", c1);
    }    

    @Override
    public void buyTicket(int row, int col, String cinema, String date, String movieName) throws CinemaException {
        try {
            Cinema cine =getCinema(cinema);
            List<CinemaFunction> pelis=cine.getFunctions();
            for (CinemaFunction i: pelis){
                if(i.getMovie().equals(movieName) && date.equals(i.getDate())){
                    i.buyTicket(row, col);
                }
            }
        } catch (CinemaPersistenceException ex) {
            Logger.getLogger(InMemoryCinemaPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public List<CinemaFunction> getFunctionsbyCinemaAndDate(String cinema, String date) throws CinemaPersistenceException {
        Cinema cine;
        List<CinemaFunction> answer=new ArrayList<>();
    
            cine = getCinema(cinema);
            List<CinemaFunction> pelis=cine.getFunctions();
          
            for (CinemaFunction i: pelis){
                if(i.getDate().startsWith(date)){
                    answer.add(i);
                }
            }
            if (answer.size()==0) {
                throw new CinemaPersistenceException("No se encontro la funcion "+date); 
            }
            
        
        return answer;
    }

    @Override
    public void saveCinema(Cinema c) throws CinemaPersistenceException {
        if (cinemas.containsKey(c.getName())){
            throw new CinemaPersistenceException("The given cinema already exists: "+c.getName());
        }
        else{
            cinemas.put(c.getName(),c);
        }   
    }

    @Override
    public Cinema getCinema(String name) throws CinemaPersistenceException {
        if (cinemas.get(name)==null) {
            throw new CinemaPersistenceException("No se encontro ese cinema "+name); 
        }
        return cinemas.get(name);
    }
    public Set<Cinema> getAllCinemas() {
               
		Set<Cinema> cines=new HashSet<Cinema>();
                Cinema x = null;
		for(Cinema c: cinemas.values()) {
			cines.add(c);
                        x=c;
		}
		return cines;
		
	}

}
