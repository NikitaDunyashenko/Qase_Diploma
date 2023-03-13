package models;

import enums.*;
import lombok.Builder;
import lombok.Getter;
import java.util.Objects;

@Getter
@Builder(setterPrefix = "set")
public class TestCase {

    private String title;
    private String description;
    private String preConditions;
    private String postConditions;
    private String parameterTitle;
    private String parameterValues;
    private String stepAction;
    private String data;
    private String expectedResult;
    private String suite;
    private Status status;
    private Severity severity;
    private Priority priority;
    private Type type;
    private Layer layer;
    private IsFlaky isFlaky;
    private Behavior behavior;
    private AutomationStatus automationStatus;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestCase aTestCase = (TestCase) o;
        return Objects.equals(title, aTestCase.title)
                && Objects.equals(description, aTestCase.description)
                && Objects.equals(preConditions, aTestCase.preConditions)
                && Objects.equals(postConditions, aTestCase.postConditions)
                && Objects.equals(parameterTitle, aTestCase.parameterTitle)
                && Objects.equals(parameterValues, aTestCase.parameterValues)
                && Objects.equals(stepAction, aTestCase.stepAction)
                && Objects.equals(data, aTestCase.data)
                && Objects.equals(expectedResult, aTestCase.expectedResult)
                && Objects.equals(suite, aTestCase.suite)
                && status == aTestCase.status
                && severity == aTestCase.severity
                && priority == aTestCase.priority
                && type == aTestCase.type
                && layer == aTestCase.layer
                && isFlaky == aTestCase.isFlaky
                && behavior == aTestCase.behavior
                && automationStatus == aTestCase.automationStatus;
    }

    public static class TestCaseBuilder {
        public TestCaseBuilder() {

        }
    }
}
