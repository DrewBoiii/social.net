package drewboiii.social.net.persistence.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import drewboiii.social.net.views.MessageViews;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(MessageViews.FullMessage.class)
    private Long id;

    @Column(updatable = false, unique = true)
    @JsonView(MessageViews.FullMessage.class)
    private String uid;

    @JsonView(MessageViews.Public.class)
    private String content;

    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @JsonView(MessageViews.Public.class)
    private LocalDateTime createdAt;

    @PrePersist
    void prePersist() {
        this.uid = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
    }

}
