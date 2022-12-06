package couts.matt.authapplication.service;

import couts.matt.authapplication.data.dto.TeamDTO;
import couts.matt.authapplication.data.dto.UserDTO;
import couts.matt.authapplication.entity.Event;
import couts.matt.authapplication.entity.Team;
import couts.matt.authapplication.entity.User;
import couts.matt.authapplication.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    private final UserService userService;

    public Team saveTeam(TeamDTO teamRequest, String authID) {
        User teamOwner = this.userService.getUserByAuthID(authID);
        if(this.teamRepository.findByTeamName(teamRequest.getTeamName()).isEmpty()) {
            Team team = Team.builder()
                    .teamName(teamRequest.getTeamName())
                    .teamColor(teamRequest.getTeamColor())
                    .teamIcon(teamRequest.getTeamIcon())
                    .teamOwnerID(teamOwner.getUserID())
                    .build();
            return teamRepository.save(team);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Team already exists");
        }
    }

    public Team getByID(Integer teamID) {
        var team = this.teamRepository.findByID(teamID);
        if(team.isPresent()) {
            return team.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No team found with id " + teamID);
        }
    }
}
