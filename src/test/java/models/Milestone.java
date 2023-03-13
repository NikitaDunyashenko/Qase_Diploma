package models;

import enums.StatusMilestone;
import lombok.Builder;
import lombok.Getter;
import java.util.Objects;

@Getter
@Builder(setterPrefix = "set")
public class Milestone {

    private String milestoneName;
    private String description;
    private StatusMilestone statusMilestone;
    private String date;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Milestone milestone = (Milestone) o;
        return Objects.equals(milestoneName, milestone.milestoneName)
                && Objects.equals(description, milestone.description)
                && statusMilestone == milestone.statusMilestone
                && Objects.equals(date, milestone.date);
    }

    public static class MilestoneBuilder {
        public MilestoneBuilder() {

        }
    }

}
