public class MyQueue {

    Component first, last;

    public void enqueue(Component component) {
        if(first == null){
            first = component;
            last = first;
        } else {
            last.next = component;
            last = component;
        }
    }

    public Component dequeue() {
        if(first == null){
            return null;
        }

        else {
            Component temp = new Component(first.getName());
            first = first.next;
            return temp;
        }
    }
}
