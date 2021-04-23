import { Button, Card, CardContent, FormControl, FormControlLabel, FormLabel, Grid, Input, MenuItem, Radio, RadioGroup, Select } from '@material-ui/core';
import UndoIcon from '@material-ui/icons/Undo';
import RedoIcon from '@material-ui/icons/Redo';
import DeleteIcon from '@material-ui/icons/Delete';
import GridOnIcon from '@material-ui/icons/GridOn';
import ArrowDropUpIcon from '@material-ui/icons/ArrowDropUp';
import ArrowLeftIcon from '@material-ui/icons/ArrowLeft';
import ArrowRightIcon from '@material-ui/icons/ArrowRight';
import ArrowDropDownIcon from '@material-ui/icons/ArrowDropDown';
import AddCircleIcon from '@material-ui/icons/AddCircle';
import SaveIcon from '@material-ui/icons/Save';
import {Button as MuiButton, Divider as MuiDivider, Typography as MuiTypography,} from '@material-ui/core';
import React, { useEffect, useState } from 'react';
import { ChromePicker, CompactPicker } from 'react-color';
import Draggable from 'react-draggable';
import styled from 'styled-components';
import CanvasEditTool from './CanvasEditTool';
import {spacing, SpacingProps} from "@material-ui/system";
import { RotateLeft } from '@material-ui/icons';
import { workflow } from '../../services';
import draw2d from 'draw2d';

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

const PortDirectButton = styled(MuiButton)`
 width: 30px;
 height: 30px;
 min-width: 30px;
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

const BlackSaveIcon = styled(SaveIcon)`
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

const NodeBgColorOption = styled.div`
    width: 65%;
    margin: auto;
    padding: 10px;
    display: flex;
`;

const NodeBgLabel = styled.div`
    width: 65%;
`;

const FontLabel = styled.div`
   width: 76%;
`;

const NodeBGSeletor = styled.div`
    width: 50%;
    border: 3px solid white;
    margin-top: 6px;
    padding: 2px;
    height: 24px;
    cursor: pointer;
    box-shadow: 1px 1px 4px 0 rgba(0, 0, 0, 0.15);   
`;

const BackDrop =  styled.div`
    position: fixed;
    inset: 0px;
    top: '0px';
    right: '0px';
    bottom: '0px';
    left: '0px';
`;

const InputLabel = styled.div`
    width: 100%;
    padding: 15px;
    display: flex;
`;

const FontListMenu = styled.div`
    width: 15%;    
`;

const FontSizeField = styled.div`
    width: 90%;
    margin: auto;
    display: flex;
`;

const FontSizeLabel = styled.div`
    width: 45%;
    display: flex;
`;

const FontColorLabel = styled.div`
    width: 45%;
    margin-left: 10px;
`;

const ColorPicker = styled(ChromePicker)`
    margin: 20px auto 0 auto;
`;

const FontPicker = styled(CompactPicker)`
    margin-left: 30px;
`;

const FontColorButton = styled.div`
    width: 23px;
    text-align: center;
    font-size: 20px;
    cursor: pointer;
    color: black;
    font-weight: bold; 
`;

const PortPositionTool = styled.div`
    width: fit-content;
    height: fit-content;
    margin: auto;
`;

const TopDirection =  styled.div`
    width: 100%;
    display: flex;
`;
const MiddleDirection =  styled.div`
    width: 100%;
    display: flex;
`;

const BottomDirection =  styled.div`
    width: 100%;
    display: flex;
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
            flowId: props.options.workflowId,
            color: '#c0c0c0',
            fontColor: '#ffffff',
            displayBgPicker: false,
            displayFontPicker: false,
            selectedFigure: null,
            grid: false,
            labelExist: false,
            figureLabel: null,
            selectedFontSize: 12,
            portType: 'output',
            port: 'bottomCenter',
            flowData: null,
            fontSizeList: [5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 22, 24, 26, 28, 30, 32]
        }
    }

    componentDidMount() {
        this.canvas = new CanvasEditTool('workflowtool');
        this.canvas.createToolBox();
        this.getFlowData();
    }
    
    getFlowData() {
        workflow.getWorkflow(this.state.flowId)
            .then(resp =>  {
                let data  = resp;
                console.log('data ========>', data);
                this.canvas.importJSON(data.flow);
                this.setState({flowData: data});
                // this.canvas.createToolBox(data.flow);
         });
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

    async handleMouseClick() {
        let selectedFigure = this.canvas.getSelectedFigure();
        if(selectedFigure) {
            console.log('selected figure ====>', selectedFigure);   
            this.setState({selectedFigure: selectedFigure});
            this.canvas.getFigureLabel(selectedFigure);
            let fontColor  = this.canvas.getFontColor();

            // console.log('portsTm =========>', portsTemp);
            this.setState({fontColor:fontColor ?  `rgba(${fontColor.red}, ${fontColor.green}, ${fontColor.blue}, ${fontColor.alpha})` : '#ffffff'});
            this.setState({labelExist: this.canvas.getLabelText() === null ? false : true})
            this.setState({figureLabel: this.canvas.getLabelText() !== null ? this.canvas.getLabelText() : ''});
            this.setState({selectedFontSize: this.canvas.getFontSize() !== null ? this.canvas.getFontSize() : 12});
            let bgColor = selectedFigure.getBackgroundColor();
            this.setState({color: bgColor ? `rgba(${bgColor.red}, ${bgColor.green}, ${bgColor.blue}, ${bgColor.alpha})` : '#c0c0c0'});
        } else {
            this.setState({selectedFigure: null});
            this.setState({figureLabel: null});
        }
    }

    openColorPoppover = () => {
        this.setState({displayBgPicker: true});
    }

    openFontPoppover = () => {
        this.setState({displayFontPicker: true});
    }

    handleChange = (color) => {
        this.setState({ color: color.hex });
        this.canvas.changeBGColor(this.state.selectedFigure, color.hex);
    }

    handleFontChange = (color) => {
        this.setState({fontColor: color.hex});
        let selectedFigure = this.canvas.getSelectedFigure();
        if(selectedFigure) {
            this.canvas.getFigureLabel(selectedFigure);
            this.canvas.changeFontColor(color.hex);
            this.canvas.updateUserData(this.state.selectedFigure, 'color', color.hex);
        }
    }

    changeLabel = (e) => {
        console.log(e.target.value);
        this.setState({figureLabel: e.target.value});
    }

    addLabel = () => {
        if(this.state.figureLabel !== null) {
            this.canvas.setFigureLabel(this.state.figureLabel, this.state.selectedFigure, this.state.selectedFontSize, this.state.fontColor)
            this.canvas.updateUserData(this.state.selectedFigure, 'label', this.state.figureLabel);
            this.canvas.updateUserData(this.state.selectedFigure, 'font', {fontSize: this.state.selectedFontSize, color: this.state.fontColor });
        }
    }

    updateLabel = () => {
        if(this.state.figureLabel !== null) {
            this.canvas.updateFigureLabel(this.state.figureLabel);
            this.canvas.updateUserData(this.state.selectedFigure, 'label', this.state.figureLabel);
        }
    }

    removeLabel() {
        let selectedFigure = this.canvas.getSelectedFigure();
        if(selectedFigure) {
            this.canvas.getFigureLabel(selectedFigure);
            this.canvas.removeLabel(this.state.selectedFigure);
            this.setState({figureLabel: ''});
        }
    }

    changeFontSize = (e) => {
        this.setState({selectedFontSize: e.target.value});
        let selectedFigure = this.canvas.getSelectedFigure();
        if(selectedFigure) {
            this.canvas.getFigureLabel(selectedFigure);
            this.canvas.setFontSize(Number(e.target.value));
        }
    }

    handleClose = () => {
        this.setState({displayBgPicker: false, displayFontPicker: false});
    }

    setPortPosition = (val) => {
        this.setState({port: val});
    }

    selectPortType = (e) => {
        console.log('select =====>')
        this.setState({portType: e.target.value});
    }

    createPort =  () => {
        let selectedFigure = this.canvas.getSelectedFigure();
        console.log('selected figure ====>', selectedFigure);
        if(selectedFigure) {
            this.setState({selectedFigure: selectedFigure});
            this.canvas.addPort(selectedFigure, this.state.port, this.state.portType);
            this.canvas.updateUserData(selectedFigure, 'port', {position: this.state.port, type: this.state.portType});
        }
    }

    save = async () => {
       let json =  await this.canvas.saveToJSON();
       
       let data = {
           ...this.state.flowData,
           projectId: null,
           flow: JSON.parse(json)
       };
       console.log('flowdata======>', data);

       workflow.updateWorkflow(this.state.flowId, data)
        .then(resp => {
            console.log('response ====>', resp);
        })
        .catch(err => {
            console.log('error ====>', err);
        })
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
                        <Button1 onClick={() => this.save()}>
                            <BlackSaveIcon></BlackSaveIcon>
                        </Button1>
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
                            <NodeBgColorOption>
                                <NodeBgLabel>
                                    <Typography p={2} style={{fontWeight: 'bold'}}>
                                        BG Color
                                    </Typography>
                                </NodeBgLabel>
                                <NodeBGSeletor  style={{backgroundColor: this.state.color}} onClick={this.openColorPoppover}>
                                </NodeBGSeletor>
                            </NodeBgColorOption>
                            {this.state.displayBgPicker && (
                                <>
                                    <BackDrop onClick={this.handleClose}></BackDrop>
                                    <ColorPicker 
                                        color={this.state.color}
                                        onChange={ this.handleChange }>
                                    </ColorPicker>
                                </>
                            )}
                            </>
                        )}
                        {this.state.selectedFigure && (
                            <>
                                <Divider my={1} mt={5}/>
                                <Typography variant="h6" p={2}  gutterBottom>
                                    Setting Label
                                </Typography>
                                <InputLabel>
                                    <Input
                                        id="label"
                                        name="label"
                                        autoComplete="label"
                                        autoFocus
                                        style={{marginLeft: 5, marginRight: 15}}
                                        value={this.state.figureLabel}
                                        onChange={this.changeLabel}
                                    />
                                    {!this.state.labelExist && (
                                        <Button variant="contained" color="primary" onClick={() => this.addLabel()}>Add</Button>
                                    )}

                                    {this.state.labelExist && (
                                        <Button variant="contained" color="primary" onClick={() => this.updateLabel()}>Save</Button>
                                    )}
                                    <Button variant="contained" color="primary" style={{marginLeft: 5}} onClick={() => this.removeLabel()}>Remove</Button>
                                </InputLabel>
                                <FontSizeField>
                                    <FontSizeLabel>
                                        <Typography p={2} style={{fontWeight: 'bold'}}>
                                            Font Size
                                        </Typography>
                                    </FontSizeLabel>
                                    <FontListMenu>
                                        <Select
                                            id="font"
                                            name ="fontSize"
                                            value={this.state.selectedFontSize}
                                            onChange={this.changeFontSize}
                                            fullWidth
                                            defaultValue={this.state.selectedFontSize}
                                        >
                                            {this.state.fontSizeList.map((fontSize, index) =>
                                                <MenuItem value={fontSize} key={index}>{fontSize}</MenuItem>
                                            )}
                                        </Select>
                                    </FontListMenu>
                                    <FontColorLabel>
                                        <Typography p={2} style={{fontWeight: 'bold'}}>
                                            Font Color
                                        </Typography>
                                    </FontColorLabel>
                                    <FontColorButton onClick={this.openFontPoppover}>
                                        A
                                        <div style={{backgroundColor: this.state.fontColor, width: '100%', height: 3, marginTop: -3}}></div>
                                    </FontColorButton>
                                </FontSizeField>
                                {this.state.displayFontPicker && (
                                    <>
                                        <BackDrop onClick={this.handleClose}></BackDrop>
                                        <FontPicker 
                                            color={this.state.fontColor}
                                            onChange={ this.handleFontChange }>
                                        </FontPicker>
                                    </>
                                )}
                                <Divider my={1} mt={5}/>
                                <Typography variant="h6" p={2}  gutterBottom>
                                    Setting Port
                                </Typography>
                                <PortPositionTool>
                                    <TopDirection>
                                        <PortDirectButton style={{marginTop: 6, marginLeft: 6}} onClick={() => this.setPortPosition('topLeft')}>
                                            <ArrowDropUpIcon style={{transform:`rotate(-45deg)`, fontSize: 36, color: this.state.port === 'topLeft' ? '#000000' : '#7f7d7d'}}></ArrowDropUpIcon>
                                        </PortDirectButton>
                                        <PortDirectButton onClick={() => this.setPortPosition('topCenter')}>
                                            <ArrowDropUpIcon style={{fontSize: 36, color: this.state.port === 'topCenter' ? '#000000' : '#7f7d7d'}}></ArrowDropUpIcon>
                                        </PortDirectButton>
                                        <PortDirectButton style={{marginTop: 6, marginRight: 6}} onClick={() => this.setPortPosition('topRight')}>
                                            <ArrowDropUpIcon style={{transform:`rotate(45deg)`, fontSize:36, color: this.state.port === 'topRight' ? '#000000' : '#7f7d7d'}}></ArrowDropUpIcon>
                                        </PortDirectButton>
                                    </TopDirection>
                                    <MiddleDirection>
                                        <PortDirectButton onClick={() => this.setPortPosition('middleLeft')}>
                                            <ArrowLeftIcon style={{fontSize: 36, color: this.state.port === 'middleLeft' ? '#000000' : '#7f7d7d'}}></ArrowLeftIcon>
                                        </PortDirectButton>
                                        <PortDirectButton style={{marginLeft: 6, marginRight: 6}} onClick={() => this.createPort()}>
                                            <AddCircleIcon style={{fontSize: 36}}></AddCircleIcon>
                                        </PortDirectButton>
                                        <PortDirectButton onClick={() => this.setPortPosition('middleRight')}>
                                            <ArrowRightIcon style={{fontSize: 36, color: this.state.port === 'middleRight' ? '#000000' : '#7f7d7d'}}></ArrowRightIcon>
                                        </PortDirectButton>
                                    </MiddleDirection>
                                    <BottomDirection>
                                        <PortDirectButton  style={{marginLeft: 6, marginBottom: 6}} onClick={() => this.setPortPosition('bottomLeft')}>
                                            <ArrowDropDownIcon style={{transform:`rotate(45deg)`, fontSize: 36, color: this.state.port === 'bottomLeft' ? '#000000' : '#7f7d7d'}}></ArrowDropDownIcon>
                                        </PortDirectButton>
                                        <PortDirectButton style={{marginTop: 6}} onClick={() => this.setPortPosition('bottomCenter')}>
                                            <ArrowDropDownIcon style={{fontSize: 36, color: this.state.port === 'bottomCenter' ? '#000000' : '#7f7d7d'}}></ArrowDropDownIcon>
                                        </PortDirectButton>
                                        <PortDirectButton style={{marginRight: 6, marginBottom: 6}} onClick={() => this.setPortPosition('bottomRight')}>
                                            <ArrowDropDownIcon style={{transform:`rotate(-45deg)`, fontSize:36, color: this.state.port === 'bottomRight' ? '#000000' : '#7f7d7d'}}></ArrowDropDownIcon>
                                        </PortDirectButton>
                                    </BottomDirection>
                                </PortPositionTool>
                                <FormControl component="fieldset" style={{width: '100%', justifyContent: 'center', alignItems: 'center'}}>
                                    <FormLabel component="legend" style={{marginLeft: 40, color: 'black'}}>Port Type</FormLabel>
                                    <RadioGroup aria-label="gender" name="type" value={this.state.portType} onChange={this.selectPortType}>
                                        <FormControlLabel value="input" control={<Radio />} label="Input" />
                                        <FormControlLabel value="output" control={<Radio />} label="Output" />
                                    </RadioGroup>
                                    </FormControl>
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