# 第二周作业

序号对应课程作业编号，文件为作业答案以及所需的资源。

4、GC 和堆内存的总结、GCLogAnalysis.java

6、OkHttpTest.java

## 4、GC 和堆内存的总结

测试环境：mac os 10.15.7、内存8g、jdk15

GC取样：SerialGC、ParallelGC、 ConcMarkSweepGC、 G1GC

内存取样：128m、256m、512m、1g、2g、4g

分别测试了以上组合在运行GCLogAnalysis时产生的GC行为、次数、时间。

SerialGC ：

```
GC：20(24.5 ms)，Minor GC：6(20.2 ms)，Full GC：13(26.9 ms)
GC：8(54.2 ms)，Minor GC：7(48.6 ms)，Full GC：1(92.9 ms)
GC：5(107 ms)，Minor GC：5(107 ms)，Full GC：0(0 ms)
GC：3(139 ms)，Minor GC：3(139 ms)，Full GC：0(0 ms)
GC：0(0 ms)，Minor GC：0(0 ms)，Full GC：0(0 ms)
GC：0(0 ms)，Minor GC：0(0 ms)，Full GC：0(0 ms)
```

ParallelGC：

```
GC：30(24.5 ms)，Minor GC：6(17.3 ms)，Full GC：24(26.3 ms)
GC：15(36.6 ms)，Minor GC：5(27.5 ms)，Full GC：10(54.8 ms)
GC：5(89.1 ms)，Minor GC：5(89.1 ms)，Full GC：0(0 ms)
GC：1(264.5 ms)，Minor GC：1(264.5 ms)，Full GC：0(0 ms)
GC：0(0 ms)，Minor GC：0(0 ms)，Full GC：0(0 ms)
GC：0(0 ms)，Minor GC：0(0 ms)，Full GC：0(0 ms)
```

~~ConcMarkSweepGC(CMCGC)：由于安装使用jdk15，并无该VM选项，所以并未做该GC测试~~

```
GC：0(0 ms)，Minor GC：0(0 ms)，Full GC：0(0 ms)
GC：0(0 ms)，Minor GC：0(0 ms)，Full GC：0(0 ms)
GC：0(0 ms)，Minor GC：0(0 ms)，Full GC：0(0 ms)
GC：0(0 ms)，Minor GC：0(0 ms)，Full GC：0(0 ms)
GC：0(0 ms)，Minor GC：0(0 ms)，Full GC：0(0 ms)
GC：0(0 ms)，Minor GC：0(0 ms)，Full GC：0(0 ms)
```

G1GC：

```
GC：46(8.54 ms)，Minor GC：43(15.5 ms)，Full GC：3(45.6  ms)
GC：4(17.6 ms)，Minor GC：4(17.6 ms)，Full GC：0(0 ms)
GC：10(28.7 ms)，Minor GC：10(28.7 ms)，Full GC：0(0 ms)
GC：9(31.4 ms)，Minor GC：9(31.4 ms)，Full GC：0(0 ms)
GC：8(30.2 ms)，Minor GC：8(30.2 ms)，Full GC：0(0 ms)
GC：4(74.2 ms)，Minor GC：4(74.2 ms)，Full GC：0(0 ms)
```

总结：

- 内存128m时由于堆大小小于创建对象占用的大小，导致oom。
- 内存越小Yong GC 和 Full GC次数都会频繁触发；当大量的对象不能释放时，会导致大量时间在做Full GC。
- 同等内存大小情况下串行GC时间几乎是并行GC的二倍。
- 内存越大的情况下G1平均GC时间可控，回收效率高；同时时间比其他GC时间短，内存越大G1效果越好；可以看到内存越大的情况下G1都基本优于串行、并行。

这节学的比较吃力，在知识的理解和运用方面还存在很多不足，希望老师能多多指点。
