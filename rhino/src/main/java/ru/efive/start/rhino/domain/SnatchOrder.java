package ru.efive.start.rhino.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;


@Entity
@Table(name="snatch_order")
public class SnatchOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "snatch_order_id_generator")
    @SequenceGenerator(name = "snatch_order_id_generator", sequenceName = "snatch_order_id_generator")
    @Column(name="snatch_order_id")
    private long id;

    @Column(name="site")
    private String site;

    @Column(name="description")
    private String description;

    @Column(name="fields")
    private String fields;

    @Column(name="created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Column(name="processed")
    private long processed;


    @ManyToOne(optional=false)
    @JoinColumn(name="user_id")
    private User user;


    public long getProcessed() {
        return processed;
    }

    public void setProcessed(long processed) {
        this.processed = processed;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}