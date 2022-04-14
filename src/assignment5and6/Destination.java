/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment5and6;

import java.io.Serializable;

/**
 *
 * @author Hoang Vu <vuhoan@sheridancollege.ca> <andrewvu270@gmail.com>
 */
public class Destination implements Serializable {
    private String name;
    private int duration;
    private int wentWith;
    private int year;
    private String comments;
    
    public Destination(){
        
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getWentWith() {
        return wentWith;
    }

    public void setWentWith(int wentWith) {
        this.wentWith = wentWith;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
    
    @Override
    public String toString(){
        return "Destination: " + this.getName() + "\nDuration: " + this.getDuration()
                + " days\nWent with: " + this.getWentWith() + " people\nYear: " 
                + this.getYear() + "\nComments: " + this.getComments();
    }
}
