package pl.edu.sportcalendar.group.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import pl.edu.sportcalendar.group.dto.GroupDto;
import pl.edu.sportcalendar.group.model.Group;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class GroupMapperTest {
    private GroupMapper groupMapper;

    @BeforeEach
    void setup() {
        groupMapper = Mappers.getMapper(GroupMapper.class);
    }

    @Test
    void shouldMapToDto_whenInputIsValid() {
        Group group = new Group();
        group.setId(1L);
        group.setName("test");

        GroupDto dto = groupMapper.toDto(group);
        assertEquals(1L, dto.id());
        assertEquals("test", dto.name());
    }

    @Test
    void shouldReturnNull_whenGroupIsNull() {
        assertNull(groupMapper.toDto(null));
    }
}
