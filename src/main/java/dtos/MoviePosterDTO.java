/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

/**
 *
 * @author oscar
 */
public class MoviePosterDTO {
    
    private String title;
    private String poster;

    public MoviePosterDTO(String title, String poster) {
        this.title = title;
        this.poster = poster;
    }

    public MoviePosterDTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
    
    
    
}

