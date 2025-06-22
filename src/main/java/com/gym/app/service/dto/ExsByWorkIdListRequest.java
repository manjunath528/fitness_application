package com.gym.app.service.dto;

import java.util.List;

public class ExsByWorkIdListRequest {
        private List<Long> selectedWorkouts;

        public List<Long> getSelectedWorkouts() {
                return selectedWorkouts;
        }

        public void setSelectedWorkouts(List<Long> selectedWorkouts) {
                this.selectedWorkouts = selectedWorkouts;
        }

        @Override
        public String toString() {
                return "ExsByWorkIdListRequest{" +
                        ", selectedWorkouts=" + selectedWorkouts +
                        '}';
        }
}
