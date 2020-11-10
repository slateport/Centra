import _ from 'underscore';
import draw2d from 'draw2d'
import StatusView from "./StatusView";

const getHeight = (statusView) => {
    return statusView.getBoundingBox().getHeight() + Positioner.gapBetweenStatuses;
}

export default class Positioner {
    public static gapBetweenStatuses = 20;

    positionStatuses (options) {
        var positionCentre,
            positionedStatuses,
            top;

        positionCentre = new draw2d.geo.Point(
            options.viewBox.getCenter().getX(),
            options.viewBox.getY() + options.viewBox.getHeight() * 0.2
        );

        function calculateTop(statusViews) {
            var groupHeight = _.reduce(_.map(statusViews, (statusView: StatusView) => {
                if (!statusView) return 20;
                return statusView.getBoundingBox().getHeight() + 20
            }), (memo, value) => memo + value);
            top = positionCentre.getY() - groupHeight / 2;
        }

        function positionStatusView(statusView) {
            if (!statusView) return;
            statusView.setPosition(positionCentre.getX() - statusView.getBoundingBox().getWidth() / 2, top);
            top += getHeight(statusView);
        }

        positionedStatuses = options.statusViews;
        positionedStatuses.sort(this.statusViewComparator(options.workflowModel));
        _.chain(positionedStatuses).tap(calculateTop).each(positionStatusView);
        return positionedStatuses;
    }

    getHeight (statusView) {
        return statusView.getBoundingBox().getHeight() + Positioner.gapBetweenStatuses;
    }

    hasCoordinates(statusView) {
        return false
    }

    statusViewComparator(workflowModel) {
        var initialTransitionDestinations = workflowModel.getInitialTransitionDestinations();

        return function(statusViewOne, statusViewTwo) {
            var statusOneIsInitial,
                statusOneIsInitialTarget,
                statusOneModel = statusViewOne.model,
                statusTwoIsInitial,
                statusTwoIsInitialTarget,
                statusTwoModel = statusViewTwo.model;

            statusOneIsInitial = statusOneModel.entry;
            statusTwoIsInitial = statusTwoModel.entry;

            // Initial statuses go before ordinary ones.
            if (statusOneIsInitial && !statusTwoIsInitial) {
                return -1;
            }
            if (!statusOneIsInitial && statusTwoIsInitial) {
                return 1;
            }

            if (!statusOneIsInitial && !statusTwoIsInitial) {
                // Initial transition destination statuses go before other statuses.
                statusOneIsInitialTarget = _.contains(initialTransitionDestinations, statusOneModel);
                statusTwoIsInitialTarget = _.contains(initialTransitionDestinations, statusTwoModel);

                if (statusOneIsInitialTarget && !statusTwoIsInitialTarget) {
                    return -1;
                }
                if (!statusOneIsInitialTarget && statusTwoIsInitialTarget) {
                    return 1;
                }
            }

            // Otherwise just order based on 1
            return 1;
        };
    }
}