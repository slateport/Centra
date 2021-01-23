import _ from 'underscore'

export default class WorkflowModel {
    private readonly _name;
    private readonly _states;
    private readonly _transitions;
    private readonly _permissions;
    private readonly _level;

        constructor(name, states, transitions, permissions, level) {
        this._name = name;
        this._states = states;
        this._transitions = transitions;
        this._permissions = permissions;
        this._level = level;
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

    level() {
        return this._level;
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