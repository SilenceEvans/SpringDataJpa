package cn.itchenxi.entity;

import javax.jws.soap.SOAPBinding;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rid;
    private String roleName;
    /**
     * 放弃维护
     */
    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<User>();

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
