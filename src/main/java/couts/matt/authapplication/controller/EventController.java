package couts.matt.authapplication.controller;

import couts.matt.authapplication.data.dto.EventDTO;
import couts.matt.authapplication.entity.Event;
import couts.matt.authapplication.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @ResponseBody
    @PostMapping("/")
    public ResponseEntity<Event> saveEvent(@Valid @RequestBody EventDTO eventRequest) {
        String authID = SecurityContextHolder.getContext().getAuthentication().getName();
        try {
            return new ResponseEntity<>(eventService.saveEvent(eventRequest, authID), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @GetMapping("/upcoming")
    public ResponseEntity<List<Event>> getUpcomingEvents() {
        try {
            return new ResponseEntity<>(eventService.getAllUpcomingEvents(), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ResponseBody
    @GetMapping("/get/{eventID}")
    public ResponseEntity<Event> getEventByID(@Valid @PathVariable Integer eventID) {
        try {
            return new ResponseEntity<>(eventService.getEventByID(eventID), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
