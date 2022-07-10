package application.app;

//import project.application.model.Vertex;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class EdgeDrawable {
    private double weightFromFirstToSecond, weightFromSecondToFirst;
    private Vertex first, second; // first - source, second - destination or versa
    private boolean directionFromFirstToSecond, directionFromSecondToFirst;
    private Line edgeLine;
    private Text weightView;
    private final static double radiusOfVertexCircle = 18.0;

    public EdgeDrawable(Vertex first, Vertex second, double weight){
        this.first = first;
        this.second = second;
        weightFromFirstToSecond = weight;
        directionFromFirstToSecond = true;
        edgeLine = new Line();
        weightView = new Text(first.getName() + "->" + second.getName() + ":" + Double.toString(weightFromFirstToSecond));
        double x1, x2, y1, y2;
        x1 = first.getCoordinates().getX();
        x2 = second.getCoordinates().getX();
        y1 = first.getCoordinates().getY();
        y2 = second.getCoordinates().getY();
        if (x1 > x2) {
            x1 -= radiusOfVertexCircle;
            x2 += radiusOfVertexCircle;
        } else {
            x2 -= radiusOfVertexCircle;
            x1 += radiusOfVertexCircle;
        }
        edgeLine.setStartX(x1);
        edgeLine.setEndX(x2);
        edgeLine.setStartY(y1);
        edgeLine.setEndY(y2);
        if (x1 > x2) {
            weightView.setX(x1 - Math.abs(x2 - x1) / 2);
        } else {
            weightView.setX(x2 - Math.abs(x2 - x1) / 2);
        }
        weightView.setY((y1 + y2) / 2 - 5);
        edgeLine.setStroke(Paint.valueOf("#806b8d"));
    }

    public void setReversedDirection(double weight){
        weightFromSecondToFirst = weight;
        directionFromSecondToFirst = true;
        weightView.setText(second.getName() + "->" + first.getName() + ":" + Double.toString(weightFromSecondToFirst) +
                "\n" + first.getName() + "->" + second.getName() + ":" + Double.toString(weightFromFirstToSecond));
    }

    public void setReversedDirection(Vertex start, Vertex finish, double weight){
        if (start == first && finish == second) {
            return;
        }
        weightFromSecondToFirst = weight;
        directionFromSecondToFirst = true;
        weightView.setText(second.getName() + "->" + first.getName() + ":" + Double.toString(weightFromSecondToFirst) +
                "\n" + first.getName() + "->" + second.getName() + ":" + Double.toString(weightFromFirstToSecond));
    }

    public void deleteOneWay(Vertex first, Vertex second){
        if (first == this.first & second == this.second) {
            directionFromFirstToSecond = false;
            weightFromFirstToSecond = 0;
            weightView.setText(second.getName() + "->" + first.getName() + ":" + Double.toString(weightFromSecondToFirst));
        } else if(first == this.second & second == this.first) {
            directionFromSecondToFirst = false;
            weightFromSecondToFirst = 0;
            weightView.setText(second.getName() + "->" + first.getName() + ":" + Double.toString(weightFromFirstToSecond));
        }
    }

    public boolean isFromFirstToSecond(Vertex first, Vertex second) {
        return this.first == first && this.second == second && directionFromFirstToSecond;
    }
    public boolean isFromSecondToFirst(Vertex first, Vertex second) {
        return this.second == first && this.first == second && directionFromSecondToFirst;
    }

    public boolean isTwoDirectional() {
        return directionFromFirstToSecond && directionFromSecondToFirst;
    }

    public boolean isOnlyOneWay() {
        return directionFromFirstToSecond ^ directionFromSecondToFirst;
    }

    public boolean isReverse(Vertex first, Vertex second) {
        return (cmpFirst(second) && cmpSecond(first));
    }

    public boolean cmpFirst(Vertex other) {
        return first == other;
    }

    public boolean cmpSecond(Vertex other) {
        return second == other;
    }

    public boolean cmpBoth(Vertex first, Vertex second) {
        return cmpFirst(first) && cmpSecond(second) || isReverse(first, second);
    }

    public Text getName(){
        return weightView;
    }

    public Line getView() { return edgeLine; }

    public void moveLine() {
        double x1, x2, y1, y2;
        x1 = first.getCoordinates().getX();
        x2 = second.getCoordinates().getX();
        y1 = first.getCoordinates().getY();
        y2 = second.getCoordinates().getY();
        if (x1 > x2) {
            x1 -= radiusOfVertexCircle;
            x2 += radiusOfVertexCircle;
        } else {
            x2 -= radiusOfVertexCircle;
            x1 += radiusOfVertexCircle;
        }
        edgeLine.setStartX(x1);
        edgeLine.setEndX(x2);
        edgeLine.setStartY(y1);
        edgeLine.setEndY(y2);
        if (x1 > x2) {
            weightView.setX(x1 - Math.abs(x2 - x1) / 2);
        } else {
            weightView.setX(x2 - Math.abs(x2 - x1) / 2);
        }
        weightView.setY((y1 + y2) / 2 - 5);
    }

    public void renewNames() {
        if (isTwoDirectional()) {
            weightView.setText(second.getName() + "->" + first.getName() + ":" + Double.toString(weightFromSecondToFirst) +
                    "\n" + first.getName() + "->" + second.getName() + ":" + Double.toString(weightFromFirstToSecond));
        } else if (directionFromFirstToSecond) {
            weightView.setText(first.getName() + "->" + second.getName() + ":" + Double.toString(weightFromFirstToSecond));
        } else {
            weightView.setText(second.getName() + "->" + first.getName() + ":" + Double.toString(weightFromSecondToFirst));
        }
    }
}

/*
5
a 100.234 2
b 189.2 0
c 200 300
d 100 300
e 500 500
a b 3
b c 1
c d 1
a d 5
d e 1
<<<<<<< HEAD


5
a 100 100
b 100 200
c 200 300
d 100 300
e 500 500
a b 3
b c 1
c d 1
d e 1
=======
>>>>>>> 276e60b332ea0c9789b6f472f07fef039ae020dc
 */