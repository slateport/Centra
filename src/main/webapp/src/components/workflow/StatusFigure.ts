import draw2d from 'draw2d'


export default class StatusFigure extends draw2d.shape.basic.Rectangle {
    private label
    private _edge
    private _fill

    constructor() {
        super();

        const locator = new draw2d.layout.locator.CenterLocator(this);
        const edge = this._edge = new draw2d.shape.basic.Rectangle();
        edge.setRadius(4);
        edge.setStroke(0);
        // @ts-ignore
        this.add(edge, locator);

        const fill = this._fill = new draw2d.shape.basic.Rectangle();
        fill.setRadius(3);
        fill.setStroke(0);
        // @ts-ignore
        this.add(fill, locator);

        const label = this.label = new draw2d.shape.basic.Label();
        label.setBold(true);
        label.setFontFamily("Arial");
        label.setFontSize(11);
        label.setStroke(0);
        // @ts-ignore
        this.add(label, locator);

    }
    setText (text)  {
        this.label.setText(text.toUpperCase());
        this._resize();
    }

    setTextColor (colour) {
        this.label && this.label.setFontColor(colour);
    }

    _resize () {
        var width = Math.max(this.label.getBoundingBox().getWidth() + 40, 60);
        // @ts-ignore
        this.setDimension(width, 30);
    }

    setDimension (width, height) {
        const isInitialised = !!this._edge;
        super.setDimension(width, height);

        if (isInitialised) {
            this._edge.setDimension(width, height);
            this._fill.setDimension(width - 2, height - 2);
        }
    }
}