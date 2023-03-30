package models;

import enums.ProjectAccessType;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
@Builder(setterPrefix = "set")
public class Project {

    private String projectName;
    private String projectCode;
    private String projectDescription;
    private ProjectAccessType projectAccessType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return projectName.equals(project.projectName)
                && projectCode.equals(project.projectCode)
                && Objects.equals(projectDescription, project.projectDescription)
                && projectAccessType == project.projectAccessType;
    }

    public static class ProjectBuilder {
        public ProjectBuilder() {

        }
    }
}
