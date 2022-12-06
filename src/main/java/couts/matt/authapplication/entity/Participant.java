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
@Table(name = "participant", schema = "authapp")
public class Participant {

    @Id
    private Integer participantID;

    private Integer userID;

    private Integer teamID;

    private Integer eventID;
}
