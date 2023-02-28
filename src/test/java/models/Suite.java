package models;

import enums.ParentSuite;
import lombok.Builder;
import lombok.Getter;
import java.util.Objects;

@Getter
@Builder(setterPrefix = "set")
public class Suite {

    private String suiteName;
    private String description;
    private String preconditions;
    private ParentSuite parentSuite;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Suite suite = (Suite) o;
        return Objects.equals(suiteName, suite.suiteName)
                && Objects.equals(description, suite.description)
                && Objects.equals(preconditions, suite.preconditions)
                && parentSuite == suite.parentSuite;
    }

    public static class SuiteBuilder {
        public SuiteBuilder() {

        }
    }
}
