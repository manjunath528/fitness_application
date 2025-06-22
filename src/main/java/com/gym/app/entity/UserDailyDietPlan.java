package com.gym.app.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "user_daily_plan")
public class UserDailyDietPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_sequence_user_daily_plan")
    @SequenceGenerator(name = "id_sequence_user_daily_plan", sequenceName = "sequence_user_daily_plan", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "login_id")
    private String loginId;

    @Column(name = "calories_goal")
    private Long caloriesGoal;

    @Column(name = "protein_goal")
    private Long proteinGoal;

    @Column(name = "carbs_goal")
    private Long carbsGoal;

    @Column(name = "fats_goal")
    private Long fatsGoal;

    @Column(name = "created_ts")
    private Date createdTs;

    @Column(name = "updated_ts")
    private Date updatedTs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public Long getCaloriesGoal() {
        return caloriesGoal;
    }

    public void setCaloriesGoal(Long caloriesGoal) {
        this.caloriesGoal = caloriesGoal;
    }

    public Long getProteinGoal() {
        return proteinGoal;
    }

    public void setProteinGoal(Long proteinGoal) {
        this.proteinGoal = proteinGoal;
    }

    public Long getCarbsGoal() {
        return carbsGoal;
    }

    public void setCarbsGoal(Long carbsGoal) {
        this.carbsGoal = carbsGoal;
    }

    public Long getFatsGoal() {
        return fatsGoal;
    }

    public void setFatsGoal(Long fatsGoal) {
        this.fatsGoal = fatsGoal;
    }

    public Date getCreatedTs() {
        return createdTs;
    }

    public void setCreatedTs(Date createdTs) {
        this.createdTs = createdTs;
    }

    public Date getUpdatedTs() {
        return updatedTs;
    }

    public void setUpdatedTs(Date updatedTs) {
        this.updatedTs = updatedTs;
    }

    @Override
    public String toString() {
        return "DailyPlan{" +
                "id=" + id +
                ", loginId='" + loginId + '\'' +
                ", caloriesGoal=" + caloriesGoal +
                ", proteinGoal=" + proteinGoal +
                ", carbsGoal=" + carbsGoal +
                ", fatsGoal=" + fatsGoal +
                ", createdTs=" + createdTs +
                ", updatedTs=" + updatedTs +
                '}';
    }

}

