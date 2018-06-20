package array;

public class Array<E> {
    private E[] array;
    private int size = 0;

    public Array() {
        array = (E[]) new Object[10];
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append("[");
        for (int i = 0 ; i < size ; i++) {
            sb.append(array[i]);
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");

        return sb.toString();
    }

    public boolean addLast(E element) {
        array[size++] = element;

        return true;
    }

    public boolean add(int idx, E element) {
        for (int i = size - 1; i >= idx; i--)
            array[i + 1] = array[i];
        array[idx] = element;
        size++;

        return true;
    }

    public boolean addFirst(E element) {
        return add(0, element);
    }

    public E remove(int idx) {
        E result = array[idx];

        if (result != null) {
            size--;

            for (int i = idx; i <= size; i++)
                array[i] = array[i + 1];

            array[size] = null;
        }

        return result;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        if (size > 0)
            return remove(size - 1);
        else
            return null;
    }

    public E get(int idx) {
        return array[idx];
    }

    public int size() {
        return size;
    }

    public int indexOf(E o) {
        for (int i = 0 ; i < size ; i++)
            if (array[i].equals(o))
                return i;

        return -1;
    }

    public ListIterator listIterator() {
        return new ListIterator();
    }

    class ListIterator {
        private int nextIdx = 0;

        public boolean hasNext() {
            return nextIdx < size;
        }

        public E next() {
            return array[nextIdx++];
        }

        public boolean hasPrevious() {
            return nextIdx > 0;
        }

        public E previous() {
            return array[--nextIdx];
        }

        public void add(E element) {
            Array.this.add(nextIdx++, element);
        }

        public void remove() {
            nextIdx--;
            Array.this.remove(nextIdx);
        }
    }
}
