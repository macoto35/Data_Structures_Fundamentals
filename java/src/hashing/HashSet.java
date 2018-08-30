package hashing;

import java.util.LinkedList;

public class HashSet<T> {
    LinkedList<T>[] chains = new LinkedList[1000];

    public boolean find(T obj) {
        LinkedList<T> chain = this.chains[obj.hashCode()];

        if (chain == null)
            return false;

        for (T key : chain)
            if (key.equals(obj))
                return true;

        return false;
    }

    public void add(T obj) {
        if (this.find(obj))
            return;

        LinkedList<T> chain = this.chains[obj.hashCode()];
        if (chain == null) {
            chain = new LinkedList<T>();
            this.chains[obj.hashCode()] = chain;
        }
        chain.add(obj);
    }

    public T remove(T obj) {
        if (!this.find(obj))
            return null;

        LinkedList<T> chain = this.chains[obj.hashCode()];
        chain.remove(obj);

        return obj;
    }
}
