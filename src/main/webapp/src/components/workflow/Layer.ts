import _ from 'underscore'
import Draw2dCanvas from "./Draw2dCanvas";
import LayerRootFigure from "./LayerRootFigure";

function insertFigure(figure, target) {
    var children = figure.getChildren().clone().asArray().reverse(),
        targetNode = target.svgNodes || target.shape;

    _.each(children, function (child) {
        insertFigure(child, target);
    });

    // This order is important; svgNodes must always come after shape.
    figure.svgNodes && targetNode && figure.svgNodes.insertAfter(targetNode);
    figure.shape && targetNode && figure.shape.insertAfter(targetNode);
}

export default class Layer {
    protected canvas: Draw2dCanvas;
    protected figures = [];

    constructor(canvas: Draw2dCanvas) {
        this.canvas = canvas;
        const placeholder = new LayerRootFigure();
        this.canvas.addFigure(placeholder)
        this.figures = [placeholder]
    }

    addFigure (figure) {
        figure.getCanvas() || this.canvas.addFigure(figure);
        figure._layer && figure._layer.removeFigure(figure);

        figure._layer = this;
        insertFigure(figure, _.last(this.figures));
        this.figures.push(figure);
    }

    removeFigure (figure) {
        this.figures = _.without(this.figures, figure);
    }
}