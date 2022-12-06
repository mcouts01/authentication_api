package couts.matt.authapplication.service;

import couts.matt.authapplication.data.dto.EventDTO;
import couts.matt.authapplication.entity.Event;
import couts.matt.authapplication.entity.User;
import couts.matt.authapplication.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    private final UserService userService;

    public Event saveEvent(EventDTO eventRequest, String authID) {
        log.info("Creating event requested by " + authID);
        try {
            User user = userService.getUserByAuthID(authID);
            Event event = Event.builder()
                    .creater_id(user.getUserID())
                    .start(eventRequest.getStart())
                    .end(eventRequest.getEnd())
                    .title(eventRequest.getTitle())
                    .description(eventRequest.getDescription())
                    .build();
            return eventRepository.save(event);
        } catch(ResponseStatusException e) {
            throw e;
        } catch(Exception e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not create request event");
        }
    }

    public List<Event> getAllUpcomingEvents() {
        log.info("Finding upcoming events");
        LocalDateTime now = LocalDateTime.now();
        var upcomingEvents = this.eventRepository.findAllAfterStartDate(now);
        if(upcomingEvents.isPresent()) {
            return upcomingEvents.get();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No events found");
        }
    }

    public Event getEventByID(Integer eventID) {
        log.info("Finding event with id: " + eventID);
        var event = this.eventRepository.findByEventID(eventID);
        if(event.isPresent()) {
            return event.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No events found with id: " + eventID);
        }
    }

}
