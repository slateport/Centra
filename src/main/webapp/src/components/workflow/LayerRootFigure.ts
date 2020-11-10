import draw2d from 'draw2d'

export default class LayerRootFigure extends draw2d.shape.basic.Rectangle {
    constructor() {
        super(0, 0);

        // @ts-ignore
        this.setAlpha(0);
        // @ts-ignore
        this.setDeleteable(false);
        // @ts-ignore
        this.setDraggable(false);
        // @ts-ignore
        this.setResizeable(false);
        // @ts-ignore
        this.setSelectable(false);
    }
}