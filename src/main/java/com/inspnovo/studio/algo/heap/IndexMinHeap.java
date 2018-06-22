package com.inspnovo.studio.algo.heap;

import static com.inspnovo.studio.algo.util.SortUtil.*;

/**
 * 基准的最小索引堆实现
 */
public class IndexMinHeap {
    // 数组，存放比较的元素
    private Comparable[] data;
    // 数组，存放元素对应的索引，堆中比较的是索引
    private int[] index;
    int capacity = 16;
    int count = 0;

    public IndexMinHeap(){

        data = new Comparable[this.capacity];
        index = new int[this.capacity];
        init();
    }

    public IndexMinHeap(int capacity){
        data = new Comparable[capacity];
        index = new int[capacity];
        this.capacity = capacity;
        init();
    }

    private void init(){
        for(int i=0; i<capacity; i++){
            index[i]=-1;
        }
    }

    /**
     * 返回item的索引值
     * @param item
     * @return
     */
    public int offer(Comparable item){
        if(count + 1 > capacity){
            throw new IllegalArgumentException("Exceed capacity");
        }
        //
        int itemIdx = count;
        this.data[itemIdx] = item;
        this.index[itemIdx] = itemIdx;
        count++;
        // 定位父节点
        //int parentIdx = (itemIdx-1)/2;
        shiftUp(itemIdx);
        return itemIdx;
    }

    /**
     * 在数据索引位置处，插入
     * @param itemIndex
     * @param item
     * @return
     */
    public int offer(int itemIndex, Comparable item){
        if(count + 1 > capacity){
            throw new IllegalArgumentException("Exceed capacity");
        }
        if(0 > itemIndex || itemIndex >= capacity || null != data[itemIndex]){
            throw new IllegalArgumentException("Invalid item index");
        }
        //
        int itemIdx = count; // index in the index array
        this.data[itemIndex] = item;
        this.index[itemIdx] = itemIndex;
        count++;
        // 定位父节点
        //int parentIdx = (itemIdx-1)/2;
        shiftUp(itemIdx);
        return itemIdx;
    }

    public Comparable pull(){
        if(0 == count){
            return null;
        }
        Comparable ret = this.data[index[0]];
        // this.data[index[0]]=null; // data数据
        int lastIdx  = count -1;
        //exch(this.index, 0, lastIdx);
        index[0] = index[lastIdx];
        index[lastIdx]=-1;
        count--;
        shiftDown(0);
        return ret;
    }

    public int pullIndex(){
        if(0 == count){
            return -1;
        }
        int ret = this.index[0];
        // this.data[index[0]]=null; // data数据
        int lastIdx  = count -1;
        //exch(this.index, 0, lastIdx);
        index[0] = index[lastIdx];
        index[lastIdx]=-1;
        count--;
        shiftDown(0);
        return ret;
    }

    public Comparable get(int itemIndex){
        if(0 > itemIndex || itemIndex >= data.length){
            throw new IllegalArgumentException("invalid index");
        }
        return this.data[itemIndex];
    }

    private void shiftDown(int itemIndex) {

        //while(itemIndex < (count - 1) / 2){
        while(itemIndex * 2 + 1 < count){ // 起码有左子树,左子树都没有的话，当前item是叶子节点，无需调整
            int leftIdx  = itemIndex * 2 + 1;
            int rightIdx = itemIndex * 2 + 2;
            // 默认左子树
            int min = leftIdx;
            // 存在右节点，且比左大
            if(rightIdx < count && less(data[index[rightIdx]], data[index[min]])){
                min = rightIdx;
            }
            // 比较和当前节点大小
            if(less(data[index[min]], data[index[itemIndex]])){
                exch(index, itemIndex, min);
                itemIndex = min;
            }else{
                break;
            }


        }
    }

    public boolean hasNext(){
        return count > 0;
    }

    private void shiftUp(int itemIdx) {
        while(itemIdx>0){

            int parentIdx = (itemIdx-1)/2;
            if(less(data[index[itemIdx]], data[index[parentIdx]])){
                exch(index, parentIdx, itemIdx);
            }
            itemIdx = parentIdx;
        }
    }

    public void change(int itemIndex, Comparable newItem){
        if(0 > itemIndex || itemIndex >= data.length){
            throw new IllegalArgumentException("invalid index");
        }
        int idxInHeap = locate(itemIndex);
        if(-1 == idxInHeap){
            throw new IllegalStateException(String.format("the data with index %d is not in the heap.", itemIndex));
        }
        this.data[itemIndex] = newItem;
        shiftUp(idxInHeap);
        shiftDown(idxInHeap);
    }

    /**
     * 确定数据在堆中的位置
     * @param itemIndex
     * @return
     */
    private int locate(int itemIndex) {
        for(int i=0; i<count; i++){
            if(itemIndex == index[i]){
                return i;
            }
        }
        return -1;
    }

    public boolean isEmpty(){
        return 0 == count;
    }

}
