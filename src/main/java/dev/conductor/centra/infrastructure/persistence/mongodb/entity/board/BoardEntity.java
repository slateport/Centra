package dev.conductor.centra.infrastructure.persistence.mongodb.entity.board;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BoardEntity {
    private String id;
    private String name;
    private BoardType type;
    private List<String> projectIds = new ArrayList<>();
    private List<BoardColumn> boardColumns = new ArrayList<>();
}
