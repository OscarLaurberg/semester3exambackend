/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import dtos.RequestDTO;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Request")
@NamedQueries({
    @NamedQuery(name = "Request.deleteAllRows", query = "DELETE FROM Request"),
    @NamedQuery(name = "Request.getAll", query = "SELECT r FROM Request r ORDER by r.title"),
    @NamedQuery(name = "Request.getByTitle", query = "SELECT r FROM Request r WHERE r.title LIKE :title")
})
public class Request implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;
    @Temporal(TemporalType.DATE)
    private Date requestDate;

    @ManyToOne
    @JoinColumn(name = "MOVIE_ID", referencedColumnName = "ID")
    private Movie owner;

    public Request() {

    }

    public Request(String title) {
        this.title = title;
        requestDate = new Date();

    }
    
    public Request (RequestDTO requestDTO){
        this.title=requestDTO.getTitle();
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Movie getOwner() {
        return owner;
    }

    public void setOwner(Movie movie) {
        this.owner = movie;
    }

    
    
}
