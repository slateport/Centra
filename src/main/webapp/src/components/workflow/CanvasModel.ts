export default class CanvasModel {
    private workflowModel;
    private _zoomLevel;

    constructor(options) {
        this.workflowModel = options.workflowModel;
    }


    get zoomLevel() {
        return this._zoomLevel;
    }

    set zoomLevel(value) {
        this._zoomLevel = value;
    }
}