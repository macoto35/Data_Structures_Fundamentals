package array;

public class ArrayStack<E> {

    private E[] array;

    private int numElement;

    public ArrayStack() {
        array = (E[]) new Object[5];
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append("[");
        for (int i = 0 ; i < numElement; i++) {
            sb.append(array[i]);
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");

        return sb.toString();
    }

    public void push(E input) {
        array[numElement++] = input;
    }

    public E top() {
        if (numElement == 0)
            return null;
        return array[numElement - 1];
    }

    public E pop() {
        if (numElement == 0)
            return null;

        E returnValue = array[--numElement];
        array[numElement] = null;

        return returnValue;
    }

    public boolean empty() {
        return numElement == 0;
    }
}
