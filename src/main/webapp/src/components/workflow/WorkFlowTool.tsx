import { Card, CardContent, Grid } from '@material-ui/core';
import UndoIcon from '@material-ui/icons/Undo';
import RedoIcon from '@material-ui/icons/Redo';
import DeleteIcon from '@material-ui/icons/Delete';
import {Button as MuiButton, Menu, MenuItem} from '@material-ui/core';
import React, { useEffect, useState } from 'react'
import Draggable from 'react-draggable';
import styled from 'styled-components';
import CanvasEditTool from './CanvasEditTool';

const FlowEditor = styled.div`
    width: 100%;
    height: ${window.innerHeight - 158}px;
    border: 1px solid rgba(21, 29, 44, 0.1);
    display: flex;
    background: #ffffff;
    flex-direction: column;
`;

const EditToolBar = styled.div`
    width: 100%;
    height: 45px;
    background: linear-gradient(rgba(230, 230, 230, 0.77) 0px, rgba(230, 230, 230 ,0.66), rgba(230, 230, 230, 0.89) 100%);
    border-bottom: 1px solid rgba(21, 29, 44, 0.1);
    padding: 5px;
`;

const Button = styled(MuiButton)`
    color : ${props=> props.theme.header.color}
`;

const BlackUndoIcon = styled(UndoIcon)`
    color: #000000;
`;

const BlackRedoIcon = styled(RedoIcon)`
    color: #000000;
`;

const BlackDeleteIcon = styled(DeleteIcon)`
    color: #000000;
`;

const EditBoard = styled.div`
    width: 100%;
    display: flex;
    flex: 1;
`;

const EditMenuBar = styled.div`
    width: 200px;
    height: 100%;
    background: rgba(198, 198, 198, 0.44);
    border-right: 1px solid rgba(21, 29, 44, 0.1);
    position: relative;
`;

const EditMenuBack = styled.div`
    width: 100%;
    height: 100%;
`;

const EditMenuContent = styled.div`
    width: 100%;
    height: 100%;
    position: absolute;
    z-index: 1;
    top: 0;
    left: 0;
`;

const CanvasView = styled.div`
    flex: 1;
    height: 100%;
`;

const SquareNode = styled.div`
    width: 80px;
    height: 35px;
    margin: 10px auto 0 auto;
    display: flex;
    justify-content: center;
    align-items: center;
    background: #c0c0c0;
    border-radius: 4px;
`;

const CircleStarter = styled.div`
    width: 50px;
    height: 50px;
    border-radius: 50%;
    background: #c0c0c0;
    margin: 10px auto 0 auto;
`;

export default class WorkFlowTool extends React.Component<any, any>{
    private canvas: CanvasEditTool;
    constructor(props) {
        super(props);
        this.state = {
            squareModelState: false,
            circleStarterState: false,
        }
    }

    componentDidMount() {
        this.canvas = new CanvasEditTool('workflowtool');
        this.canvas.createToolBox();
    }

    modelHandleEvent(e, data){
        if(e.type === 'mouseup') {
            if(data.x > 120 ) {
                switch(data.node.id) {
                    case 'rectangle':
                        this.createNode(data.node.id, data.x -140, data.y + 70);
                        this.setState({squareModelState: false})
                        break;
                    case 'circle':
                        this.createNode(data.node.id, data.x - 125, data.y + 10);
                        this.setState({circleStarterState: false})
                        break;
                }
            }
        }
    }

    createNode(type, x, y){
        this.canvas.addNewNode(type, x, y);
        // let command = this.canvas.getCommand();
        // console.log('=============>', command);
    }

    undo() {
        this.canvas.undo();
    }

    delete() {
        this.canvas.delete();
    }


    render() {
        return (
            <FlowEditor>
                <EditToolBar>
                    <Button onClick={() => this.undo()}>
                        <BlackUndoIcon ></BlackUndoIcon>
                    </Button>
                    <Button>
                        <BlackRedoIcon></BlackRedoIcon>
                    </Button>
                    <Button onClick={() => this.delete()}>
                        <BlackDeleteIcon></BlackDeleteIcon>
                    </Button>
                </EditToolBar>
                <EditBoard>
                <EditMenuBar>
                    <EditMenuBack>
                        <CircleStarter></CircleStarter>
                        <SquareNode></SquareNode>
                    </EditMenuBack>
                    <EditMenuContent>
                        <Draggable
                            onDrag={(event, data) => this.modelHandleEvent(event, data)}
                            onStart={(event, data) => this.modelHandleEvent(event, data)}
                            onStop={(event, data) => this.modelHandleEvent(event, data)}
                            position={{x: 0, y: 0}}
                        >
                            <CircleStarter className="drag-box" id="circle"></CircleStarter>
                         </Draggable>
                         <Draggable
                            onDrag={(event, data) => this.modelHandleEvent(event, data)}
                            onStart={(event, data) => this.modelHandleEvent(event, data)}
                            onStop={(event, data) => this.modelHandleEvent(event, data)}
                            position={{x: 0, y: 0}}
                        >
                            <SquareNode className="drag-box" id="rectangle"></SquareNode>
                        </Draggable>
                    </EditMenuContent>
                </EditMenuBar>
                <CanvasView>
                    <div id="workflowtool" style={{width: '100%', height: '100%'}}></div>
                </CanvasView>
                </EditBoard>
            </FlowEditor>
        )
    }
    
}