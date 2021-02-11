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
        // this._canvas = new draw2d.Canvas(this._id);
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

    getCommand(): any {
        // return this._commandStack.canRedo();
    }

    undo() {
        console.log('=============>undo');
        this._canvas.getCommandStack().undo();
    }

    delete() {
        console.log('delete -=--------------->');
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
}