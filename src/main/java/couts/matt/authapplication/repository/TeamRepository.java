package couts.matt.authapplication.repository;

import couts.matt.authapplication.entity.Team;
import couts.matt.authapplication.entity.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long> {

    @Query("SELECT * FROM AUTHAPP.team WHERE team_id = :teamid")
    Optional<Team> findByID(@Param("teamid") Integer teamID);

    @Query("SELECT * FROM AUTHAPP.team WHERE team_name = :teamname")
    Optional<Team> findByTeamName(@Param("teamname") String teamName);
}
