package com.gym.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "badge")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Badge implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_sequence_badge")
    @SequenceGenerator(name = "id_sequence_badge", sequenceName = "sequence_badge", allocationSize = 1)
    @Column(name="id")
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="description")
    private String description;
    @Column(name="criteria")
    private String criteria;// e.g., "WORKOUT_COUNT"
    @Column(name="target_value")
    private int targetValue;
    @Column(name="created_ts")
    private Timestamp createdTs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public int getTargetValue() {
        return targetValue;
    }


    public void setTargetValue(int targetValue) {
        this.targetValue = targetValue;
    }

    public Timestamp getCreatedTs() {
        return createdTs;
    }

    public void setCreatedTs(Timestamp createdTs) {
        this.createdTs = createdTs;
    }
    @Override
    public String toString() {
        return "Badge{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", criteria='" + criteria + '\'' +
                ", targetValue=" + targetValue +
                ", createdTs=" + createdTs +
                '}';
    }

}
