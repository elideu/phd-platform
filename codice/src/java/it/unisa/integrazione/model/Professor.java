/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.integrazione.model;

/**
 *
 * @author serter
 */
public class Professor extends Account {
    private String link;
    private String department;
    private String FK_account;    
    private final String type = "docente";


    public void setDepartment(String department) {
        this.department = department;
    }
    
    @Override
    public String getTypeOfAccount() {
        return type;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDepartment() {
        return department;
    }

    public String getLink() {
        return link;
    }

    public void setFK_account(String FK_account) {
        this.FK_account = FK_account;
    }

    public String getFK_account() {
        return FK_account;
    }
    
    


    

    
}
