package pl.edu.sportcalendar.group.controller;

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
public class GroupController {
    private final GroupService groupService;

    @GetMapping
    public ResponseWrapper<List<GroupDto>> getAll() {
        return ResponseWrapper.ok(groupService.getAll());
    }
}
