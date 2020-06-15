/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entity.Movie;

/**
 *
 * @author oscar
 */
public class MovieDTO {
    
   private String title;
   private int year;
   private String plot;
   private String directors;
   private String genres;
   private String cast;
   private String poster;

    public MovieDTO(String title, int year, String plot, String directors, String genres, String cast, String poster) {
        this.title = title;
        this.year = year;
        this.plot = plot;
        this.directors = directors;
        this.genres = genres;
        this.cast = cast;
        this.poster = poster;
    }
    
    public MovieDTO (Movie movie){
        this.title = movie.getTitle();
        this.year = movie.getYear_();
        this.plot = movie.getPlot();
        this.directors = movie.getDirectors();
        this.genres = movie.getGenres();
        this.cast = movie.getCast_();
        this.poster = movie.getPoster();
    }
   
   public MovieDTO(){
       
   }

    public String getPoster() {
        return poster;
    }
   
   

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getDirectors() {
        return directors;
    }

    public void setDirectors(String directors) {
        this.directors = directors;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }
   
   
    
}
