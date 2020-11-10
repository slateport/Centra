import _ from 'underscore';
import CanvasModel from "./CanvasModel";
import CanvasView from "./CanvasView";

export default class Canvas {
    private _canvasModel: CanvasModel;
    private _canvasView: CanvasView;

    constructor(optionsArg) {
        const options = _.defaults(optionsArg, {
            actions: true,
            fullScreenButton: true,
            immutable: false
        });

        this._canvasModel = new CanvasModel({
            workflowModel: options.workflowModel
        })

        this._canvasView = new CanvasView({
            canvasModel: this._canvasModel,
            immutable: options.immutable,
            workflowModel: options.workflowModel
        })
    }


    get canvasModel(): CanvasModel {
        return this._canvasModel;
    }

    get canvasView(): CanvasView {
        return this._canvasView;
    }

    autoFit () {
        this._canvasView.autoFit();
    }
}