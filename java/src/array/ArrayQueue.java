package array;

public class ArrayQueue<E> {
    private E[] array;

    private int read;

    private int write;

    public ArrayQueue() {
        this.array = (E[]) new Object[5];
    }

    public String toString() {
        int i = read;

        StringBuffer sb = new StringBuffer();
        sb.append("[");
        while(array[i] != null) {
            sb.append(array[i]);
            sb.append(",");

            i = increment(i);
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");

        return sb.toString();
    }

    public void enQueue(E input) {
        if (canEnQueue()) {
            array[write] = input;
            write = increment(write);
        } else {
            throw new IllegalStateException();
        }
    }

    private boolean canEnQueue() {
        if ((read == 0 ? array.length - 1 : read - 1) == write)
            return false;
        else
            return true;
    }

    public E deQueue() {
        if (empty())
            return null;

        E del = array[read];
        array[read] = null;
        read = increment(read);
        return del;
    }

    private int increment(int i) {
        return (i == array.length - 1) ? 0 : i + 1;
    }

    public boolean empty() {
        return read == write;
    }
}
