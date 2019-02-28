/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.filters;

/**
 *
 * @author user
 */
public class filterException extends Exception{

    public filterException(String message) {
        super(message);
    }

    public filterException(String message, Throwable cause) {
        super(message, cause);
    } 
    
}
