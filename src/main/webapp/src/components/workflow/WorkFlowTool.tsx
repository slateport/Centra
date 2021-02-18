import { Button, Card, CardContent, Grid, Input } from '@material-ui/core';
import UndoIcon from '@material-ui/icons/Undo';
import RedoIcon from '@material-ui/icons/Redo';
import DeleteIcon from '@material-ui/icons/Delete';
import GridOnIcon from '@material-ui/icons/GridOn';
import {Button as MuiButton, Divider as MuiDivider, Typography as MuiTypography,} from '@material-ui/core';
import React, { useEffect, useState } from 'react';
import { SketchPicker, ChromePicker } from 'react-color';
import Draggable from 'react-draggable';
import styled from 'styled-components';
import CanvasEditTool from './CanvasEditTool';
import {spacing, SpacingProps} from "@material-ui/system";

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
    display: flex;
`;

const ActionTool = styled.div`
    width: 50%;
    height: 100%;
    text-align: left;
`;

const PropertyTool = styled.div`
    width: 50%;
    height: 100%;
    text-align: right;
`;
const Button1 = styled(MuiButton)`
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

const BlackGridIcon = styled(GridOnIcon)`
    color: #000000;
`;

const EditBoard = styled.div`
    width: 100%;
    display: flex;
    flex: 1;
`;

const EditMenuBar = styled.div`
    width: 300px;
    height: 100%;
    background: rgba(198, 198, 198, 0.44);
    border-right: 1px solid rgba(21, 29, 44, 0.1);
    position: relative;
`;

const EditMenuBack = styled.div`
    width: 100%;
    height: 100%;
    margin-top: 50px;
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

const ColorPicker = styled(ChromePicker)`
    margin: 20px auto 0 auto;
`;

interface TypographyPropsType extends SpacingProps {
    component?: string
}

const Typography = styled(MuiTypography)<TypographyPropsType>(spacing);

const Divider = styled(MuiDivider)(spacing);

export default class WorkFlowTool extends React.Component<any, any>{
    private canvas: CanvasEditTool;
    constructor(props) {
        super(props);
        this.state = {
            squareModelState: false,
            circleStarterState: false,
            color: '#c0c0c0',
            selectedFigure: null,
            grid: false,
            figureLabel: null,
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
                        this.createNode(data.node.id, data.x -190, data.y + 110);
                        this.setState({squareModelState: false})
                        break;
                    case 'circle':
                        this.createNode(data.node.id, data.x - 175, data.y + 50);
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

    redo() {
        this.canvas.redo(); 
    }

    delete() {
        this.canvas.delete();
    }

    showGrid() {
        if(!this.state.grid) {
            this.canvas.setGridView();
            this.setState({grid: true});
        } else {
            this.canvas.disableGridView();
            this.setState({grid: false});
        }
    }

    handleMouseClick() {
        // console.log('selected figure=====+>', this.canvas.getSelectedFigure().getBackgroundColor( ));
        if(this.canvas.getSelectedFigure()) {
            this.setState({selectedFigure: this.canvas.getSelectedFigure()});
            let bgColor = this.canvas.getSelectedFigure().getBackgroundColor();
            this.setState({color: {r: bgColor.red, g: bgColor.green, b: bgColor.blue, a: bgColor.alpha}});
        } else {
            this.setState({selectedFigure: null});
        }
        
    }

    handleChange = (color) => {
        console.log('color =========>', color);
        this.setState({ color: color.hex });
        this.canvas.changeBGColor(this.state.selectedFigure, color.hex);
    }

    changeLabel = (e) => {
        console.log(e.target.value);
        this.setState({figureLabel: e.target.value});
    }

    addLabel = () => {
        console.log('==========>?');
        this.canvas.setFigureLabel(this.state.figureLabel)
    }


    render() {
        return (
            <FlowEditor>
                <EditToolBar>
                    <ActionTool>
                        <Button1 onClick={() => this.undo()}>
                            <BlackUndoIcon ></BlackUndoIcon>
                        </Button1>
                        <Button1 onClick={() => this.redo()}>
                            <BlackRedoIcon></BlackRedoIcon>
                        </Button1>
                        <Button1 onClick={() => this.delete()}>
                            <BlackDeleteIcon></BlackDeleteIcon>
                        </Button1>
                    </ActionTool>
                    <PropertyTool>
                        <Button1 onClick={() => this.showGrid()}>
                            <BlackGridIcon></BlackGridIcon>
                        </Button1>
                    </PropertyTool>
                </EditToolBar>
                <EditBoard>
                <EditMenuBar>
                    <EditMenuBack>
                        <CircleStarter></CircleStarter>
                        <SquareNode></SquareNode>
                    </EditMenuBack>
                    <EditMenuContent>
                        <Typography variant="h6" p={2} mt={1} gutterBottom >
                            Step Shapes
                        </Typography>
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
                        {this.state.selectedFigure && (
                            <>
                                <Divider my={1} mt={5}/>
                                <Typography variant="h6" p={2}  gutterBottom>
                                    Add Label
                                </Typography>
                                <Input
                                    id="label"
                                    name="label"
                                    autoComplete="label"
                                    autoFocus
                                    style={{marginLeft: 5, marginRight: 15}}
                                    value={this.state.figureLabel}
                                    onChange={this.changeLabel}
                                />
                                <Button color="primary" onClick={() => this.addLabel()}>Add</Button>
                                <Typography variant="h6" p={2}  gutterBottom>
                                    Background Color
                                </Typography>
                                <ColorPicker 
                                    color={this.state.color}
                                    onChange={ this.handleChange }>
                                </ColorPicker>
                            </>
                        )}
                    </EditMenuContent>
                </EditMenuBar>
                <CanvasView>
                    <div id="workflowtool" style={{width: '100%', height: '100%'}} onClick={() => this.handleMouseClick()}></div>
                </CanvasView>
                </EditBoard>
            </FlowEditor>
        )
    }
    
}