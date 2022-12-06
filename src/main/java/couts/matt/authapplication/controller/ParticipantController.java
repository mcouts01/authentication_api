package couts.matt.authapplication.controller;

import couts.matt.authapplication.entity.Participant;
import couts.matt.authapplication.entity.Team;
import couts.matt.authapplication.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/participant")
@CrossOrigin
@RequiredArgsConstructor
public class ParticipantController {

    private ParticipantService participantService;

    @ResponseBody
    @PostMapping("/")
    private ResponseEntity<Participant> saveParticipant(@Valid @RequestBody Integer eventID) {
        String authID = SecurityContextHolder.getContext().getAuthentication().getName();
        try {
            var participant = this.participantService.saveParticipant(authID, eventID);
            return new ResponseEntity<>(participant, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @GetMapping("/get/{eventID}")
    private ResponseEntity<Team> getParticipantTeam(@Valid @PathVariable Integer eventID) {
        String authID = SecurityContextHolder.getContext().getAuthentication().getName();
        try {
            var team = this.participantService.getParticipantTeam(authID, eventID);
            return new ResponseEntity<>(team, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
