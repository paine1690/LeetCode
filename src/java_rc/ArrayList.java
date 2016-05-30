package java_rc;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.RandomAccess;

public class ArrayList<E> {	

    private static final long serialVersionUID = 8683452581122892189L;//序列版本号

    private static final int DEFAULT_CAPACITY = 10;//默认的初始容量
   
    private static final Object[] EMPTY_ELEMENTDATA = {}; //公有的空数组
    
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};//也是空数组，和上面区别在于在第一次add元素的时候会判断

    
    /*
     * ArrayList的两个私有属性
     */
    transient Object[] elementData; // 基于该数组实现，用于保存数据
    private int size;//size表示容器内元素的个数
    
    
	/*
	 * 三个构造函数
	 */
    
    public ArrayList(int initialCapacity) {//指定容量大小
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: "+
                                               initialCapacity);
        }
    }

    public ArrayList() {//无参构造函数，
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    public ArrayList(Collection<? extends E> c) {//创建一个包含collection的ArrayList
        elementData = c.toArray();
        if ((size = elementData.length) != 0) {
            // c.toArray might (incorrectly) not return Object[] (see 6260652)
            if (elementData.getClass() != Object[].class)
                elementData = Arrays.copyOf(elementData, size, Object[].class);
        } else {
            // replace with empty array.
            this.elementData = EMPTY_ELEMENTDATA;
        }
    }	
    
    
    /*
     * 确保数组的大小
     * 根据现有数组的大小计算出minCapacity
     * 二者最终都会调用ensureExplicitCapacity(minCapacity);
     */
    public void ensureCapacity(int minCapacity) {
        int minExpand = (elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA)
            // any size if not default element table
            ? 0
            // larger than default for default empty table. It's already
            // supposed to be at default size.
            : DEFAULT_CAPACITY;

        if (minCapacity > minExpand) {
            ensureExplicitCapacity(minCapacity);
        }
    }

    private void ensureCapacityInternal(int minCapacity) {
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        }

        ensureExplicitCapacity(minCapacity);
    }

    private void ensureExplicitCapacity(int minCapacity) {//如果现有elementData的长度不够满足需求的大小，那么就扩大现有的数组
        modCount++;

        // overflow-conscious code
        if (minCapacity - elementData.length > 0)
            grow(minCapacity);
    }
    
    
    

    /*
     * 增加数组的长度以确保minCapacity个存储空间
     */
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;// 数组的最大长度
    
    private void grow(int minCapacity) {//计算出新的数组长度，并将原有数组复制过去
        // overflow-conscious code
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        // minCapacity is usually close to size, so this is a win:
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ?
            Integer.MAX_VALUE :
            MAX_ARRAY_SIZE;
    }
    
    
    
    
    
    //在指定位置插入元素或集合中元素时，首先调用该方法判断指定的位置是否合法
    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }
    
    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }
    
    /*
     * add与addAll
     */
    public boolean add(E e) {//在容器末尾加入一个指定新的元素
        ensureCapacityInternal(size + 1);  // Increments modCount!!
        elementData[size++] = e;
        return true;
    }
    
    public void add(int index, E element) {//在指定位置插入一个指定的元素
        rangeCheckForAdd(index);

        ensureCapacityInternal(size + 1);  // Increments modCount!!
        System.arraycopy(elementData, index, elementData, index + 1,
                         size - index);
        elementData[index] = element;
        size++;
    }
    
    public boolean addAll(Collection<? extends E> c) {//在容器末尾加入一个指定集合中的所有的元素
        Object[] a = c.toArray();
        int numNew = a.length;
        ensureCapacityInternal(size + numNew);  // Increments modCount
        System.arraycopy(a, 0, elementData, size, numNew);
        size += numNew;
        return numNew != 0;
    }

    public boolean addAll(int index, Collection<? extends E> c) {//在指定位置插入一个指定集合的所有的元素
        rangeCheckForAdd(index);

        Object[] a = c.toArray();
        int numNew = a.length;
        ensureCapacityInternal(size + numNew);  // Increments modCount

        int numMoved = size - index;
        if (numMoved > 0)
            System.arraycopy(elementData, index, elementData, index + numNew,
                             numMoved);

        System.arraycopy(a, 0, elementData, index, numNew);
        size += numNew;
        return numNew != 0;
    }
    
    
    
    //返回容器中持有元素的个数
    public int size() {
        return size;
    }

    //返回容器是否为空，即没有元素
    public boolean isEmpty() {
        return size == 0;
    }

    /*
     * 查找给定元素
     */
    public boolean contains(Object o) {//返回容器中是否包含某个元素
        return indexOf(o) >= 0;
    }    
    
    public int indexOf(Object o) {//返回某个元素第一次出现的位置，如果没有则返回-1
        if (o == null) {//注意null和非null元素相等是不一样的
            for (int i = 0; i < size; i++)
                if (elementData[i]==null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(elementData[i]))
                    return i;
        }
        return -1;
    }

    
    public int lastIndexOf(Object o) {//返回某个元素从后向前第一次出现的位置
        if (o == null) {
            for (int i = size-1; i >= 0; i--)
                if (elementData[i]==null)
                    return i;
        } else {
            for (int i = size-1; i >= 0; i--)
                if (o.equals(elementData[i]))
                    return i;
        }
        return -1;
    }
   
    private void rangeCheck(int index) {//在set、get和remove方法之前调用该方法检查给定位置是否合法
        if (index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }
    
    @SuppressWarnings("unchecked")
    E elementData(int index) {
        return (E) elementData[index];
    }

    public E get(int index) {//返回指定位置的元素
        rangeCheck(index);

        return elementData(index);
    }
    
    

    public E set(int index, E element) {//将指定位置的元素改为输入的元素
        rangeCheck(index);

        E oldValue = elementData(index);
        elementData[index] = element;
        return oldValue;
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    /*
     * remove方法，包括按位置和按元素删除
     */
    

    public E remove(int index) {//删除指定位置的元素，并作为返回值返回
        rangeCheck(index);

        modCount++;
        E oldValue = elementData(index);

        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index+1, elementData, index,
                             numMoved);
        elementData[--size] = null; // clear to let GC do its work

        return oldValue;
    }

    private void fastRemove(int index) {//快速删除指定位置的元素，与上面的相比跳过了检查位置合法性并且没有返回值
        modCount++;
        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index+1, elementData, index,
                             numMoved);
        elementData[--size] = null; // clear to let GC do its work
    }

    public boolean remove(Object o) {//首先查找指定元素是否存在，如果存在将其删除并返回true，如果不存在返回false
        if (o == null) {
            for (int index = 0; index < size; index++)
                if (elementData[index] == null) {
                    fastRemove(index);
                    return true;
                }
        } else {
            for (int index = 0; index < size; index++)
                if (o.equals(elementData[index])) {
                    fastRemove(index);
                    return true;
                }
        }
        return false;
    }

    public void clear() {//删除容器内所有的元素，将其变为空
        modCount++;

        // clear to let GC do its work
        for (int i = 0; i < size; i++)
            elementData[i] = null;

        size = 0;
    }

    protected void removeRange(int fromIndex, int toIndex) {//删除指定区间内的所有元素
        modCount++;
        int numMoved = size - toIndex;
        System.arraycopy(elementData, toIndex, elementData, fromIndex,
                         numMoved);

        // clear to let GC do its work
        int newSize = size - (toIndex-fromIndex);
        for (int i = newSize; i < size; i++) {
            elementData[i] = null;
        }
        size = newSize;
    }
    
    
    
    
    
    
    
}
