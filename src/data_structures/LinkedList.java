package data_structures;

public class LinkedList<T> {

    private Element<T> head;
    int size;

    public LinkedList(T firstElement){
        head = new Element(firstElement);
        size = 0;
    }

    public LinkedList(){
        head = null;
        size = 0;
    }

    public void addLast(T e){
        Element<T> element = new Element(e);
        Element<T> tail = head;
        while(tail.getNext() != null){
            tail = tail.getNext();
        }
        tail.setNext(element);
        tail = element;
        if(head == null){
            head = element;
        }
        size++;
    }

    public void addFirst(T e){
        Element<T> element = new Element(e, head);
        head = element;
        size++;
    }

    public void add(T e, int index){
        Element<T> element = new Element(e);
        Element<T> before = getElementByIndex(index - 1);
        if(before == head){
            head = element;
            head.setNext(before);
        }
        else {
            element.setNext(before.getNext());
            before.setNext(element);
        }
        size++;
    }

    public boolean addAfter(T e, T before){
        Element<T> cursor = head;
        while (cursor != null){
            if(cursor.getEl().equals(before)){
                Element element = new Element(e, cursor.getNext());
                cursor.setNext(element);
                size++;
                return true;
            }
            cursor = cursor.getNext();
        }
        return false;
    }

    public T get(int index){
        Element<T> cursor = head;
        for(int i = 0; i < index; i++) {
            cursor = cursor.getNext();
        }
        return cursor.getEl();
    }

    public boolean contains(T e){
        return indexOf(e) != -1;
    }

    public boolean remove(T e){
        Element<T> cursor = head;
        if(head.getEl().equals(e)){
            head = head.getNext();
            size--;
            return true;
        }
        while (cursor.getNext() != null){
            if(cursor.getNext().getEl().equals(e)){
                cursor.setNext(cursor.getNext().getNext());
                size--;
                return true;
            }
            cursor = cursor.getNext();
        }
        return false;
    }

    public void removeByIndex(int index){
        Element<T> cursor = head;
        for(int i = 0; i < index - 1; i++){
            cursor = cursor.getNext();
        }
        if(index == 0){
            head = cursor.getNext();
        }
        else {
            cursor.setNext(cursor.getNext().getNext());
        }
        size--;
    }

    public int indexOf(T e){
        Element<T> cursor = head;
        int index = 0;
        while (cursor != null){
            if(cursor.getEl().equals(e)){
                return index;
            }
            index++;
            cursor = cursor.getNext();
        }
        return -1;
    }

    public LinkedList<T> merge(LinkedList<? extends T> anotherList){
        LinkedList<T> linkedList = new LinkedList<>();
        linkedList.head = head;
        Element e = getElementByIndex(size - 1);
        e.setNext(anotherList.head);
        return linkedList;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        String r = "";
        Element<T> cursor = head;
        while (cursor != null){
            r += (cursor.getEl().toString() + ", ");
            cursor = cursor.getNext();
        }
        return r;
    }

    private Element getHead(){
        return head;
    }

    private Element getElementByIndex(int index){
        Element cursor = head;
        for(int i = 0; i < index; i++){
            cursor = cursor.getNext();
        }
        return cursor;
    }

    private class Element<T>{
        private T el;
        private Element next;

        public Element(T el, Element next){
            this.el = el;
            this.next = next;
        }

        public Element(T el){
            this(el, null);
        }

        public T getEl() {
            return el;
        }

        public void setEl(T el) {
            this.el = el;
        }

        public Element getNext() {
            return next;
        }

        public void setNext(Element next) {
            this.next = next;
        }
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addLast(4);
        list.addLast(5);
        list.addLast(6);
        list.add(123, 3);
        list.addAfter(666, 5);
        list.add(0, 0);
        System.out.println(list);
        list.remove(666);
        list.remove(0);
        list.removeByIndex(list.getSize() - 1);
        System.out.println(list);
        System.out.println(list.indexOf(123));
        System.out.println(list.indexOf(666));
        System.out.println(list.get(5));
    }
}
