package com.example.sweets.entity.user;

import com.example.sweets.entity.base.BaseDomain;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Getter
@Setter
public class Role extends BaseDomain<UUID> {

    @Column(unique = true)
    private String name;
    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToMany(mappedBy = "roles")
    private List<User> userList = new ArrayList<>();

    public String getAuthority() {
        return name.toUpperCase();
    }

}
