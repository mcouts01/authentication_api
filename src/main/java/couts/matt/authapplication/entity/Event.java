package couts.matt.authapplication.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "event", schema = "authapp")
public class Event {
    @Id
    private Integer eventID;

    private LocalDate start;

    private LocalDate end;

    private String title;

    private String description;

    private Integer creater_id;
}
