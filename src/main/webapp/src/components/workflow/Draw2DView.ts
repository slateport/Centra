import draw2d from 'draw2d'

export default class Draw2DView {
    protected canvas: draw2d.Canvas;
    protected figure;
    protected layers = []

    constructor (options) {
        this.canvas = options.canvas;
    }

    /**
     * Remove the view from the canvas.
     */
    remove () {
        this.canvas.removeFigure(this.figure);
    }

    getLayer (name) {
        return this.canvas.layers && this.canvas.layers[name];
    }
}