package collections;

import java.util.*;

public class MyMap<K, V> extends AbstractMap<K, V> {

    private ArrayList<Pair> arrayList;

    public MyMap(){
        arrayList = new ArrayList<>();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        HashSet set = new HashSet();
        for(Pair p : arrayList){
            set.add(p);
        }
        return set;
    }

    @Override
    public int size() {
        return arrayList.size();
    }

    @Override
    public boolean isEmpty() {
        return arrayList.isEmpty();
    }

    @Override
    public boolean containsValue(Object value) {
        for(Pair p : arrayList){
            if(p.getValue().equals(value)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        for(Pair p : arrayList){
            if(p.getKey().equals(key)){
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        for(Pair p : arrayList){
            if(p.getKey().equals(key)){
                return p.getValue();
            }
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        for(Pair p : arrayList){
            if(p.getKey().equals(key)){
                V r = p.getValue();
                p.setValue(value);
                return r;
            }
        }
        arrayList.add(new Pair(key, value));
        return null;
    }

    @Override
    public V remove(Object key) {
        for(Pair p : arrayList){
            if(p.getKey().equals(key)){
                V r = p.getValue();
                arrayList.remove(p);
                return r;
            }
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for(K k : m.keySet()){
            put(k, m.get(k));
        }
    }

    @Override
    public void clear() {
        arrayList.clear();
    }

    @Override
    public Set<K> keySet() {
        HashSet<K> set = new HashSet<>();
        for(Pair p : arrayList){
            set.add(p.getKey());
        }
        return set;
    }

    @Override
    public Collection<V> values() {
        ArrayList<V> list = new ArrayList<>();
        for(Pair p : arrayList){
            list.add(p.getValue());
        }
        return list;
    }

    private class Pair implements Entry{
        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public Object setValue(Object value) {
            try{
                V r = this.value;
               this.value = (V)value;
               return r;
            }
            catch (ClassCastException e){
                System.err.println(e.getMessage());
                return null;
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MyMap<?, ?> myMap = (MyMap<?, ?>) o;
        return Objects.equals(arrayList, myMap.arrayList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), arrayList);
    }
}
