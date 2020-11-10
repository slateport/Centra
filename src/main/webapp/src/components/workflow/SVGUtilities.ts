import jquery from 'jquery'

const transformPoint = (element, matrix, x, y) => {
    var point = jquery(element).closest("svg").get(0).createSVGPoint();
    point.x = x;
    point.y = y;

    point = point.matrixTransform(matrix);
    return {x: point.x, y: point.y};
}


export default class SVGUtilities {

    static fromScreenToSVGCoordinate(element, x ,y) {
        element = jquery(element).get(0)
        return transformPoint(element, element.getScreenCTM().inverse(), x, y);
    }
}