package couts.matt.authapplication.repository;

import couts.matt.authapplication.entity.Event;
import couts.matt.authapplication.entity.Team;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {

    @Query("SELECT * FROM AUTHAPP.event WHERE  start >= :date")
    Optional<List<Event>> findAllAfterStartDate(@Param("date") LocalDateTime date);

    @Query("SELECT * FROM AUTHAPP.event WHERE event_id = :eventid")
    Optional<Event> findByEventID(@Param("eventid") Integer eventID);
}
