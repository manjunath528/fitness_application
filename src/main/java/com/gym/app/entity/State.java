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
@Table(name = "state")
@JsonIgnoreProperties(ignoreUnknown = true)
public class State implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_sequence_state")
    @SequenceGenerator(name = "id_sequence_state", sequenceName = "sequence_state", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "time_zone")
    private String timeZone;

    @Column(name = "state")
    private String state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Country country;

    @Column(name = "country_id", insertable = false, updatable = false)
    private Long countryId;

    @Column(name = "off_set")
    private String offset;

    @Column(name = "state_with_time_zone")
    private String stateWithTimeZone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getStateWithTimeZone() {
        return stateWithTimeZone;
    }

    public void setStateWithTimeZone(String stateWithTimeZone) {
        this.stateWithTimeZone = stateWithTimeZone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof State)) return false;

        State state1 = (State) o;

        return new EqualsBuilder().append(getId(), state1.getId()).append(getTimeZone(), state1.getTimeZone()).append(getState(), state1.getState()).append(getCountry(), state1.getCountry()).append(getCountryId(), state1.getCountryId()).append(getOffset(), state1.getOffset()).append(getStateWithTimeZone(), state1.getStateWithTimeZone()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getId()).append(getTimeZone()).append(getState()).append(getCountry()).append(getCountryId()).append(getOffset()).append(getStateWithTimeZone()).toHashCode();
    }

    @Override
    public String toString() {
        return "State{" +
                "id=" + id +
                ", timeZone='" + timeZone + '\'' +
                ", state='" + state + '\'' +
                ", country=" + country +
                ", countryId=" + countryId +
                ", offset='" + offset + '\'' +
                ", stateWithTimeZone='" + stateWithTimeZone + '\'' +
                '}';
    }
}