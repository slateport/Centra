import draw2d from 'draw2d'
import BaseStatusView from "./BaseStatusView";
import StatusFigure from "./StatusFigure";
import Direction from "./Direction";
import PositionOnParentBoundsLocator from "./PositionOnParentBoundsLocator";

export default class StatusView extends BaseStatusView {

    protected ports = [];

    constructor(options) {
        super(options);
        this.model = options.model
    }

    addPort(bias, direction) {
        // const port = this.createPort({connectionDirection: direction});
        // const locator = new PositionOnParentBoundsLocator({
        //     parent: this.figure,
        //     side: direction,
        //     bias: bias
        // });

        // this.figure.addPort(port, locator);

    }

    createFigure () {
        const figure = this.figure = new StatusFigure();
        // figure.onDeselect = _.bind(this._onDeselect, this);
        // figure.onMouseEnter = _.bind(this._onMouseEnter, this);
        // figure.onMouseLeave = _.bind(this._onMouseLeave, this);
        // figure.onSelect = _.bind(this._onSelect, this);
        //figure.setSelectable(!this.immutable); // TODO

        // this.listenTo(this.workflowModel, "validation:statuses", function(validationResult) {
        //     figure.setValidation(validationResult[this.model.get("stepId")]);
        // });

        // if (!this.immutable && this.workflowModel.get('permissions').get('editStatus')) {
        //     figure.onDoubleClick = _.bind(this.edit, this);
        // }

        this.getLayer("statuses").addFigure(figure);
        this.createPorts();
        this.positionFigure();
        return figure;
    }

    createPorts () {
        if(this.model.ports && this.model.ports.length > 0) {
            this.model.ports.map(port => {
                let newPort;
                switch (port.portPos) {
                    case "T1":
                        newPort = new draw2d.layout.locator.XYRelPortLocator(this.figure.width / 2, 0);
                        break;
                    case "TM":
                        newPort = new draw2d.layout.locator.XYRelPortLocator(this.figure.width, 0);
                        break;
                    case "T3":
                        newPort = new draw2d.layout.locator.XYRelPortLocator(this.figure.width / 2 * 3, 0);
                        break;
                    case "RM":
                        newPort = new draw2d.layout.locator.XYRelPortLocator(this.figure.width * 2, this.figure.height);
                        break;
                    case "B3":
                        newPort = new draw2d.layout.locator.XYRelPortLocator(this.figure.width  / 2 * 3, this.figure.height * 2);
                        break;
                    case "BM":
                        newPort = new draw2d.layout.locator.XYRelPortLocator(this.figure.width, this.figure.height * 2);
                        break;
                    case "B1":
                        newPort = new draw2d.layout.locator.XYRelPortLocator(this.figure.width /2 , this.figure.height * 2);
                        break;
                    case "LM":
                        newPort = new draw2d.layout.locator.XYRelPortLocator(0, this.figure.height);
                        break;
                }
                this.figure.createPort(port.portType, newPort);
            })
        }
        // this.ports = [
        //     // Top
        //     this.addPort(0.22, Direction.UP),
        //     this.addPort(0.5, Direction.UP),
        //     this.addPort(0.78, Direction.UP),

        //     // // Right
        //     this.addPort(0.5, Direction.RIGHT),

        //     // // Bottom
        //     this.addPort(0.78, Direction.DOWN),
        //     this.addPort(0.5, Direction.DOWN),
        //     this.addPort(0.22, Direction.DOWN),

        //     // // Left
        //     this.addPort(0.5, Direction.LEFT)
        // ];
    }

    getBackgroundColor () {
        var colour,
            isSubtle = this.isSubtle(),
            statusCategory = undefined; // get from the model and workflowStep
            // switch(this.model.label) {
            //     case "TO DO":
            //         colour = "#42526e";
            //         break;
            //     case "IN PROGRESS":
            //         colour = "#0053cc";
            //         break;
            //     case "DONE":
            //         colour = "#03875b";
            //         break;
            // }
        if (statusCategory) {
            colour = "#deebff";
        } else if (isSubtle) {
            colour = "#ffffff";
        } else {
            colour = "#4a6785";
        }

        return colour;
    }

    isSubtle() {
        // Change based on where we are in the workflow
        // isCurrentStepInWorkflow && !initial
        return false;
    }

    getBoundingBox () {
        return this.figure.getBoundingBox();
    }

    getPorts () {
        return this.ports;
    }

    getBorderColor(){
        return "#fff";
    }

    getTextColor () {
        return "#fff"
    }

    render() {
        super.render();
        this.figure.setBackgroundColor(this.getBackgroundColor());
        this.figure.setColor(this.getBorderColor());
        this.figure.setText(this.model.label);
        this.figure.setTextColor(this.getTextColor());
        this.positionFigure();
        return this;
    }
}