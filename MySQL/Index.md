## 索引

### 索引的分类

#### 按物理存储分类
1. 主键索引  
    B+Tree 的叶子节点存放的是实际数据，所有完整的用户记录都存放在主键索引的 B+Tree 的叶子节点里
2. 二级索引  
   二级索引的 B+Tree 的叶子节点存放的是主键值，而不是实际数据

#### 按字段类型分类