package dev.conductor.centra.domain.workflow.entities;

import java.util.List;

public class FlowSchema {
    private String type;
    private String id;
    private int x;
    private int y;
    private int height;
    private int width;
    private int alpha;
    private boolean selectable;
    private boolean draggable;
    private int angle;
    private List userData;
    private String cssClass;
    private List ports;
    private String bgColor;
    private String color;
    private int stroke;
    private int radius;
    private String dasharray;
    private int outlineStroke;
    private String outlineColor;
    private String policy;
    private List vertex;
    private String router;
    private Object source;
    private Object target;

    public FlowSchema() {};

    public FlowSchema(String type, String id, int x, int y, int height, int width, int alpha, boolean selectable, boolean draggable, int angle, List userData, String cssClass, List ports, String bgColor,
                      String color, int stroke, int radius, String dasharray, int outlineStroke, String outlineColor, String policy, List vertex, String router, Object source, Object target) {
        this.type = type;
        this.id = id;
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.alpha = alpha;
        this.selectable = selectable;
        this.draggable = draggable;
        this.angle = angle;
        this.userData = userData;
        this.cssClass = cssClass;
        this.ports = ports;
        this.bgColor = bgColor;
        this.color = color;
        this.stroke = stroke;
        this.radius = radius;
        this.dasharray = dasharray;
        this.outlineColor = outlineColor;
        this.outlineStroke = outlineStroke;
        this.policy = policy;
        this.vertex = vertex;
        this.router = router;
        this.source = source;
        this.target = target;
    }
}
