/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.services;

import edu.eci.arsw.cinema.filters.filter;
import edu.eci.arsw.cinema.filters.filterException;
import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.persistence.CinemaPersitence;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author cristian
 */
@Service
public class CinemaServices {
    @Autowired
    CinemaPersitence cps=null;
    @Autowired
    filter filr=null;

    public filter getFilr() {
        return filr;
    }
    
    
    public void setFilr(filter filr) {
        this.filr = filr;
    }
    

    public void addNewCinema(Cinema c){
        try {
            cps.saveCinema(c);
        } catch (CinemaPersistenceException ex) {
            Logger.getLogger(CinemaServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Movie> busqueda(Cinema cinema, String date, String parametro) throws filterException{
        List<Movie> flag=null;
        flag=filr.filtro(cinema, date, parametro);
        return flag;
    }
    
    public Set<Cinema> getAllCinemas(){
        return cps.getAllCinemas();
    }
    
    /**
     * 
     * @param name cinema's name
     * @return the cinema of the given name created by the given author
     * @throws CinemaException
     */
    public Cinema getCinemaByName(String name) throws CinemaException, CinemaPersistenceException{
         return cps.getCinema(name);
    }
    
    
    public void buyTicket(int row, int col, String cinema, String date, String movieName) throws CinemaException{
        cps.buyTicket(row, col, cinema, date, movieName);
    }
    
    public List<CinemaFunction> getFunctionsbyCinemaAndDate(String cinema, String date) throws CinemaException, CinemaPersistenceException {
        return cps.getFunctionsbyCinemaAndDate(cinema, date);
    }

    public void setCps(CinemaPersitence cps) {
        this.cps = cps;
    }

    public void update(String name, CinemaFunction cf) throws CinemaPersistenceException {
		cps.update(name, cf);
	}
}
