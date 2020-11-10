import _ from 'underscore';
import draw2d from 'draw2d'
import InitialStatusView from "./InitialStatusView";
import StatusView from "./StatusView";
import TransitionView from "./TransitionView";
import Draw2dCanvas from "./Draw2dCanvas";
import WorkflowModel from "./WorkflowModel";
import CanvasModel from "./CanvasModel";
import LayerRootFigure from "./LayerRootFigure";
import Positioner from "./Positioner";

export default class CanvasView {

    private canvasModel: CanvasModel;
    private workflowModel:  WorkflowModel
    private canvas: Draw2dCanvas;
    private immutable = false;
    private statusViews = [];
    private transitionViews = [];

    constructor(options) {
        this.canvasModel = options.canvasModel;
        this.immutable = options.immutable;
        this.workflowModel = options.workflowModel;


    }

    addStatus (statusModel) {
        var isInitial = statusModel.entry,
            // isSelected = this.canvasModel.get("selectedModel") === statusModel,
            statusView,
            viewClass;

        if (isInitial) {
            viewClass = InitialStatusView;
        } else {
            viewClass = StatusView;
        }

        statusView = new viewClass({
            canvas: this.canvas,
            getAllTargetPorts: _.bind(this.getAllTargetPorts, this),
            immutable: this.immutable,
            isPortDragged: false,
            model: statusModel,
            workflowModel: this.workflowModel
        }).render();

        this.statusViews.push(statusView);
        // isSelected && statusView.select();
        return statusView;
    }

    addTransition (transitionModel) {
        const isGlobalTransition = transitionModel.isGlobalTransition(),
            isLoopedTransition = transitionModel.isLoopedTransition(),
            // isSelected = this._canvasModel.get("selectedModel") === transitionModel,
            isSelected = false;
            const transitionView = this.getTransitionViewWithModel(transitionModel);

        if (transitionView) {
            return transitionView;
        }

        const view = new TransitionView({
            canvas: this.canvas,
            canvasModel: this.canvasModel,
            immutable: this.immutable,
            model: transitionModel,
            sourceView: this.getStatusViewWithModel(transitionModel.from),
            targetView: this.getStatusViewWithModel(transitionModel.to),
            workflowModel: this.workflowModel
        });

        view.render();

        this.transitionViews.push(transitionView);
        isSelected && transitionView.select();
        return transitionView;
    }

    getAllTargetPorts () {
        function isInitial(statusView) {
            return statusView.model.isInitial();
        }

        function getPorts(statusView) {
            return statusView.getPorts();
        }

        return _.chain(this.statusViews).reject(isInitial).flatMap(getPorts).value();
    }

    getTransitionViewWithModel (transitionModel) {
        return this.transitionViews.find(function (transitionView) {
            return transitionView.model === transitionModel;
        });
    }

    getStatusViewWithModel (statusModel) {
        return this.statusViews.find(function (statusView) {
            return statusView.model === statusModel;
        });
    }

    render(element) {
        this.canvas || (this.canvas = this.createCanvas(element));
        return this;
    }

    createCanvas(element) {
        // Figure layers from back to front.
        const layers = [
            "statuses",
            "selected-status",
            "transitions",
            "global-transitions",
            "looped-transitions-container",
            "looped-transitions",
            "highlighted-transition",
            "selected-transition",
            "transition-labels"
        ];

        const canvas = new Draw2dCanvas(element);
        canvas.createLayers(layers);
        return canvas;
    }

    getUniqueId () {
        var i = 0,
            id;

        do {
            i++;
            id = "workflow-designer" + i;
        } while (document.getElementById("#" + id));

        return id;
    }

    _shouldRender () {
        // @ts-ignore
        return this.canvas && this.canvas.html && this.canvas.html.is(":visible");
    }

    autoFit () {
        var boundingBox,
            boundingBoxCenter,
            fits,
            padding = 10,
            viewBox;

        if (!this._shouldRender()) {
            return false;
        }

        // Ensure that the SVG element's size is correct; otherwise auto
        // resizing will kick in and ruin out beautiful view box.
        this.canvas.fitToContainer();
        boundingBox = this.getCanvasBoundingBox();

        if (boundingBox) {
            boundingBox.scale(padding * 2, padding * 2);
            viewBox = new draw2d.geo.Rectangle(0, 0);

            // Does the bounding box fit in the ideal view box?
            fits = boundingBox.getHeight() <= viewBox.getHeight() &&
                boundingBox.getWidth() <= viewBox.getWidth();

            if (!fits) {

                this.canvas.setViewBox(boundingBox);
                viewBox = this.canvas.getViewBox();
            }

            // Center the diagram in the view box.
            boundingBoxCenter = boundingBox.getCenter();
            this.canvas.setViewBox(new draw2d.geo.Rectangle(
                boundingBoxCenter.getX() - viewBox.getWidth()/2,
                boundingBoxCenter.getY() - viewBox.getHeight()/2,
                viewBox.getWidth(),
                viewBox.getHeight()
            ));

            this.canvasModel.zoomLevel(this.canvas.getZoom());
        }
    }

    getCanvasBoundingBox () {
        var canvasBoundingBox,
            figures = this.canvas.getFigures().asArray(),
            lines = this.canvas.getLines().asArray();

        function addFigureBoundingBox(figure) {
            var boundingBox = figure.getBoundingBox();

            // if (canvasBoundingBox) {
            //     canvasBoundingBox = canvasBoundingBox.union(boundingBox);
            // } else {
                canvasBoundingBox = boundingBox;
            // }
        }

        function isIgnored(figure) {
            return figure instanceof LayerRootFigure;
        }

        _.chain(figures).reject(isIgnored).each(addFigureBoundingBox);
        _.each(lines, addFigureBoundingBox);
        return canvasBoundingBox;
    }

    positionNewStatuses () {
        var positionedStatusViews,
            statusViews = this.workflowModel.states().map(this._getStatusViewWithModel(this.statusViews));

        positionedStatusViews = (new Positioner()).positionStatuses({
            statusViews: statusViews,
            viewBox: this.canvas.getViewBox(),
            workflowModel: this.workflowModel
        });

        // Find transitions of automatically positioned statuses and reset their connection figures.
        // This will trigger transition source and target angle re-calculation.
        function isNonGlobalTransitionConnectedToPositionedStatus(transitionView) {
            return !transitionView.model.isGlobalTransition() && transitionView.isConnectedToAnyStatus(positionedStatusViews);
        }
        _.chain(this.transitionViews).filter(isNonGlobalTransitionConnectedToPositionedStatus).invoke("resetConnection");
    }

    _getStatusViewWithModel (statusViews) {
        return statusModel => {
            return statusViews.find(function (statusView) {
                return statusView.model === statusModel;
            });
        }
    }
}