package search;

public class HeapNode<P> {
    double value;
    P node;

    public HeapNode(){
        this.value = -1;
        this.node = null;

    }

    public HeapNode(HeapNode<P> node){
        this.value = node.getValue();
        this.node = node.getNode();
    }

    public HeapNode(P node, double value){
        this.value = value;
        this.node = node;
    }

    public double getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public P getNode() {
        return node;
    }

    public void setNode(P node) {
        node = node;
    }
}
