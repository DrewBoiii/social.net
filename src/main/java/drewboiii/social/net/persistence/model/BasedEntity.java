package drewboiii.social.net.persistence.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import drewboiii.social.net.views.BasedEntityViews;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@MappedSuperclass
public class BasedEntity {

    public static final String ID_ATTR = "id";
    public static final String UID_ATTR = "uid";
    public static final String CREATED_AT_ATTR = "createdAt";
    public static final String STATUS_ATTR = "status";

    public enum Status {
        ACTIVE, DELETED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(BasedEntityViews.SystemFields.class)
    private Long id;

    @Column(updatable = false, unique = true)
    @JsonView(BasedEntityViews.SystemFields.class)
    private String uid;

    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @JsonView(BasedEntityViews.SystemFields.class)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @JsonView(BasedEntityViews.SystemFields.class)
    private Status status;

    @PrePersist
    void prePersist() {
        this.uid = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
        this.status = Status.ACTIVE;
    }

}
