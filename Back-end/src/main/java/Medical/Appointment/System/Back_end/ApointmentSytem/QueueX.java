package Medical.Appointment.System.Back_end.ApointmentSytem;

public class QueueX {
    private int maxSize;
    private String[] queArray;
    private int front;
    private int rear;

    public QueueX(int size) {
        maxSize = size;
        queArray = new String[maxSize];
        front = 0;
        rear = -1;
    }

    public boolean isFull() {
        return rear == maxSize - 1;
    }

    public boolean isEmpty() {
        return front > rear;
    }

    public void insert(String item) {
        if (isFull()) {
            throw new RuntimeException("Queue is full");
        }
        queArray[++rear] = item;
    }

    public String remove() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        return queArray[front++];
    }

    public String[] toArray() {
        if (isEmpty()) return new String[0];
        String[] result = new String[rear - front + 1];
        for (int i = front; i <= rear; i++) {
            result[i - front] = queArray[i];
        }
        return result;
    }
}
