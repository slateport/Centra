package dev.conductor.centra.data.defualt;

import dev.conductor.centra.entities.IssueType;
import dev.conductor.centra.service.IssueTypeService;
import org.springframework.stereotype.Component;

@Component
public class DefaultInstall {

    private final IssueTypeService issueTypeService;

    public DefaultInstall(IssueTypeService issueTypeService) {
        this.issueTypeService = issueTypeService;
    }

    public void createDefaultEntities(){
        createIssueTypeAndSchema();
    }

    protected void createIssueTypeAndSchema(){
        IssueType bug = issueTypeService.save(new IssueType("Bug"));
        IssueType epic = issueTypeService.save(new IssueType("Epic"));
        IssueType improvement = issueTypeService.save(new IssueType("Improvement"));
        IssueType newFeature = issueTypeService.save(new IssueType("New Feature"));
        IssueType story = issueTypeService.save(new IssueType("Story"));
        IssueType subTask = issueTypeService.save(new IssueType("Sub-task"));
        IssueType task = issueTypeService.save(new IssueType("Task"));


    }
}