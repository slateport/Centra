import _ from 'underscore'
import Draw2DView from "./Draw2DView";

export default class TransitionView extends Draw2DView {
    private canvasModel: any;
    private immutable: any;
    private views: [];
    private workflowModel: any;
    private connection: any;
    private model;

    constructor (options) {
        super(options);
        this.canvasModel = options.canvasModel;
        this.immutable = options.immutable;
        _.reset(this.views, [options.sourceView, options.targetView]);
        this.workflowModel = options.workflowModel;
    }

    render () {
        if (this.canvas && this.canvas.html && this.canvas.html.is(":visible")) {
            this.connection || this.createConnection();
            this.connection.setText(this.model.label);
            this.setTextVisibility();
        }
        return this;
    }

    // TODO!
    createConnection () {
        var connection;

        // connection = new TransitionConnection(this.isInitialTransition());
        // // connection.onDeselect = _.bind(this._onDeselect, this);
        // // connection.onMouseEnter = _.bind(this._onMouseEnter, this);
        // // connection.onMouseLeave = _.bind(this._onMouseLeave, this);
        // // connection.onReconnect = _.bind(this.trigger, this, "reconnect", this);
        // // connection.onSelect = _.bind(this._onSelect, this);
        // connection.setSelectable(!this.immutable);
        //
        // // if (!this._immutable) {
        // //     connection.installEditPolicy(new LineSelectionFeedbackPolicy());
        // //     connection.onDoubleClick = _.bind(this.edit, this);
        // // }
        //
        // this.canvas.getLayer("transitions").addFigure(connection);
        // this.connection = this.figure = connection;
        // // this.setConnectionPorts(); // TODO
        // this.setTextVisibility();
    }

    isInitialTransition () {
        return false; //TODO
    }

    setTextVisibility () {
        // var hasName = !!this.model.get("name"),
        //     showLabels = this._canvasModel.get("showTransitionLabels"),
        //     visible = hasName && (showLabels || this.isHighlighted() || this._isSelected());

        this.connection.setTextVisible(true);
    }

    resetConnection () {
        if (this.canvas && this.connection) {
            this.canvas.removeFigure(this.connection)
            this.connection = null
        }
        this.render()
    }
}