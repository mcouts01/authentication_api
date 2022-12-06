package couts.matt.authapplication.service;

import couts.matt.authapplication.entity.Participant;
import couts.matt.authapplication.entity.Team;
import couts.matt.authapplication.entity.User;
import couts.matt.authapplication.repository.ParticipantRepository;
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
public class ParticipantService {

    private final ParticipantRepository participantRepository;

    private final TeamService teamService;

    private final UserService userService;

    public Participant saveParticipant(String authID, Integer eventID) {
        User user = userService.getUserByAuthID(authID);
        if(participantRepository.findByUserIDAndEventID(authID, eventID).isEmpty()) {
            Participant participant = Participant.builder()
                    .userID(user.getUserID())
                    .eventID(eventID)
                    .teamID(null)
                    .build();
            return participantRepository.save(participant);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Participant already exists");
        }
    }

    public Team getParticipantTeam(String authID, Integer eventID) {
        var participant = this.participantRepository.findByUserIDAndEventID(authID, eventID);
        if(participant.isPresent()) {
            try {
                return this.teamService.getByID(participant.get().getTeamID());
            } catch(ResponseStatusException e) {
                log.info(e.getMessage());
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Participant is not on a team");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Participant not found");
        }
    }
}
