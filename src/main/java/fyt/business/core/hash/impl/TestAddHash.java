package fyt.business.core.hash.impl;

import fyt.business.core.hash.TestHash;

import java.util.SortedMap;
import java.util.TreeMap;


/**
 * 增加节点数据迁移规则
 *
 * 举例
 * 有一个hash环 实际节点有3个 A  B  C
 * 在不增加虚拟节点的情况下  整个hash环被分为3个部分 数据会根据hash值 随机分配到3个hash段
 * A-B B-C C-A  本例代码中取的是顺时针下第一个节点 那么
 * A-B 实际上是分配在B 节点
 * B-C 实际上是分配在C 节点
 * C-A 实际上是分配在A 节点
 * 若此时 增加一个节点 D  经过计算 导致 hash环节点分配如下 A  B  D  C
 * hash环被分成 A-B  B-D  D-C  C-A
 * 此情况下 则需要迁移 原先 属于B-C hash段的数据
 *
 * 找出 hash值 在 B-D之间的数据 将该部分数据从节点C迁移到节点D 即可
 *
 *
 *
 *
 */


public class TestAddHash extends TestHash{

    //定义实际节点
    static String[] sufix = new String[]{"00000","00001","00002","00003"};

    //定义虚拟节点数量
    static int viratulSize = 10;

    //模拟hash环
    TreeMap<Integer,String> sortedMap = new TreeMap<>();

    public TestAddHash(){
        this.init();
    }


    void init(){

        /**
         * 根据表名称以及虚拟节点数量 对hash环进行填充 记录 hash值 和 表 位置
         * 记录虚拟节点与实际节点之间的位置
         */
        for(int i=0;i<sufix.length;i++){

            for(int j=0;j<viratulSize;j++){
                String sufixValue = sufix[i];
                String str = sufixValue+"viratulSize"+j;
                int hash =this.getHash(str);
                System.out.println("hash:"+hash+"......value:"+sufixValue);
                sortedMap.put(hash,sufixValue);
            }
        }

    }

    @Override
    public String getValue(String str) {

        if(str == null || sortedMap.isEmpty()){
            return null;
        }

        //计算 目标内容的 hash值
        int hash = getHash(str);
        /**
         *  获取目标内容hash值 顺时针下的第一个节点
         */
        SortedMap<Integer,String> subMap = sortedMap.tailMap(hash);
        System.out.println("subMap:"+subMap);

        //若tailMap 不存在 则归我sortedMap 初始key 代表hash 闭环
        int key = subMap.isEmpty()?sortedMap.firstKey():subMap.firstKey();
        String sufixStr  = sortedMap.get(key);
        return sufixStr;
    }


    public static void main(String[] args) throws Exception{


        TreeMap<Integer,String> sortedMap = new TreeMap<>();


        sortedMap.put(1,"1");
        sortedMap.put(3,"3");
        sortedMap.put(5,"5");
        System.out.println(sortedMap.tailMap(6));
        System.out.println(sortedMap.lowerKey(6));
        System.out.println(sortedMap.higherKey(6));

    }
}
