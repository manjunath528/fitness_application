package com.gym.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;

@Entity
@Table(name = "workout")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Workout implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_sequence_workout")
    @SequenceGenerator(name = "id_sequence_workout", sequenceName = "sequence_workout", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "workout_name")
    private String workout;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "membership_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Membership membership;

    @Column(name="membership_id",insertable = false, updatable = false)
    private Long membershipId;

    @Column(name="description")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long workoutId) {
        this.id = workoutId;
    }

    public String getWorkout() {
        return workout;
    }

    public void setWorkout(String workoutName) {
        this.workout = workoutName;
    }

    public Membership getMembership() {
        return membership;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }

    public Long getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(Long membershipId) {
        this.membershipId = membershipId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    @Override
    public String toString() {
        return "Workout{" +
                "workoutId=" + id +
                ", workoutName='" + workout + '\'' +
                ", membership=" + membership +
                ", membershipId=" + membershipId +
                ", description='" + description + '\'' +
                '}';
    }

}

