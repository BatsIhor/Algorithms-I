import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item>  implements Iterable<Item>
{
    private int n = 0;
    private int used = 0;
    private Item[] items = (Item[]) new Object[1];
    
    private class RandomizedQueueIterator implements Iterator<Item>
    {
        private int n = 0;
        private Item[] data = (Item[]) new Object[used];
        
        public RandomizedQueueIterator()
        {
            int j = 0;
            
            for (int i = 0; i < items.length; i++) {
                
                if (items[i] == null)
                    continue;
                
                data[j] = items[i];
                
                int r = StdRandom.uniform(j + 1);
                
                Item tmp = data[j];
                data[j] = data[r];
                data[r] = tmp;
                
                j++;
            }
        }
        
        public boolean hasNext()
        {
            return n < data.length;
        }
        
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
        
        public Item next()
        {
            if (!hasNext())
                throw new NoSuchElementException();
                
            return data[n++];
        }
    }
    
    public RandomizedQueue()
    {
    }
    
    public boolean isEmpty()
    {
        return used == 0;
    }
    
    public int size()
    {
        return used;
    }
    
    private void resize(int size)
    {
        n = 0;
        Item[] newItems = (Item[]) new Object[size];
        
        for (int i = 0; i < items.length; i++) {
            
            if (items[i] == null)
                continue;
            
            newItems[n++] = items[i];
        }
        
        items = newItems;
    }
    
    public void enqueue(Item item)
    {
        if (item == null)
            throw new NullPointerException();
            
        if (n == items.length)
            resize(n * 2);
            
        used++;
        items[n++] = item;
    }
    
    public Item dequeue()
    {
        int r;
        
        if (isEmpty())
            throw new NoSuchElementException();
            
        do {
            r = StdRandom.uniform(n);
        } while (items[r] == null);
        
        Item item = items[r];
        
        used--;
        items[r] = null;
        
        if (used > 0 && used == items.length/4)
            resize(items.length / 2);
            
        return item;
    }
    
    public Item sample()
    {
        int r;
        
        if (isEmpty())
            throw new NoSuchElementException();
            
        do {
            r = StdRandom.uniform(n + 1);
        } while (items[r] == null);
        
        return items[r];
    }
    
    public Iterator<Item> iterator()
    {
        return new RandomizedQueueIterator();
    }
}