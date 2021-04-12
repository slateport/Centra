import draw2d from 'draw2d';

export default class CanvasEditTool {

    private _id;
    private _node: draw2d.shape.node;
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
        let router = new draw2d.layout.connection.ManhattanConnectionRouter();
        // var conn = new draw2d.Connection();
	    // conn.setRouter(new draw2d.layout.connection.ManhattanConnectionRouter());
	    // conn.setOutlineStroke(0);
	    // conn.setOutlineColor("#303030") ;
	    // conn.setStroke(2);
	    // conn.setRadius(5);
        // conn.setColor('#00A8F0');
        // return conn;
        return new draw2d.Connection({
            outlineStroke: 0,
            outlineColor: '#303030',
            stroke: 2,
            radius: 5,
            color: '#00A8F0',
            router: router
        });
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
        return this._canvas.getSelection().primary;
    }

    changeBGColor(figure: draw2d.Figure, color) {
        figure.attr({
            "bgColor": color
        })
    }

    changeFontColor(color) {
        this._selectedLabel.setFontColor(color);
    }

    setFigureLabel(string, selectedFigure: draw2d.shape.node.Node, fontSize: number, color) {
        let label = new draw2d.shape.basic.Label({text:string});
        label.setFontColor(color);
        label.setFontSize(fontSize);
        label.setOutlineStroke(0);
        label.setOutlineColor('transparent');
        label.setStroke(0);
        selectedFigure.add(label, new draw2d.layout.locator.CenterLocator());
        selectedFigure.setUserData({text: string, color: color, fontSize: fontSize, outlineStroke: 0, outlineColor: 'transparent', stroke: 0})
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

    updateUserData(node: draw2d.shape.node.Node, type: string, value: any) {
        let data = node.getUserData() !== null ?  node.getUserData() : {};
        switch(type) {
            case "port":
                data.ports = data.ports ? [...data.port, value] : [value];
                node.setUserData(data);
                break;
            case "label":
                data.text = value;
                node.setUserData(data);
                break;
            case "font":
                data.fontSize = value.fontSize;
                data.color = value.color; 
                node.setUserData(data);
                break;
            case "color": 
                data.color = value;
                node.setUserData(data);
                break;
        }
    }

    addPort(node: draw2d.shape.node.Node, port: string, portType: string) {
        console.log('portType ====>', portType);
        if(node) {
            let multiple = node.getCssClass().indexOf('Start') > -1 ? false : true;
            if(port === 'topLeft') {
                let port1 = new draw2d.HybridPort();
                port1.setName(`${Date.now()}_topLeft`);
                node.createPort(portType, new draw2d.layout.locator.XYRelPortLocator(15.5,0));
            }
            if(port === 'topCenter') {
                let port2 = new draw2d.HybridPort();
                port2.setName(`${Date.now()}_topCenter`);
                node.createPort(portType, new draw2d.layout.locator.XYRelPortLocator(multiple ? 50.5 : 50, 0));
            }
            if(port === 'topRight')  {
                let port3 = new draw2d.HybridPort();
                port3.setName(`${Date.now()}_topRight`);
                node.createPort(portType, new draw2d.layout.locator.XYRelPortLocator(85.5,0));
            }
            if(port === 'middleLeft') {
                let port4 = new draw2d.HybridPort();
                port4.setName(`${Date.now()}_middleLeft`);
                node.createPort(portType, new draw2d.layout.locator.XYRelPortLocator(0, multiple ? 50.5 : 50));
            }
            if(port === 'middleRight') {
                let port5 = new draw2d.HybridPort();
                port5.setName(`${Date.now()}_middleRight`);
                node.createPort(portType, new draw2d.layout.locator.XYRelPortLocator(multiple ? 98.5 : 100, multiple ? 50.5 : 50));
            }
            if(port === 'bottomLeft') {
                let port6 = new draw2d.HybridPort();
                port6.setName(`${Date.now()}_bottomLeft`);
                node.createPort(portType, new draw2d.layout.locator.XYRelPortLocator(15.5,94));
            }
            if(port === 'bottomCenter') {
                let port7 = new draw2d.HybridPort();
                port7.setName(`${Date.now()}_bottomCenter`);
                node.createPort(portType, new draw2d.layout.locator.XYRelPortLocator(multiple ? 50.5 : 50, multiple ? 94: 100));
            }
            if(port === 'bottomRight') {
                let port8 = new draw2d.HybridPort();
                port8.setName(`${Date.now()}_bottomRight`);
                node.createPort(portType, new draw2d.layout.locator.XYRelPortLocator(85.5, 94));
            }
            node.layoutPorts();
            // let data = node.getUserData() !== null ? node.getUserData() : {};
            // console.log('dat ===>', data);
            // port =  data.port ? data.port.push(portType) : [portType];
            // node.attr({userData: {...data, port}});
        }
    }

    saveToJSON() {
        let  writer = new draw2d.io.json.Writer();
        writer.marshal(this._canvas, function(json){
            var jsonTxt = JSON.stringify(json,null,2);
            console.log('canvas json ====>', jsonTxt);
        }); 
    }

    importJSON(json) {
        json.map(item => {
            switch(item.type) {
                case "draw2d.shape.node.Start":
                    this.createNode('Start', item);
                    break;
                case "draw2d.shape.node.Between":
                    this.createNode('Between', item);
                    break;
                case "draw2d.Connection":
                    this.connectPort(item, json);
                    break;
                default: 
                    break;
            }
        });
    }

    async createNode(type, attr) {
        this._node = new draw2d.shape.node[type]({
            x: attr.x,
            y: attr.y,
            stroke: attr.stroke,
            color: attr.color,
            bgColor: attr.bgColor,
            width: attr.width,
            height: attr.height,
            radius: attr.radius,
            id: attr.id,
        });
        this._node.resetPorts();
        if(attr.userData.text) {
            let data = attr.userData;
            let label = new draw2d.shape.basic.Label({
                text: data.text,
                stroke: data.stroke,
                fontSize: data.fontSize,
                fontColor: data.color,
                outlineStroke: data.outlineStroke,
                outlineColor: data.outlineColor
            });
            this._node.add(label, new draw2d.layout.locator.CenterLocator());
        }
        if(attr.ports.length > 0) {
            attr.userData.ports.map(async(port) => {
                await this.addPort(this._node, port.position, port.type);
            });
        }
        this._canvas.add(this._node);
    }

    connectPort(data, json) {
        console.log('all ports ====>', this._canvas.getAllPorts());
        let ports = this._canvas.getAllPorts();
        let connection = new draw2d.Connection();
        connection.setRouter(new draw2d.layout.connection.ManhattanConnectionRouter());
        let source;
        let target;

        let nodes = this._canvas.getFigures();
        console.log('nodes =======>', nodes);
        nodes.data.forEach(node => {
            if(node.getId() === data.source.node) {
                source = node.getPort(data.source.port);
            }
            if(node.getId() === data.target.node) {
                target = node.getPort(data.target.port);
            }
        });
        connection.setSource(source);
        connection.setTarget(target);
        this._canvas.add(connection);
    }

    createConnect() {
        // var con = new draw2d.Connection({
        //     color: data.color,
        //     id: data.id,
        //     outlineColor: data.outlineColor,
        //     outlineStroke: data.outlineStroke,
        //     radius: data.radius,
        //     stroke: data.stroke
        // });
        
        console.log('all ports ====>', this._canvas.getAllPorts());
        let ports = this._canvas.getAllPorts();
        let connection = new draw2d.Connection();
        connection.setRouter(new draw2d.layout.connection.ManhattanConnectionRouter());
        connection.setSource(ports[0]);
        connection.setTarget(ports[1]);
        this._canvas.add(connection);
        // let source;
        // let target;
        // await ports.data.forEach(port => {
        //     if(port.name === data.source.port) {
        //         source = port
        //     }
        //     if(port.name === data.target.port) {
        //         target = port;
        //     }
        // });
        // let con = this.createConnection(ports[0], ports[1]);
        // this._canvas.add(con);
    }
}