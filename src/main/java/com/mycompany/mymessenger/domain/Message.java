package com.mycompany.mymessenger.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Message {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Customer sender;
    @ManyToOne
    private Customer reciever;
    private String body;

    @XmlAttribute
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlElement
    public Customer getSender() {
        return sender;
    }

    public void setSender(Customer sender) {
        this.sender = sender;
    }

    @XmlElement
    public Customer getReciever() {
        return reciever;
    }

    public void setReciever(Customer reciever) {
        this.reciever = reciever;
    }

    @XmlElement
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}
