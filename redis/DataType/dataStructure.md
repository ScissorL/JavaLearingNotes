## 数据结构

### SDS
![img_2.png](..%2Fimgs%2Fimg_2.png)

### 链表
![img_3.png](..%2Fimgs%2Fimg_3.png)

### 压缩列表
![img_4.png](..%2Fimgs%2Fimg_4.png)
![img_5.png](..%2Fimgs%2Fimg_5.png)
- 连锁更新问题
  - 压缩列表新增某个元素时，如果空间不够，压缩列表占用的内存空间就需要重新分配。而当新插入的元素较大时，可能会导致后续元素的prevlen变化，从而引起连锁更新问题
  - 因此压缩列表主要适用于元素数据量较少的情况，即使发生连锁更新也是可以接受的

### 哈希表
![img_6.png](..%2Fimgs%2Fimg_6.png)
```
typedef struct dictEntry {
//键值对中的键
void *key;

    //键值对中的值
    union {
        void *val;
        uint64_t u64;
        int64_t s64;
        double d;
    } v;
    //指向下一个哈希表节点，形成链表
    struct dictEntry *next;
} dictEntry;
```
- 键值对中的值是一个联合体，因为键值对中的值既可以是指向实际值的指针又可以是具体的值，这么做可以节省内存。
- 使用链式哈希解决冲突
  - 当链式过长时，需要rehash来扩展hash表大小
  - 采用的手段是将hash表1迁移到更大的表2（但是存在的问题是，如果迁移量太大会导致redis阻塞，无法正常处理请求
    - 用渐进式rehash解决：在rehash过程中，每次哈希元素进行新增、删除、查找或者更新时，redis除了执行相应的操作外，还会顺序将hash表1中索引位置迁移到hash表2

### 整数集合
整数集合是set的底层实现之一，当一个set对象只包含整数值元素且元素数量不大时，选用整数集合
```
typedef struct intset {
    //编码方式
    uint32_t encoding;
    //集合包含的元素数量
    uint32_t length;
    //保存元素的数组
    int8_t contents[];
} intset;
```
contents数组是存在扩容情况的
![img_7.png](..%2Fimgs%2Fimg_7.png)

### 跳表
由链表基础上改进而来的多有序链表
![img_8.png](..%2Fimgs%2Fimg_8.png)
跳表每个节点的层数是通过随机数决定的，这种方法可以较好地保证跳表结构的平衡
- 跳表在创建节点时候，会生成范围为[0-1]的一个随机数，如果这个随机数小于 0.25（相当于概率 25%），那么层数就增加 1 层，然后继续生成下一个随机数，直到随机数的结果大于 0.25 结束，最终确定该节点的层数。

### quicklist
![img_9.png](..%2Fimgs%2Fimg_9.png)
quicklist中的每个节点都是压缩列表，通过控制每个压缩列表的大小或者元素个数来规避潜在的连锁更新的风险问题，但是这并没有完全解决连锁更新的问题

### listpack
![img_10.png](..%2Fimgs%2Fimg_10.png)
listpack改用双向链表，保存前后节点的指针，每个节点中不再保存前面节点的长度，避免了连锁更新的问题