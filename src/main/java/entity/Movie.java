/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import dtos.MovieDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author oscar
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Movie.deleteAllRows", query = "DELETE FROM Movie"),
    @NamedQuery(name = "Movie.getAll", query = "SELECT m FROM Movie m"),
    @NamedQuery(name = "Movie.deleteMovieById", query = "DELETE FROM Movie m WHERE m.id = :id"),
    @NamedQuery(name = "Movie.getByTitle", query = "SELECT m FROM Movie m WHERE m.title = :title"),
})

public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private int year_;
    @Column (nullable = false)
    private String plot;
    @Column (nullable = false)
    private String directors;
    @Column (nullable = false)
    private String genres;
    @Column (nullable = false)
    private String cast_;
    @Column
    private String poster;
    

    @OneToMany(mappedBy="owner", cascade = {CascadeType.PERSIST,CascadeType.ALL})
    private List<Request> requests = new ArrayList<>();
    
       public Movie(String title, int year, String plot, String directors, String genres, String cast) {
        this.title = title;
        this.year_ = year;
        this.plot = plot;
        this.directors = directors;
        this.genres = genres;
        this.cast_ = cast;

    }

    public Movie() {
    }

    public Movie(MovieDTO movieDTO){
        this.title = movieDTO.getTitle();
        this.year_ = movieDTO.getYear();
        this.plot = movieDTO.getPlot();
        this.directors = movieDTO.getDirectors();
        this.genres = movieDTO.getGenres();
        this.cast_ = movieDTO.getCast();
    }
       
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public void addRequest(Request request){
        request.setOwner(this);
        if(!requests.contains(request)){
            requests.add(request);
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear_() {
        return year_;
    }

    public void setYear_(int year_) {
        this.year_ = year_;
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

    public String getCast_() {
        return cast_;
    }

    public void setCast_(String cast_) {
        this.cast_ = cast_;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }
    
    
    
}
