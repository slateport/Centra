import React from "react";
import _ from 'underscore';
import { workflow } from '../../services'
import Canvas from "./Canvas"
import WorkflowModel from './WorkflowModel'


export default class WorkflowApplication extends React.Component<any, any>{
    private canvas: Canvas;
    private options;
     constructor(props) {
         super(props);
         this.options = _.defaults({}, props.options, {
             draft: false,
             immutable: false
         });

         this.state = {
             workflowModel: new WorkflowModel(
                 null,
                 [],
                 [],
                 null
             ),
             width: 0,
             height: 0
         }

         // if (!options.immutable) {
         //     this._layoutAutoSaver = new LayoutAutoSaver({
         //         workflowModel: this._workflowModel
         //     });
         // }
     }

    createCanvas (options) {
        options = _.extend(options, {
            el: options.element,
            workflowModel: options.workflowModel,
        });

        return new Canvas(options);
    }

    initialiseWorkflow (options) {
        return this.loadWorkflow(options)
    }

    loadWorkflow(options) {
        return workflow.getWorkflow(options.workflowId)
            .then(
                data => this.setState({
                    workflowModel: new WorkflowModel(data.name, data.states, data.transitions, null )}))

    }

    componentDidMount() {
        this.setState({width: ((window.innerWidth - 40) / 4 * 3) - 24, height: window.innerHeight / 3 * 2})
        this.initialiseWorkflow(this.options)
            .then(() => {
                this.canvas = this.createCanvas({ workflowModel: this.state.workflowModel });
                this.canvas.canvasView.render('workflow');
                this.state.workflowModel.states().map(state => this.canvas.canvasView.addStatus(state))
                this.canvas.canvasView.positionNewStatuses();
                // this.canvas.autoFit();
            })
    }

    render() {
         return (
             <div id={'workflow'} style={{width: this.state.width, height:this.state.height}}/>
         )
    }
}