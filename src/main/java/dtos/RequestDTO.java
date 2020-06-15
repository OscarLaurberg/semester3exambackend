/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entity.Request;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 *
 * @author oscar
 */
@Schema(name = "Request")
public class RequestDTO {
    @Hidden
    private Long id;
    @Schema(required = true, example = "Troels og de travle heste")
    private String title;

    public RequestDTO() {
    }

    public RequestDTO(String title) {
        this.title = title;
    }
    
    
    
    public RequestDTO(Request request){
        this.title = request.getTitle();
        this.id=request.getId();
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
    
    
    
    
}
