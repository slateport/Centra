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
                 null,
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
            .then(resp =>  {
                let data  = resp;
                data.states.push({entry: true, isTerminus: false, label: "INIT"});
                this.setState({workflowModel: new WorkflowModel(data.name, data.states, data.transitions, null, data.level )});
            })

    }

    componentDidMount() {
        this.initialiseWorkflow(this.options)
            .then(() => {
                if(this.state.workflowModel._level === 1) {
                    this.setState({width: ((window.innerWidth - 40) / 4 * 3) - 112, height: 700})
                } else {
                    this.setState({width: ((window.innerWidth - 40) / 4 * 3) - 112, height: 500})
                }
                this.canvas = this.createCanvas({ workflowModel: this.state.workflowModel });
                this.canvas.canvasView.render('workflow');
                this.state.workflowModel.states().map(state => {
                    this.canvas.canvasView.addStatus({...state, level: this.state.workflowModel._level});
                })
                this.canvas.canvasView.positionNewStatuses();
                this.canvas.canvasView.setConnectionStatusView();
                // this.canvas.autoFit();
            })
    }

    render() {
         return (
             <div id={'workflow'} style={{width: this.state.width, height:this.state.height}}/>
         )
    }
}