import draw2d from 'draw2d';

export default class CanvasEditTool {

    private _id;
    private _circle: draw2d.shape.node.Start; 
    private _rect: draw2d.shape.node.Between;
    private _canvas: draw2d.Canvas;
    private _commandAdd: draw2d.command.CommandAdd;
    private _command: draw2d.command.Command;
    private _selectedLabel: draw2d.shape.basic.Label;
    constructor(id) {
        this. _id = id;
        this._canvas = new draw2d.Canvas(this._id);
    }

    createToolBox() {
        this._canvas.installEditPolicy(new draw2d.policy.canvas.SnapToInBetweenEditPolicy());
        this._canvas.installEditPolicy(new draw2d.policy.canvas.SnapToCenterEditPolicy());
        this._canvas.installEditPolicy(new draw2d.policy.connection.DragConnectionCreatePolicy({createConnection: this.createConnection}))
    }

    createConnection() {
        var conn = new draw2d.Connection();
	    conn.setRouter(new draw2d.layout.connection.ManhattanConnectionRouter());
	    conn.setOutlineStroke(0);
	    conn.setOutlineColor("#303030");
	    conn.setStroke(2);
	    conn.setRadius(5);
	    conn.setColor('#00A8F0');
	    return conn;
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
                    height: 36,
                    radius: 4 
                  });
                this._command = new draw2d.command.Command(this._rect.getId());
                this._rect.resetPorts();
                this._canvas.add(this._rect);
                break;
            case 'circle':
                this._circle = new draw2d.shape.node.Start({
                    x:x,y:y, stroke:0, color:"#3d3d3d", bgColor:"#c0c0c0", width: 50, height: 50, radius: 300
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

    getSelectedFigure(): draw2d.shape.node.Node {
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

    addPort(node: draw2d.shape.node.Node, ports: any) {
        if(node) {
            let multiple = node.getCssClass().indexOf('Start') > -1 ? false : true;
            if(ports.topLeft) {
                let port1 = new draw2d.HybridPort();
                port1.setName(`${Date.now()}_topLeft`);
                node.addPort(port1, new draw2d.layout.locator.XYRelPortLocator(15.5,0));
            }
            if(ports.topCenter) {
                let port2 = new draw2d.HybridPort();
                port2.setName(`${Date.now()}_topCenter`);
                node.addPort(port2, new draw2d.layout.locator.XYRelPortLocator(multiple ? 50.5 : 50, 0));
            }
            if(ports.topRight)  {
                let port3 = new draw2d.HybridPort();
                port3.setName(`${Date.now()}_topRight`);
                node.addPort(port3, new draw2d.layout.locator.XYRelPortLocator(85.5,0));
            }
            if(ports.middleLeft) {
                let port4 = new draw2d.HybridPort();
                port4.setName(`${Date.now()}_middleLeft`);
                node.addPort(port4, new draw2d.layout.locator.XYRelPortLocator(0, multiple ? 50.5 : 50));
            }
            if(ports.middleRight) {
                let port5 = new draw2d.HybridPort();
                port5.setName(`${Date.now()}_middleRight`);
                node.addPort(port5, new draw2d.layout.locator.XYRelPortLocator(multiple ? 98.5 : 100, multiple ? 50.5 : 50));
            }
            if(ports.bottomLeft) {
                let port6 = new draw2d.HybridPort();
                port6.setName(`${Date.now()}_bottomLeft`);
                node.addPort(port6, new draw2d.layout.locator.XYRelPortLocator(15.5,94));
            }
            if(ports.bottomCenter) {
                let port7 = new draw2d.HybridPort();
                port7.setName(`${Date.now()}_bottomCenter`);
                node.addPort(port7, new draw2d.layout.locator.XYRelPortLocator(multiple ? 50.5 : 50, multiple ? 94: 100));
            }
            if(ports.bottomRight) {
                let port8 = new draw2d.HybridPort();
                port8.setName(`${Date.now()}_bottomRight`);
                node.addPort(port8, new draw2d.layout.locator.XYRelPortLocator(85.5, 94));
            }
            node.layoutPorts();
        }
    }

    saveToJSON() {
        let  writer = new draw2d.io.json.Writer();
        writer.marshal(this._canvas, function(json){
            var jsonTxt = JSON.stringify(json,null,2);
            console.log('canvas json ====>', jsonTxt);
        }); 
    }
}