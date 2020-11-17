package com.supporthands.supportHands.model;

import static javax.persistence.TemporalType.TIMESTAMP;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"creationDate", "lastModifiedDate"}, allowGetters = true)
public abstract class Auditable<U> {

    @Column(name = "created_date", updatable = false)
    @JsonIgnore
    @CreatedDate
    protected Timestamp creationDate;


    @Column(name = "lastMod_date")
    @LastModifiedDate
    @JsonIgnore
    protected Timestamp lastModifiedDate;

    @Column(name = "created_by")
    @CreatedBy
    @JsonIgnore
    protected String createdBy;

}
