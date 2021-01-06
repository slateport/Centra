import draw2d from 'draw2d'
import _ from 'underscore'
import Draw2DView from "./Draw2DView";

export default abstract class BaseStatusView extends Draw2DView{

    protected fill;
    protected immutable = true;
    protected isPortDragged;
    protected getAllTargetPorts;
    protected workflowModel;
    protected model;

    constructor (options) {
        super(options);

        options = _.defaults({}, options, {
            immutable: false
        });

        this.immutable = options.immutable;
        this.isPortDragged = options.isPortDragged;
        this.getAllTargetPorts = options.getAllTargetPorts;
        this.workflowModel = options.workflowModel;
    }

    remove(){
        alert('to implement BaseStatusView::remove()')
    }

    /**
     * Creates and configures the view's Draw2D figures.
     *
     * @private
     */
    _createAndConfigureFigure () {
        var figure = this.createFigure(),
            instance = this;

        figure.onDrag = _.wrap(figure.onDrag, function (f) {
            f.apply(this, _.toArray(arguments).slice(1));
            //instance.trigger("drag");
        });

        figure.onDragEnd = _.wrap(figure.onDragEnd, function (f) {
            f.apply(this, _.toArray(arguments).slice(1));
            // instance._updatePosition();
            // instance.trigger("dragEnd");
        });

        if (this.immutable) {
            figure.setDeleteable(false);
            figure.setResizeable(false);
        } else {
            figure.installEditPolicy(new draw2d.policy.figure.DragDropEditPolicy());
        }

        return figure;
    }

    abstract createFigure();

    render () {
        this.figure || (this.figure = this._createAndConfigureFigure());
        return this;
    }

    createPort (options) {
        options = _.extend({}, options, {
            canvas: this.canvas,
            isPortDragged: this.isPortDragged,
            getAllTargetPorts: this.getAllTargetPorts
        });
        return new draw2d.HybridPort(options);
    }

    getAngleToFigure (figure) {
        var figureCentre = figure.getBoundingBox().getCenter(),
            thisCentre = this.figure.getBoundingBox().getCenter();

        return Math.atan2(
            figureCentre.getY() - thisCentre.getY(),
            figureCentre.getX() - thisCentre.getX()
        ) / Math.PI * 180;
    }

    getAngleToPort (port) {
        return this.getAngleToFigure(port)
    }

    getAngleToStatus (statusView) {
        return this.getAngleToFigure(statusView._figure);
    }

    getPortForAngle (angle) {
        function getAngleDifference(port) {
            const difference = Math.abs(angle - this._getAngleToFigure(port));
            return difference < 180 ? difference : Math.abs(difference - 360);
        }

        return _.min(this.getPorts(), getAngleDifference, this);
    }

    abstract getPorts(): draw2d.port

    positionFigure () {
        // if (this.model?.x && this.model?.y) {
        //     this.figure.setPosition(
        //         this.model.x,
        //         this.model.y
        //     );
        // }
        if(!this.model) {
            this.figure.setPosition(this.canvas.initialWidth / 2 - 25, 150);
        } else {
            switch(this.model.label) {
                case "TO DO":
                    this.figure.setPosition((this.canvas.initialWidth - this.figure.width) / 2, 220);
                    break;
                case "IN PROGRESS":
                    this.figure.setPosition((this.canvas.initialWidth - this.figure.width) / 2, 270);
                    break;
                case "DONE":
                    this.figure.setPosition((this.canvas.initialWidth - this.figure.width) / 2, 320);
                    break;
            }
        }
    }

    setPosition (x, y) {
        this.model.x = x;
        this.model.y = y;
    }
}