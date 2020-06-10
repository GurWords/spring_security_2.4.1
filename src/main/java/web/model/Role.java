package web.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "role")
    String role;
//    @ManyToMany( fetch = FetchType.LAZY, mappedBy = "roleList")
//    private List<User> userList;
    @OneToMany(mappedBy = "role",fetch = FetchType.EAGER)
    private List<User> userList = new ArrayList<>();

    public Role(){};

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role1 = (Role) o;
        return Objects.equals(role, role1.role) &&
                Objects.equals(userList, role1.userList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(role, userList);
    }

    @Override
    public String getAuthority() {
        return getRole();
    }

    public static class Builder{
        private Role role;
        public Builder(){
            role = new Role();
        }
        public Builder withRole(String role){
            this.role.role = role;
            return this;
        }
        public Role build(){
            return role;
        }
    }
}
