import draw2d from 'draw2d'
import BaseStatusView from "./BaseStatusView";
import Draw2DUtilities from "./Draw2DUtilities";
import Direction from "./Direction";

export default class InitialStatusView extends BaseStatusView {
    private port;

    constructor(options) {
        super(options);
        this.model = options.model
    }

    createFigure() {
        var figure, fill;

        figure = this.figure = new draw2d.shape.basic.Circle(30);
        figure.setBackgroundColor("#C0C0C0");
        figure.setStroke(0);
        figure.setSelectable(false);
        figure.snapToGeometry = true;

        // fill = this.fill = new draw2d.shape.basic.Circle(30);
        // fill.setBackgroundColor("#C0C0C0");
        // fill.setStroke(0);

        // figure.add(fill, new draw2d.layout.locator.CenterLocator(figure));
        this.canvas.add(figure);

        // Draw2DUtilities.setCursor(fill, "move");
        this.addPort();
        this.positionFigure();
        return figure;
    }

    addPort () {
        let port = new draw2d.layout.locator.XYRelPortLocator(this.figure.width,this.figure.height * 2);
        this.figure.createPort('output',port);
        // const direction = Direction.DOWN

        // this.port = this.createPort({
        //     connectionDirection: direction
        // });

        // const locator = new draw2d.layout.locator.Locator({
        //     parent: this.figure,
        //     side: direction,
        //     bias: 0.5
        // });

        // this.figure.addPort(this.port, locator);
    }

    getBoundingBox () {
        return this.figure.getBoundingBox();
    }

    getPorts () {
        return [this.port];
    }

    isSelected () {
        return false;
    }

    render(): this {
        super.render();
        this.positionFigure();
        return this;
    }
}