import _ from 'underscore'
import draw2d from 'draw2d'
import jquery from 'jquery'
import Layer from "./Layer";
import SVGUtilities from "./SVGUtilities";

export default class Draw2dCanvas extends draw2d.Canvas {
    [x: string]: any;
    protected paper;
    protected svgElement;
    protected layers;

    constructor(args) {
        super(args);

        this.paper.canvas.style.position = ""; // Draw2D doesn't like Raphael's sub-pixel rendering fix; kill it.
        this.svgElement = jquery(this.paper.canvas);


    }

    createLayers(args) {
        var layers = this.layers || (this.layers = {});

        _.each(_.flatten(arguments), function (name) {
            layers[name] || (layers[name] = new Layer(this));
        }, this);
    }

    addFigure(figure: draw2d.Figure) {
        super.add(figure)
    }

    fitToContainer () {
        var canvasHeight = Number(this.svgElement.attr("height")),
            canvasWidth = Number(this.svgElement.attr("width")),
            // @ts-ignore
            containerHeight = this.html.height(),
            // @ts-ignore
            containerWidth = this.html.width(),
            shouldFit = canvasHeight !== containerHeight || canvasWidth !== containerWidth;

        // @ts-ignore
        if (!this.html.is(":visible")) {
            return;
        }

        if (shouldFit) {
            this.paper.setSize(containerWidth, containerHeight);
            this.svgElement.css({
                height: containerHeight,
                width: containerWidth
            });
        }
    }

    setViewBox (rectangle) {
        this.paper.setViewBox(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
    }

    getViewBox () {
        var bottomRight,
            offset = jquery(this.html).offset(),
            origin;

        // With no viewBox attribute, the origin is (0, 0).
        origin = this.paper.canvas.getAttribute("viewBox");
        origin = origin && _.map(origin.split(" "), parseFloat).slice(0, 2) || [0, 0];

        // Subtract the document's scroll because this method expects a point in screen space.
        bottomRight = this.fromDocumentToCanvasCoordinate(
            offset.left + this.svgElement.width() - jquery(document).scrollLeft(),
            offset.top + this.svgElement.height() - jquery(document).scrollTop()
        );

        return new draw2d.geo.Rectangle(
            origin[0],
            origin[1],
            bottomRight.x - origin[0],
            bottomRight.y - origin[1]
        );
    }

    fromDocumentToCanvasCoordinate (x, y) {
        var point = SVGUtilities.fromScreenToSVGCoordinate(this.paper.canvas, x, y);
        return new draw2d.geo.Point(point.x, point.y);
    }
}