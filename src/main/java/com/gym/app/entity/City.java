package com.gym.app.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;

/**
 * Created by Manjunath Reddy
 */
@Entity
@Table(name = "city")
@JsonIgnoreProperties(ignoreUnknown = true)
public class City implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_sequence_city")
    @SequenceGenerator(name = "id_sequence_city", sequenceName = "sequence_city", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "city")
    private String city;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private State state;

    @Column(name = "state_id", insertable = false, updatable = false)
    private Long stateId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof City)) return false;

        City city1 = (City) o;

        return new EqualsBuilder()
                .append(getId(), city1.getId())
                .append(getStateId(), city1.getStateId())
                .append(getCity(), city1.getCity())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .append(getCity())
                .append(getStateId())
                .toHashCode();
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", stateId=" + stateId +
                '}';
    }
}
