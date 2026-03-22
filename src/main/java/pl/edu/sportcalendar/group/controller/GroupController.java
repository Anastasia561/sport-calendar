package pl.edu.sportcalendar.group.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.sportcalendar.group.dto.GroupDto;
import pl.edu.sportcalendar.group.service.GroupService;
import pl.edu.sportcalendar.wrapper.ResponseWrapper;

import java.util.List;

@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
@Tag(name = "Groups", description = "Endpoints for managing event groups")
public class GroupController {
    private final GroupService groupService;

    @Operation(summary = "Get list of all groups")
    @GetMapping
    public ResponseWrapper<List<GroupDto>> getAll() {
        return ResponseWrapper.ok(groupService.getAll());
    }
}
