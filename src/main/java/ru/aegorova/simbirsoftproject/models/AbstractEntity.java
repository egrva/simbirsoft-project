package ru.aegorova.simbirsoftproject.models;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@MappedSuperclass
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractEntity implements Serializable {

    private Long id;
    private ZonedDateTime created;
    private ZonedDateTime updated;
    private Long version;

    AbstractEntity(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    @Column(name = "created", updatable = false)
    public ZonedDateTime getCreated() {
        return created;
    }

    @Column(name = "updated", insertable = false)
    public ZonedDateTime getUpdated() {
        return updated;
    }

    @Version
    public Long getVersion() {
        return version;
    }

    @PrePersist
    public void toCreate() {
        setCreated(ZonedDateTime.now(ZoneId.of("Europe/Moscow")));
    }

    @PreUpdate
    public void toUpdate() {
        setUpdated(ZonedDateTime.now(ZoneId.of("Europe/Moscow")));
    }
}