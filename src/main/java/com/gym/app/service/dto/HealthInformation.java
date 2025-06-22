package com.gym.app.service.dto;


import java.io.Serializable;

public class HealthInformation implements Serializable {

    private Long age;

    private String gender;

    private Long height;

    private Long currentWeight;

    private Long goalWeight;

    private String activityLevel;

    private Long targetCalories;

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public Long getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(Long currentWeight) {
        this.currentWeight = currentWeight;
    }

    public Long getGoalWeight() {
        return goalWeight;
    }

    public void setGoalWeight(Long goalWeight) {
        this.goalWeight = goalWeight;
    }

    public String getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(String activityLevel) {
        this.activityLevel = activityLevel;
    }

    public Long getTargetCalories() {
        return targetCalories;
    }

    public void setTargetCalories(Long targetCalories) {
        this.targetCalories = targetCalories;
    }

    @Override
    public String toString() {
        return "HealthInformation{" +
                "age=" + age +
                ", gender='" + gender + '\'' +
                ", height=" + height +
                ", currentWeight=" + currentWeight +
                ", goalWeight=" + goalWeight +
                ", activityLevel='" + activityLevel + '\'' +
                ", targetCalories=" + targetCalories +
                '}';
    }
}
