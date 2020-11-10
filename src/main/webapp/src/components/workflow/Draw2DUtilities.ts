export default class Draw2DUtilities {
    static  setCursor (figure, cursor) {
        const target = figure.svgNodes || figure.shape;
        target && target.attr("cursor", cursor);
    }
}