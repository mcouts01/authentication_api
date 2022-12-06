package couts.matt.authapplication.controller;

import couts.matt.authapplication.data.dto.TeamDTO;
import couts.matt.authapplication.data.dto.UserDTO;
import couts.matt.authapplication.entity.Team;
import couts.matt.authapplication.entity.User;
import couts.matt.authapplication.service.TeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/team")
@CrossOrigin("*")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @ResponseBody
    @PostMapping("/")
    public ResponseEntity<Team> saveTeam(@Valid @RequestBody TeamDTO newTeam) {
        log.info("Saving Team with name " + newTeam.getTeamName());
        try {
            String authID = SecurityContextHolder.getContext().getAuthentication().getName();
            Team createdTeam = teamService.saveTeam(newTeam, authID);
            return new ResponseEntity<>(createdTeam, HttpStatus.OK);
        } catch(Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
