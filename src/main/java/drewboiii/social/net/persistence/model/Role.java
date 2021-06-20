package drewboiii.social.net.persistence.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Role extends BasedEntity {

    public enum RoleName {
        USER("user"), MODER("moderator"), ADMIN("administrator");

        private final String detailedName;

        RoleName(String name) {
            this.detailedName = name;
        }

        public String getDetailedName() {
            return detailedName;
        }
    }

    public static final String NAME_ATTR = "name";

    @Enumerated(EnumType.STRING)
    private RoleName name;

    // TODO: 6/20/2021 permissions

}
