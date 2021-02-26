import draw2d from 'draw2d';

export default class CanvasEditTool {

    private _id;
    private _circle: draw2d.shape.node.Start; 
    private _rect: draw2d.shape.node.Between;
    private _canvas: draw2d.Canvas;
    // private _commandStack: draw2d.command.CommandStack;
    private _commandAdd: draw2d.command.CommandAdd;
    private _command: draw2d.command.Command;
    // private _selectedFigure: draw2d.Figure;
    private _selectedLabel: draw2d.shape.basic.Label;
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
                this._rect = new draw2d.shape.node.Between({
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
                this._rect.resetPorts();
                this._canvas.add(this._rect);
                break;
            case 'circle':
                this._circle = new draw2d.shape.node.Start({
                    x:x,y:y, stroke:0, color:"#3d3d3d", bgColor:"#c0c0c0", radius: 50
                });
                this._circle.resetPorts();
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

    getSelectedFigure(): draw2d.Figure {
        // this._selectedFigure = this._canvas.getSelection().getPrimary();
        return this._canvas.getSelection().getPrimary();
    }

    changeBGColor(figure: draw2d.Figure, color) {
        figure.attr({
            "bgColor": color
        })
    }

    changeFontColor(color) {
        this._selectedLabel.setFontColor(color);
    }

    setFigureLabel(string, selectedFigure: draw2d.Figure, fontSize: number, color) {
        let label = new draw2d.shape.basic.Label({text:string});
        label.setFontColor(color);
        label.setFontSize(fontSize);
        label.setOutlineStroke(0);
        label.setOutlineColor('transparent');
        label.setStroke(0);
        selectedFigure.add(label, new draw2d.layout.locator.CenterLocator());
    }

    updateFigureLabel(string) {
        this._selectedLabel.setText(string);
    }

    getFontColor(): any {
        if(this._selectedLabel) {
            return this._selectedLabel.getFontColor();
        } else {
            return null;
        }
    }

    getFigureLabel(selectedFigure: draw2d.Figure): draw2d.shape.basic.Label {
        if(selectedFigure.getChildren().data.length > 0) {
            selectedFigure.getChildren().data.map(child => {
                if(child.getCssClass().indexOf('Label') > -1) {
                    this._selectedLabel =  child;
                }
            });
        } else {
            this._selectedLabel =  null;
        }
    }

    getLabelText(): string {
        if(this._selectedLabel) {
            return this._selectedLabel.getText();
        } else {
            return null;
        }
    }

    removeLabel(figure: draw2d.Figure) {
        if(this._selectedLabel) {
            figure.remove(this._selectedLabel);
        }
    }

    getFontSize(): number {
        if(this._selectedLabel) {
            return this._selectedLabel.getFontSize();
        } else {
            return null;
        }
    }

    setFontSize(size) {
        console.log(this._selectedLabel, size);
        if(this._selectedLabel) {
            this._selectedLabel.setFontSize(size);
        }
    }

    addPort(node: draw2d.shape.node.Node) {
        if(node) {
            node.createPort('output');
            node.laoyoutPorts();
        }
    }
}