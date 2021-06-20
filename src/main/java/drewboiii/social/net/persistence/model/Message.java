package drewboiii.social.net.persistence.model;

import com.fasterxml.jackson.annotation.JsonView;
import drewboiii.social.net.views.MessageViews;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Message extends BasedEntity {

    public static final String CONTENT_ATTR = "content";

    @JsonView(MessageViews.Public.class)
    private String content;

    @ManyToOne
    @JsonView(MessageViews.Public.class)
    private Usr author;

}
