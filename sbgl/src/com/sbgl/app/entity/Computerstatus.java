package com.sbgl.app.entity;



/**
 * Computerstatus entity. @author MyEclipse Persistence Tools
 */

public class Computerstatus extends com.sbgl.app.dao.DaoAbs implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String name;


    // Constructors

    /** default constructor */
    public Computerstatus() {
    }

	/** minimal constructor */
    public Computerstatus(Integer id) {
        this.id = id;
    }
    
    /** full constructor */
    public Computerstatus(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
   








}
