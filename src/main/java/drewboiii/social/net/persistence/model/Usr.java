package drewboiii.social.net.persistence.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Entity
@Table(name = "usr")
@AllArgsConstructor
@NoArgsConstructor
public class Usr extends BasedEntity {

    public static final String USERNAME_ATTR = "username";
    public static final String PASSWORD_ATTR = "password";

    @Column(unique = true)
    private String username;

    private String password;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Message> messages;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = {
                    @JoinColumn(name = "usr_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id", referencedColumnName = "id")
            })
    private Set<Role> roles;

}
