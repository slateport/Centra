import draw2d from 'draw2d'
import Direction from "./Direction";

export default class PositionOnParentBoundsLocator extends draw2d.layout.locator.Locator {

    private side;
    private bias;
    private parent;

    constructor(options) {
        super(options.parent);

        this.parent = options.parent;
        this.side = options.side;
        this.bias = options.bias;
    }

    relocate (index, target) {
        // @ts-ignore
        var parentBoundingBox = this.parent.getBoundingBox(),
            direction = Direction,
            x, y;

        if (this.side === direction.UP || this.side === direction.DOWN) {
            x = parentBoundingBox.getWidth() * this.bias;
            y = (this.side === direction.UP) ? 0 : parentBoundingBox.getHeight();
        } else if (this.side === direction.LEFT || this.side === direction.RIGHT) {
            y = parentBoundingBox.getHeight() * this.bias;
            x = (this.side === direction.LEFT) ? 0 : parentBoundingBox.getWidth();
        }

        target.setPosition(x, y);
    }
}