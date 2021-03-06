import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Holds a list of String objects always sorted
 * in ascending order.
 *
 * @author Zachary Latta
 */
public class OrderedStringListType implements Iterable<String>
{
    private final int DEFAULT_CAPACITY = 10;
    private final int RESIZE_FACTOR = 2;
    private String[] list;
    private int elements;

    /**
     * Creates an empty list of the default capacity.
     */
    public OrderedStringListType()
    {
        list = new String[DEFAULT_CAPACITY];
        elements = 0;
    }

    /**
     * This constructor creates an empty list of the specified capacity.
     *
     * @param capacity The initial capacity.
     * @throws IllegalArgumentException if the specified capacity is less than one.
     */
    public OrderedStringListType(int capacity)
    {
        if(capacity < 1)
        {
            throw new IllegalArgumentException();
        }

        list = new String[capacity];
        elements = 0;
    }

    /**
     * Adds a string to the list.
     *
     * @param str The string to add.
     */
    public void add(String str)
    {
        // Check if there's anything in the list before checking against it.
        if(elements == 0)
        {
            addAtEnd(str);
        }
        else
        {
            boolean added = false;

            for(int i = 0; i < elements; i++)
            {
                if(str.compareTo(list[i]) < 0)
                {
                    add(i, str);

                    added = true;
                    break;
                }
            }

            if(!added)
            {
                addAtEnd(str);
            }
        }
    }

    /**
     * Adds a string at a specified index.
     *
     * @param index The added string's position.
     * @param str   The string to add.
     * @throws IndexOutOfBoundsException When index is out of bounds.
     */
    private void add(int index, String str)
    {
        if(index >= elements || index < 0)
        {
            throw new IndexOutOfBoundsException();
        }
        else if(elements == list.length)
        {
            resize();
        }

        System.arraycopy(list, index, list, index + 1, elements - index);
        list[index] = str;
        elements++;
    }

    /**
     * Adds string on to end of list.
     */
    private void addAtEnd(String str)
    {
        if (elements == list.length)
        {
            resize();
        }

        list[elements] = str;
        elements++;
    }

    /**
     * Clears the list.
     */
    public void clear()
    {
        for(int index = 0; index < list.length; index++)
        {
            list[index] = null;
        }

        elements = 0;
    }

    /**
     * Searches the list for a specified string.
     *
     * @param str The string to search for.
     * @return true if the list contains the string { } false otherwise
     */
    public boolean contains(String str)
    {
        int index = 0;
        boolean found = false;

        while(!found && index < elements)
        {
            if(list[index].equals(str))
            {
                found = true;
            }

            index++;
        }

        return found;
    }

    /**
     * Gets an element at a specified position.
     *
     * @param index The specified index.
     * @return The element at the index.
     * @throws IndexOutOfBoundsException When index is out of bounds.
     */
    public String get(int index)
    {
        if(index >= elements || index < 0)
        {
            throw new IndexOutOfBoundsException();
        }

        return list[index];
    }

    /**
     * Gets the index of the first occurrence of the specified string.
     *
     * @param str The string to search for.
     * @return The index of the first occurrence of str if it exists { } -1
     *         if it does not.
     */
    int indexOf(String str)
    {
        int index = 0;
        boolean found = false;

        while(!found && index < elements)
        {
            if(list[index].equals(str))
            {
                found = true;
            }
            else
            {
                index++;
            }
        }

        if(!found)
        {
            index = -1;
        }

        return index;
    }

    /**
     * Determines whether the list is empty.
     *
     * @return true if the list is empty { } false otherwise
     */
    boolean isEmpty()
    {
        return (elements == 0);
    }

    /**
     * Removes a specified string from the list.
     *
     * @param str The string to remove.
     * @return true if the string was found { } false otherwise
     */
    boolean remove(String str)
    {
        int index = 0;
        boolean found = false;

        while(!found && index < elements)
        {
            if(list[index].equals(str))
            {
                list[index] = null;
                found = true;
            }

            index++;
        }

        if(found)
        {
            while(index < elements)
            {
                list[index - 1] = list[index];
                index++;
            }

            elements--;
        }

        return found;
    }

    /**
     * Removes a string at the specified index.
     *
     * @param index The index of the String to remove.
     * @return The string that was removed.
     * @throws IndexOutOfBoundsException When index is out of bounds.
     */
    public String remove(int index)
    {
        if(index >= elements || index < 0)
        {
            throw new IndexOutOfBoundsException();
        }

        String temp = list[index];
        list[index] = null;
        index++;

        while(index < elements)
        {
            list[index - 1] = list[index];
            index++;
        }

        elements--;

        return temp;
    }

    /**
     * Gets number of elements in the list.
     *
     * @return Number of elements in the list.
     */
    public int size()
    {
        return elements;
    }

    /**
     * Resizes the list to twice its current length.
     */
    private void resize()
    {
        int newLength = RESIZE_FACTOR * list.length;

        String[] tempList = new String[newLength];

        System.arraycopy(list, 0, tempList, 0, elements);

        list = tempList;
    }

    @Override
    public Iterator iterator()
    {
        Iterator<String> iterator = new Iterator<String>()
        {
            int position = 0;

            @Override
            public boolean hasNext()
            {
                return list[position] != null;
            }

            @Override
            public String next()
            {
                return list[position++];
            }

            @Override
            public void remove()
            {
            }
        };

        return iterator;
    }
}
