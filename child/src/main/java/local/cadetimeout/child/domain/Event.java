package local.cadetimeout.child.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(insertable = false, updatable = false)
    private LocalDateTime created;

    private String data;
}
