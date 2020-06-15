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
public class CombinedMovieInfoDTO {
    
    private String title;
    private int year;
    private String plot;
    private String directors;
    private String genres;
    private String cast;
    private String poster;
    
    public CombinedMovieInfoDTO(MovieInfoDTO movieInfo, MoviePosterDTO moviePoster){
        this.title=movieInfo.getTitle();
        this.year=movieInfo.getYear();
        this.plot = movieInfo.getPlot();
        this.directors = movieInfo.getDirectors();
        this.genres = movieInfo.getGenres();
        this.cast = movieInfo.getCast();
        this.poster = moviePoster.getPoster();
    }
    
}
