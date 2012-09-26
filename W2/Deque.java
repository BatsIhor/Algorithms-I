import java.util.Iterator;

public class Deque<Item> implements Iterable<Item>
{
    private int size;
    private Node<Item> first;
    private Node<Item> last;
    
    private class Node<Item>
    {
        public Node<Item> previous;
        public Node<Item> next;
        public Item item;
        
        public Node(Node<Item> previous, Node<Item> next, Item item)
        {
            this.previous = previous;
            this.next = next;
            this.item = item;
        }
    }
    
    private class DequeIterator implements Iterator<Item>
    {
        private Node<Item> current = first;
        
        public boolean hasNext()
        {
            return current != null;
        }
        
        public void remove()
        {
            throw new java.lang.UnsupportedOperationException();
        }
        
        public Item next()
        {
            if (!hasNext())
                throw new java.util.NoSuchElementException();
                
            Item item = current.item;
            
            current = current.next;
            
            return item;
        }
    }
    
    public Deque()
    {        
        size  = 0;
        first = null;
        last  = null;
    }
    
    public boolean isEmpty()
    {
        return size == 0;
    }
    
    public int size()
    {
        return size;
    }
    
    public void addFirst(Item item)
    {
        if (item == null)
            throw new java.lang.NullPointerException();
        
        Node<Item> node = new Node<Item>(null, first, item);
        
        if (!isEmpty())
            first.previous = node;
            
        first = node;
        
        if (last == null) last = first;
        
        size++;
    }
    
    public void addLast(Item item)
    {
        if (item == null)
            throw new java.lang.NullPointerException();
            
        Node<Item> node = new Node<Item>(last, null, item);
        
        if (last != null)
            last.next = node;
            
        last = node;
        
        if (isEmpty()) first = last;
        
        size++;
    }
    
    public Item removeFirst()
    {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        
        Item item = first.item;
        
        if (first.next != null) first.next.previous = null;
        if (last == first) last = null;
        first = first.next;
        
        size--;
        
        return item;
    }
    
    public Item removeLast()
    {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        
        Item item = last.item;
        
        if (last.previous != null) last.previous.next = null;
        if (last == first) first = last = null;
        if (last != null) last = last.previous;
        
        size--;
        
        return item;
    }
    
    public Iterator<Item> iterator()
    {
        return new DequeIterator();
    }
}