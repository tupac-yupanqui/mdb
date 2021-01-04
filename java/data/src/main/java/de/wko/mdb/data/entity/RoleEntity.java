package de.wko.mdb.data.entity;

import de.wko.mdb.types.Role;
import de.wko.mdb.types.enums.ERole;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

    public RoleEntity() {

    }

    public RoleEntity(ERole name) {
        this.name = name;
    }

    public RoleEntity(Role r) {
        this.name = r.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ERole getName() {
        return name;
    }

    public void setName(ERole name) {
        this.name = name;
    }

    public Role getType() {
        Role r = new Role(this.name);
        r.setId(id);
        return r;
    }
}