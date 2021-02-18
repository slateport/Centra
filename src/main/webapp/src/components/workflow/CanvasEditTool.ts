import draw2d from 'draw2d';

export default class CanvasEditTool {

    private _id;
    private _circle: draw2d.shape.basic.Circle; 
    private _rect: draw2d.shape.basic.Rectangle;
    private _canvas: draw2d.Canvas;
    // private _commandStack: draw2d.command.CommandStack;
    private _commandAdd: draw2d.command.CommandAdd;
    private _command: draw2d.command.Command;
    constructor(id) {
        this. _id = id;
        this._canvas = new draw2d.Canvas(this._id);
        
    }

    createToolBox() {
        this._canvas.installEditPolicy(new draw2d.policy.canvas.SnapToInBetweenEditPolicy());
        this._canvas.installEditPolicy(new draw2d.policy.canvas.SnapToCenterEditPolicy());
    }

    get canvasView(): draw2d.Canvas {
        return this._canvas;
    }

    addNewNode(type, x, y) {
        switch(type) {
            case 'rectangle':
                this._rect = new draw2d.shape.basic.Rectangle({
                    x: x,
                    y: y,
                    bgColor: "#c0c0c0",
                    alpha  : 1,
                    stroke: 0,
                    width: 80,
                    height: 35,
                    radius: 4 
                  });
                  this._command = new draw2d.command.Command(this._rect.getId());
                //   this._canvas.getCommandStack().excute(this._command);
                  this._canvas.add(this._rect);
                  break;
            case 'circle':
                this._circle = new draw2d.shape.basic.Circle({
                    x:x,y:y, stroke:0, color:"#3d3d3d", bgColor:"#c0c0c0"
                });
                this._canvas.add(this._circle);
                break;
        }
    }

    undo() {
        this._canvas.getCommandStack().undo();
    }

    redo() {
        this._canvas.getCommandStack().redo();
    }

    delete() {
        let selection = this._canvas.getSelection();
        this._canvas.getCommandStack().startTransaction(draw2d.Configuration.i18n.command.deleteShape);
        selection.each((index, figure) => {
            if (figure instanceof draw2d.Connection) {
                if (selection.contains(figure.getSource().getRoot()) || selection.contains(figure.getTarget().getRoot())) {
                  return
                }
            }
            let cmd = figure.createCommand(new draw2d.command.CommandType(draw2d.command.CommandType.DELETE))
            if (cmd !== null) {
                this._canvas.getCommandStack().execute(cmd)
            }
        });
        this._canvas.getCommandStack().commitTransaction();
    }

    setGridView() {
        this._canvas.installEditPolicy(new draw2d.policy.canvas.ShowGridEditPolicy());
    }

    disableGridView() {
        this._canvas.uninstallEditPolicy(new draw2d.policy.canvas.ShowGridEditPolicy());
    }

    getSelectedFigure() {
        return this._canvas.getSelection().getPrimary();
    }

    changeBGColor(figure: draw2d.Figure, color) {
        console.log('=============>?', figure, color);
        figure.attr({
            "bgColor": color
        })
    }

    setFigureLabel(string) {
        let label = new draw2d.shape.basic.Label({text:string});
        label.setFontColor('#ffffff');
        label.setOutlineStroke(0);
        label.setOutlineColor('transparent');
        label.setStroke(0);
        this.getSelectedFigure().add(label, new draw2d.layout.locator.CenterLocator());
    }
}