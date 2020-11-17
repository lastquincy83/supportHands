package com.supporthands.supportHands.repository;

import com.supporthands.supportHands.model.Notes;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface NotesRepository extends CrudRepository<Notes, Long>{

    @Query("select note from Notes note where note.userDetails.userId = :userId")
    List<Notes> findNotesByUserId(@Param("userId") Long userId);

    @Query("select note from Notes note where note.userDetails.userId = :userId and note.noteId = :noteId")
    Optional<Notes> findByNoteIdAndUserId(@Param("noteId") Long noteId, @Param("userId") Long userId);
}
