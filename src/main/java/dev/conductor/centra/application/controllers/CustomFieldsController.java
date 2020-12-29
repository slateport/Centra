package dev.conductor.centra.application.controllers;

import dev.conductor.centra.domain.customField.api.CustomFieldService;
import dev.conductor.centra.domain.customField.entity.CustomField;
import dev.conductor.centra.domain.customField.entity.CustomFieldValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/customfields")
public class CustomFieldsController extends BaseController {

    private final CustomFieldService service;

    @Autowired
    public CustomFieldsController(CustomFieldService service) {
        this.service = service;
    }

    @PostMapping()
    public CustomField createCustomField(@RequestBody CustomField customField, Principal principal) {

        if (!isAdmin(principal)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        return service.createCustomField(customField);
    }

    @GetMapping("/issue/{id}")
    public List<CustomFieldValue> getCustomFieldValuesForIssues(@PathVariable String id) {
        return service.getCustomFieldValuesForIssue(getIssueByExternalId(id));
    }

    @PostMapping("/issue/{id}")
    public CustomFieldValue saveCustomFieldValue(@RequestBody CustomFieldValue value, @PathVariable String id) {
        CustomField customField = service.getCustomFieldById(value.getCustomFieldId());

        if (customField == null) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Custom Field not found!");
        }

        return service.saveValue(getIssueByExternalId(id), value);
    }

}
