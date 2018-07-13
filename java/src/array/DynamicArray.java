package array;

public class DynamicArray<E> {
    private E[] arr;
    
    private int size;

    private int capacity;

    public String toString() {
	if (this.arr == null)
	    return "";
	
	StringBuffer sb = new StringBuffer();
	for(E data : arr) {
	    if (data != null) {
	        sb.append(data);
                sb.append(",");
	    }
	}

	return sb.toString();
    }


    public int size() {
        return this.size;
    }

    public int capacity() {
	return this.capacity;
    }

    public void pushBack(E data) {
	if (this.size == this.capacity) {
	    this.capacity = this.capacity == 0 ? 1 : this.capacity * 2;
	    E[] newArr = (E[]) new Object[this.capacity];

	    for (int i = 0 ; i < this.size ; i++) {
		newArr[i] = this.arr[i];
	    }
	    this.arr = newArr;
	}
	this.arr[this.size++] = data;
    }

    public E get(int idx) {
	if (idx < 0 || idx >= this.size)
	    throw new IndexOutOfBoundsException();
	return this.arr[idx];
    }

    public void set(int idx, E data) {
	if (idx < 0 || idx >= this.size)
	    throw new IndexOutOfBoundsException();
	this.arr[idx] = data;
    }

    public E remove(int idx) {
	if (idx < 0 || idx >= this.size)
	    return null;
        
	E result = this.arr[idx];

	for (int i = idx ; i < this.size - 1 ; i++)
	    this.arr[i] = this.arr[i + 1];
        this.size--;

	return result;
    }
}
