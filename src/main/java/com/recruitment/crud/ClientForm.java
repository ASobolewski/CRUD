package com.recruitment.crud;

import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ClientForm implements Serializable {

    private Integer clientId;
    private Client client;

    public boolean readOnlyRendered(){
        return clientId != null;
    }
    
    public boolean idRendered(){
        return client.getId() != null;
    }
    
    public boolean dateRendered(){
        return client.getCreationDate()!= null;
    }
    
    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

}
