import _ from 'underscore'

export default class WorkflowModel {
    private readonly _name;
    private readonly _states;
    private readonly _transitions;
    private readonly _permissions;

        constructor(name, states, transitions, permissions) {
        this._name = name;
        this._states = states;
        this._transitions = transitions;
        this._permissions = permissions;
    }


    name() {
        return this._name;
    }

    states() {
        return this._states;
    }

    transitions() {
        return this._transitions;
    }

    permissions() {
        return this._permissions;
    }

    getInitialTransitionDestinations () {
        return _.map(this._getInitialTransitions(), function (transition) {
            return transition.target;
        });
    }

    _getInitialTransitions () {
        return this.transitions().filter(function (transition) {
            return transition.isInitial;
        });
    }
}