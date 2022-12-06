package couts.matt.authapplication.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "team", schema = "authapp")
public class Team {
    @Id
    private Integer teamID;

    private String teamName;

    private Integer teamOwnerID;

    private String teamIcon;

    private String teamColor;

    private Integer eventID;
}
