package com.github.mobdev778.yusupova.core.designsystem.components.animatedtext.figure

internal class ImmutableFigure(
    private val points: XYMap<Point>,
    private val centerPoint: Point,
    private val startPoint: Point
) : Figure {

    override fun getPoint(x: Int, y: Int): Point? {
        return points.get(x, y)
    }

    override val size: Int = points.size()

    override fun getCenterPoint(): Point {
        return centerPoint
    }

    override fun getStartPoint(): Point {
        return startPoint
    }

    override fun iterator(): Iterator<Point> {
        return points.valueIterator()
    }
}
