package com.inspnovo.studio.algo.heap;

import com.inspnovo.studio.algo.util.SortTestUtil;

import static com.inspnovo.studio.algo.util.SortUtil.*;

/**
 * 基准的最小堆实现
 */
public class MinHeap/*<T extends Comparable>*/ {
    private Comparable[] data;
    int capacity = 16;
    int count = 0;

    public MinHeap(){
        data = new Comparable[this.capacity];
    }

    public MinHeap(int capacity){
        data = new Comparable[capacity];
        this.capacity = capacity;
    }

    public boolean isFull(){
        return count == capacity;
    }


    public void offer(Comparable item){
        if(count + 1 > capacity){
            throw new IllegalStateException("Exceed capacity");
        }
        //
        int itemIdx = count;
        this.data[itemIdx] = item;
        count++;
        // 定位父节点
        //int parentIdx = (itemIdx-1)/2;
        shiftUp(itemIdx);
    }

    public Comparable get(){
        if(0 == count){
            return null;
        }
        Comparable ret = this.data[0];
        return ret;
    }

    public Comparable pull(){
        if(0 == count){
            return null;
        }
        Comparable ret = this.data[0];
        int lastIdx  = count -1;
        exch(this.data, 0, lastIdx);
        count--;
        shiftDown(0);
        return ret;
    }

    private void shiftDown(int itemIndex) {

        //while(itemIndex < (count - 1) / 2){
        while(itemIndex * 2 + 1 < count){ // 起码有左子树,左子树都没有的话，当前item是叶子节点，无需调整
            int leftIdx  = itemIndex * 2 + 1;
            int rightIdx = itemIndex * 2 + 2;
            // 默认左子树
            int min = leftIdx;
            // 存在右节点，且比左大
            if(rightIdx < count && less(data[rightIdx], data[min])){
                min = rightIdx;
            }
            // 比较和当前节点大小
            if(less(data[min], data[itemIndex])){
                exch(data, itemIndex, min);
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
            if(less(data[itemIdx], data[parentIdx])){
                exch(data, parentIdx, itemIdx);
            }
            itemIdx = parentIdx;
        }
    }

    public boolean isEmpty(){
        return 0 == count;
    }

    public static void main(String[] args) {
        MinHeap h = new MinHeap();
        h.offer(1);
        h.offer(2);
        h.offer(3);
        h.offer(4);
        h.offer(5);
        h.offer(6);
        show(h.data);
        Comparable item = h.pull();
        System.out.println(item);
        show(h.data);

        while(h.hasNext()){
            item = h.pull();
            System.out.println(item);
            show(h.data);
        }

        h = new MinHeap(100);// 要构建一个最小堆
        Comparable[] vals = SortTestUtil.randomIntegers(1000000, 1000000);
        for(int i=0; i< vals.length; i++){
            if(!h.isFull()){
                h.offer(vals[i]);
                continue;
            }else{
                Comparable min = h.get();
                if(less(min, vals[i])){
                    h.pull();
                    h.offer(vals[i]);
                }
            }

        }
        show(h.data);
    }
}
