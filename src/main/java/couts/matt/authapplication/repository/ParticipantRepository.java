package couts.matt.authapplication.repository;

import couts.matt.authapplication.entity.Participant;
import couts.matt.authapplication.entity.Team;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParticipantRepository extends CrudRepository<Participant, Long> {

    @Query("SELECT * FROM AUTHAPP.participant WHERE participant_id = :participantid")
    Optional<Participant> findByID(@Param("participantid") Integer participantID);

    @Query("SELECT * FROM AUTHAPP.participant WHERE participant_id = :participantid AND event_id = :eventid")
    Optional<Participant> findByUserIDAndEventID(@Param("participantid") String participantID, @Param("eventid") Integer eventID);
}
