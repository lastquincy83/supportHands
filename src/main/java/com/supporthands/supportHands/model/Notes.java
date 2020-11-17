package com.supporthands.supportHands.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Notes")
public class Notes extends NoteBase implements Serializable   {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="note_Id")
    private Long noteId;


    /*In case of we need to maintain many notes to one User relation through User Entity*/
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="userid")
    private User userDetails;

    @Builder
    public Notes(String title, String message, User userDetails) {
        super(title, message);
        this.userDetails = userDetails;
    }
}
