package drewboiii.social.net.persistence.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import drewboiii.social.net.util.ViewUtils;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(ViewUtils.FullMessage.class)
    private Long id;

    @JsonView(ViewUtils.FullMessage.class)
    private String uid;

    @JsonView(ViewUtils.FullMessage.class)
    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonView(ViewUtils.FullMessage.class)
    private LocalDateTime createdAt;

    @PrePersist
    public void createdAt() {
        this.uid = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
    }

}
