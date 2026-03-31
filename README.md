# XingCore **核心工具包**

## :book:相关介绍

**xingcore**, XingCore, xinghe，意为“星核”，作为 Xing Java 系列工具库代码精华部分，作为后续 Xing Java工具库的基础、内核、依赖，也是后续相关 Java工具库的标准、规范。

### :scroll:简介

是一个功能丰富且易用的 **Java工具库**，通过诸多实用工具类的使用，旨在帮助开发者快速、便捷地完成各类开发任务。

XingCore 包含一系列接口，基础实现，涵盖核心工具、加密、数据结构算法等多个领域。

这些封装包含了系列字符串、数字、集合、编码、日期、文件、IO、加密、数据库JDBC、JSON、HTTP客户端等一系列基础操作，还包含了 ACM数据结构算法，各种base接口，快速开发工具类（链式调用、逻辑建造），可以满足各种不同的开发需求。

###   :golf:本包说明

本工具库遵从**面向接口**，所以接口可能分得特别细。

该 core 工具包将核心拆分应对不同使用情况，大大提高灵活性，未来sdk可能会依赖该sdk, 如 xingtools 等。

星天（xingtian）制作，基于 Java 8 向前兼容， 是一个整合各工具类的整合包。

## :bookmark:依赖使用

### :inbox_tray:安装教程

> 使用 Maven 导入依赖，选择下面一个方式

Maven 依赖 （全量版本）（会携带 Jackson 等系列包）

```xml
<dependency>
	<groupId>top.cutexingluo.core</groupId>
	<artifactId>xingcore-common-all</artifactId>
	<version>1.2.1</version>
</dependency>
```

Maven 依赖（无额外依赖）

```xml
<dependency>
	<groupId>top.cutexingluo.core</groupId>
	<artifactId>xingcore-common</artifactId>
	<version>1.2.1</version>
</dependency>
```



mini 版（去除 base-extra 包，精华版本）

Maven 依赖 （全量版本）（会携带 Jackson 等系列包）

```xml
<dependency>
	<groupId>top.cutexingluo.core</groupId>
	<artifactId>xingcore-mini-all</artifactId>
	<version>1.2.1</version>
</dependency>
```

Maven 依赖（无额外依赖，最简版本，只含三个包）

```xml
<dependency>
	<groupId>top.cutexingluo.core</groupId>
	<artifactId>xingcore-mini</artifactId>
	<version>1.2.1</version>
</dependency>
```



目前推荐使用的版本如下：

```txt
推荐使用最新版 v1.2.1
xingcore v1.2.0, v1.2.1
```

xingcore 最低版本不能低于 v1.2.0

## :wrench:包含组件

| 模块                | 介绍                                                         |
| ------------------- | ------------------------------------------------------------ |
| xingtools-base      | 基础包，包含各种接口，枚举类等，不含任何实体类，做公用声明   |
| xingtools-base-impl | 基础实现包(依赖xingcore-base)，包含各种接口的实现类，以及各种常用基础实体类、处理类 |
| xingtools-base-lib  | 基础工具包(依赖xingcore-base-impl)，包含各种常用工具类       |
| xingcore-base-extra | 扩展包(依赖xingcore-base-lib)，包含各种扩展类，不常用类，过时类，以及不稳定工具（未来常变化） |
|                     |                                                              |
| xingcore-bom        | bom包，用于管理依赖版本                                      |
|                     |                                                              |
| xingcore-mini       | 含（base、base-impl、base-lib）三个基础包，无额外包          |
| xingcore-mini-all   | 含（base、base-impl、base-lib）三个基础包，以及附加包        |
| xingcore-common     | 含全部四个基础包，无额外包                                   |
| xingcore-common-all | 含全部四个基础包，以及附加包                                 |

当前版本组件之间的依赖关系如下：

xingtools-base <- xingtools-base-impl <- xingtools-base-lib <- xingcore-base-extra

## :apple:使用方式

### :lemon:使用讲解

根据类名意思了解该功能，好处是暂时不用查文档，并且和hutool互补，能够加快开发效率.

1. 可以通过`Util.`静态类的方式调出静态方法，一般由`XT`开头的类，例如`XTObjUtil`, `XTStrUtil` 等，以便直接调出类名、方法。
2. 通过 new 一个工具操作类 `Handler`等
3. 通过 of 等方式使用，例如 `new StreamChain()` 或 `StreamChain.of()`

## 🧰部分代表功能

### ⚙️ 工具类

#### 1.数据封装接口 （`IResultData`, `IResult`）

`IR`, `IResultData`, `IResult` 分别追加提供 getMsg, getCode, getData 方法

`IResultDataSource`, `IResultSource` 继续追加 setMsg, setCode, setData 方法

例如`IResultData`接口如下

```java
public interface IResultData<T> extends IR {
    T getCode();
}
```

可以根据面向接口实现方法，来满足异常常量、返回类、异常的统一。

```java
// 异常常量
@Getter
public enum EnumResult implements IResultData<Integer> {
    // 成功
    SUCCESS(200, "操作成功");
    private final int code;//状态码
    private final String msg;//状态码对应的信息
}
// 异常
@Getter
public class ServerException extends RuntimeException implements IResultData<Integer>{
    private int code;
    @Override
    public String getMsg() {
        return super.getMessage();
    }
}
// 返回类
@Data
@Accessors(chain = true)
public class MyResult<T> implements IResultSource<Integer, T> {
    private Integer code;
    private String msg;
    private T data;
    
    // 面向接口
    public static <T> MyResult<T> successBy(IResultData<Integer> origin) {
        MyResult<T> result = new MyResult<>();
        result.setCode(origin.getCode());
        result.setMsg(origin.getMsg());
        return result;
    }
}
```

然后可以通过 ResultUtil 工具类进行封装返回值。

```java
// controller 示例
@RestController
@RequestMapping("/common")
public class CaptchaController {
    ...
    @GetMapping("/captcha") 
    public MyResult<?> getCaptchaInfo() {
        XX xx = testService.getXX();
        
        // ResultUtil 工具类
        return ResultUtil.selectFill(xx,
                EnumResult.SUCCESS, 
                EnumResult.ERROR,
                new MyResult<>());
        
		//  默认策略(可以更改) 等同于下面 
        
        return captcha == null || Boolean.FALSE.equals(xx)  ? 
            	MyResult.errorBy(EnumResult.ERROR):
                MyResult.successBy(EnumResult.SUCCESS).setData(xx);
    }
}
```

在`1.0.3`版本提供了几个基础实现类

`CommonResult` （通用返回类）

`MSResult<T>` (或 `R`), `Result`, `StrMSResult<T>`, `StrResult`  (code 为 `Integer`或 `String` , data 为 泛型 T或`Object` 的 四个组合)

`CommonResult` 类的基本属性如下：

```java
@Data
public class CommonResult<C, T> implements IResultSource<C, T> {
    protected C code;
    protected String msg;
    protected T data;
}
```

**贯彻面向接口！**

#### 2.锁、异步(多线程)

锁提供基本的 LockHandler 类，以及下面的子类 XTLockHandler , XTExtLockHandler 等类。

下面是 LockHandler 的基本使用。

```java
    @Test
    void test() {
        XTLockMeta lockMeta = new XTLockMeta(XTLockType.ReentrantLock); // 可重入锁
        LockHandler lockHandler = new LockHandler(lockMeta);
        lockHandler.init();
        // Spring 的线程池
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.initialize();


        // Runnable 接口
        XTAsync.runAsync(() -> { // 继承 CompleteFuture 类，扩展了一些静态方法
            lockHandler.lock(() -> {// Callable 接口
                for (int i = 0; i < 20; i++) {
                    XTJUC.sleepMillis(100); // 目前仅两个线程方法，睡100ms
                    System.out.println(Thread.currentThread().getName() + "," + i);
                }
                return null; // 返回值
            }, null);
        }, executor);


        XTAsync.runAsync(() -> {
            lockHandler.lock(() -> {
                for (int i = 0; i < 20; i++) {
                    XTJUC.sleepMillis(80);
                    System.out.println(Thread.currentThread().getName() + "," + i);
                }
                return null;
            }, null);

        }, executor);

        XTJUC.sleepMillis(100_000);
    }
```

#### 3.各种工具类

比如 `XTStrUtil` , `XTCollUtil` , `XTMapUtil` 等等扩展类，均是继承 hutool 包的对应工具类，可以直接使用目标类或扩展类。

各种需求示例

```java
// 替换 ${} 括号里的内容
XTPickUtil.putValueFromBraces("你们好！ ${}, 你好！","XXX");

// 根据 value 删除 Map 的值
HashMap<Integer,String> map = new HashMap<>();
XTMapUtil.deleteByValueWithStream(map, "hello world");

// 为 value 的 collection 添加数据
HashMap<Integer,List<String>> map = new HashMap<>();
XTMapUtil.checkAddAll(hashMap1, "hello", ArrayList::new,
                          Arrays.asList("hello", "world"));

// 组合路径 xx/aa
XTPathUtil.combinePath("xx", "aa", XTPathUtil.UNIX_SEPARATOR);
XTPathUtil.combinePath("xx/", "/aa", XTPathUtil.UNIX_SEPARATOR);

// json 序列化
// Jackson 模拟 FastJson
JacksonSerializer serializer = new JacksonSerializer().initToFastJson();
String s = serializer.stringify(new XX());

// 仿 JS Apply, Call, Bind
new XTApply(PrintClass.class, "print", String.class).apply(target,"hello world");
new XTCall(PrintClass.class, "print", String.class).call(target,"hello world");
new XTBind("print", String.class).bind(target,"hello world");

// 扩展比较器 XTComparable, XTComparator, 可以比较 null
XTComparator c = new XTComparator(true); // 正序比较, 如果是在 sort 里面 , true -> null 值排最后, false -> null 排前面
c.tryCompareNull(null , 1);

// 反射工具 ClassMaker
ClassMaker<XX> cm = new ClassMaker(XX.class);
XX xx = cm.newInstanceNoExc(); // 静默实例化

// 未完待续
```

#### 4.高级建造工具（`BuilderMapChain`, `StreamChain`）

##### 1.HashMap扩展

为 HashMap 添加值

```java
void test() {
    HashMap<String, Integer> hashMap = new HashMap<>();

    // 为 map 添加 key,value 对
    int i = XTHashMap.putMapEntriesFromDValues(
        hashMap,
        "hello", 1,
        "world", 2
    );


    System.out.println(hashMap); // {world=2, hello=1}

	// 为 map 的 list value 补充值, check 代表不存在就 new 一个ArrayList填充进去
    HashMap<String, List<String>> hashMap1 = new HashMap<>();
    XTMapUtil.checkAddAll(hashMap1, "hello", ArrayList::new,
                          Arrays.asList("hello", "world"));
    XTMapUtil.checkAddAll(hashMap1, "hello", ArrayList::new,
                          Arrays.asList("hello1", "world1"));


    System.out.println(hashMap1); // {hello=[hello, world, hello1, world1]}
}
```

##### 2.Optional 功能扩展类 StreamChain

StreamChain 包含 Optional 几乎所有方法，还对其进行了扩展

```java
void test() {
    StreamChain<Integer> chain = StreamChain.ofNullable(null)
        .directMap(v -> v == null ? 2 : (int) v + 1);
    System.out.println(chain); // StreamChain[2]

    StreamChain<Integer> streamChain = new ObjectStreamChain(1)
        .cast(Integer.class)
        .flatMap(StreamChain::ofNullable)
        .map(v -> v + 1);
    System.out.println(streamChain); // StreamChain[2]

    ObjectStreamChain objectStreamChain = new ObjectStreamChain(2);
    System.out.println(objectStreamChain);// StreamChain[2]

    System.out.println(objectStreamChain.equals(streamChain)); // true

    StreamChain<Integer> opt = StreamChain.ofNullable(1);
    List<Integer> collect = opt.stream().collect(Collectors.toList());
    Integer integer = opt
        .flatMap(StreamChain::of)
        .get();
    System.out.println(integer); // 1
    System.out.println(opt); // StreamChain[1]
    System.out.println(collect); // [1]

}
```

##### 3.高级建造类 BuilderMapChain

生成一个建造树，如果当前层的值不存在，便可以从其他兄弟节点获取或生成，或者从父节点生成，直到得到值，返回。

```java
void test() {
    BuilderMapChain chain = new BuilderMapChain(3, null,  () -> {
        // 第1层
        return "第1层,";
    }).withGetter(null, o -> {
        // 第2层
        String str = (String) o;
        str += "第2层,";
        return str;
    }).withListGetter(null, Arrays.asList( // 第3层
        (o) -> { // 从上1层获取数据
            String str = (String) o;
            str += "第3层-1,";
            return str;
        },
        (o) -> { // 从上2层获取数据
            String str = (String) o;
            str += "第3层-2,";
            return str;
        }
    ));
    
    // 下面三块是独立的，不在同一个方法内，因为创建便会填充数据（一次性对象）。
    
    Entry<Integer, String> entry = chain.createFrontDfs(3); // 前驱dfs创建3层
    System.out.println(entry); // Entry [key=3, value=第1层,第2层,第3层-1,]
    String s = chain.getValue(3); // 获取第3层数据
    System.out.println(s); // 第1层,第2层,第3层-1,

    Entry<Integer, String> entry2 = chain.createBackBfs(2); // 后驱bfs创建2层
    System.out.println(entry2); // Entry [key=1, value=第1层,第2层,]
    String s2 = chain.getValue(2); // 获取第2层数据
    System.out.println(s2); // 第1层,第2层,
    
    Entry<Integer, String> entry3 = chain.createBackDfs(3); // 后驱dfs创建3层
    System.out.println(entry3); // Entry [key=2, value=第1层,第3层-2,]
    String s3 = chain.getValue(3); //获取第3层数据
    System.out.println(s3); // 第1层,第3层-2,
}
```

第3层数据不为null，则返回数据。

第3层数据为null，便从第3层

>front（List 从前往后，前驱）
>
>back（List 从后往前，后驱）获取生成方法（用于生成该层数据，填充到该层）
>
>dfs （依次获取上层数据，直至能填充目标层数据）
>
>bfs （先把该层List遍历完成，还没有填充数据才从上层获取）

**那么有什么用呢？**

示例如下：

```java
void test{
    ...
    ApplicationContext applicationContext = SpringUtils.getApplicationContext(); // Spring 上下文
    // 创建3层建造树
    BuilderMapChain chain = new BuilderMapChain(3, applicationContext) 
        .with(redisTemplate, o -> { // redisTemplate 数据
            ApplicationContext ac = (ApplicationContext) o;
            return ac.getBean(RedisTemplate.class); // redisTemplate 数据不存在则从容器获取
        }).withList(redisCache, Arrays.asList(
        o -> {
            RedisTemplate<String, Object> rt = (RedisTemplate<String, Object>) o;
            return new RYRedisCache(rt); // 填充进 RYRedisCache
        },
        o -> {
            ApplicationContext ac = (ApplicationContext) o;
            return ac.getBean(RYRedisCache.class); // RYRedisCache 数据不存在则从容器获取
        }
    ));
    
    // 得到生成的值
    RYRedisCache redisCache = chain.createFrontDfs(3).getValue(3);
}
```

里面有许许多多快速开发的工具，还请多多研究。

#### 5.*系列算法

算法都放在  top.cutexingluo.tools.utils.se.algo.cpp 包下，顾名思义，工具/SE/算法/C++，

例如二分查找

```java
int index = XTBinarySearch.lowerBound(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 5);
// c++ std::lower_bound 找到大于或等于目标的数据的位置, index = 4
```

例如字符串算法，在字符串里面查找子串，原生java是一个个匹配，遇到多重复字符速度变慢。

这里有KMP算法

```java
int index = XTStringAlgo.find("abcabcabc...abcdabcabcabc", "abcd");// 查找重复字符串
```

例如数据结构，简易线段树，线段树，动态线段树，平衡二叉树，树状数组，字典树，Splay树，Treap树，B 树，B+ 树的基础实现，可供参考

```java
BTree<String, String> tree = new BTree<>(3); // B树
BPlusTree<String, String> tree = new BPlusTree<>(3); // B+树
tree.put("1", "1");
tree.put("4", "4");
tree.put("2", "2");
// 实现 NavigableMap 接口，兼容基本操作
```

例如数学

```java
Point a = new Point(1.0,1.0);
Point b = new Point(1.0,2.0);
double dis = a.distance(b); // 两点距离
Point middle = a.middle(b); // 两点中点
```

例如图论，Dijkstra 最短路，最小生成树，网络流等

例如状态机（类似 map）

```java
NodeStateMachine<Integer, StatusNode<EnumPay, EnumPay>> stateMachine = new NodeStateMachine<>();
stateMachine.put(EnumPay.ALIPAY.getCode(),
        new StatusNode<>(EnumPay.ALIPAY,
                Arrays.asList(
                        EnumPay.ALIPAY,
                        EnumPay.WECHAT,
                        EnumPay.UNIONPAY
                )
        ));
stateMachine.put(EnumPay.WECHAT.getCode(),
        new StatusNode<>(EnumPay.WECHAT,
                Arrays.asList(
                        EnumPay.WECHAT,
                        EnumPay.UNIONPAY
                )
        ));
boolean accept = stateMachine.canAcceptNode(EnumPay.ALIPAY.getCode(), EnumPay.ALIPAY.getCode());
System.out.println(accept); // true

boolean accept1 = stateMachine.canAcceptNode(EnumPay.ALIPAY.getCode(), EnumPay.WECHAT.getCode());
System.out.println(accept1); // true

boolean accept2 = stateMachine.canAcceptNode(EnumPay.ALIPAY.getCode(), EnumPay.UNIONPAY.getCode());
System.out.println(accept2); // false

boolean accept3 = stateMachine.canAcceptNode(EnumPay.ALIPAY.getCode(), EnumPay.CASH.getCode());
System.out.println(accept3); // false

StatusNode<EnumPay, EnumPay> node = stateMachine.get(EnumPay.ALIPAY.getCode());
System.out.println(node); // StatusNode(node=ALIPAY, nextNodes=[ALIPAY, WECHAT, UNIONPAY])
boolean contains = node.getChildren().contains(EnumPay.ALIPAY);
System.out.println(contains); // true
```

#### 6.异常处理器

可以对异常进行操作，可以实现 ExceptionDelegate 接口，自定义操作

提供了两个默认处理代表

- ExceptionPrintDelegate
- ExceptionLogDelegate

```java
// 三个参数分别为：自定义处理器，是否e.printStackTrace(), 是否System.err.println(e)
ExceptionPrintDelegate<Throwable> delegate = new ExceptionPrintDelegate<>((e, obj) -> {
    return e.getMessage();
}, true, true); 

// 处理异常
// 第一个参数为异常，第二个为任意对象，将会传到上面的表达式中
Object result = delegate.handle(new NullPointerException("test"), null);
```



### 🔧 注解

#### 1.***参数校验** （@XxxStatus）

必须导入 validation 包 并且参数/类上 `@Valid`或`@Validated`

不同类型参数校验注解如：

`@IntStatus` (Integer), `@StrStatus` (String)

`@ShortStatus` (Short), `@DoubleStatus` (Double) ...

```java
@Data
public class MyUserQuery {

    @StrStatus(notBlankIfPresent = true, // 1.首先如果非空字符串和 null 均会进入下一步
               anyStr = { // 2.必须匹配的字符串
            "1", "0" 
    }, message = "类型格式错误") 
    private String type;

    @StrStatus(anyReg = { // 正则匹配
            RegexPool.MOBILE
    }, message = "手机格式错误") // 验证
    @StrJson(value = SensitiveSerializer.class, name = "PHONE") //脱敏
    private String phone;

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式错误") //validation包的校验
    private String email;

    @StrJson(value = SensitiveSerializer.class, name = "") // 返回值脱敏
    private String password;
    
    @ShortStatus(
            matchNum = { // 必须匹配数字
                    EnumDelFlag.NOT_DELETED_CODE,
                    EnumDelFlag.IS_DELETED_CODE
            },
            message = "删除状态格式错误"
    )
    // @NotNull 
    // 可以添加 @NotNull注解 (注意是 validation 包的) 或者 @ShortStatus注解里面 notNull=true
    private Short delFlag;
}
```

如上所示，各种校验注解相互配合，加快代码的开发。

#### 2.异步线程 

1.可以使用**编程式**，例如 XTAsync, 或者你的类实现 ThreadHelper 接口或者  ThreadExecutorHelper 接口

下面示例作为 异步配置，同时兼容ThreadPoolTaskExecutor, AsyncConfigurer(支持@Async 注解)和 ThreadHelper (CompletableFuture 编程式操作)

```java
@EnableAsync
@Configuration
public class AsyncConfig implements AsyncConfigurer, ThreadHelper {

    @Autowired
    ThreadPoolTaskExecutor taskExecutor;

    @Override
    public Executor getAsyncExecutor() {
        return taskExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return null;
    }

    @Override
    public Executor executor() {
        return taskExecutor;
    }
}
```

注入该对象即可使用ThreadHelper里面的方法

2.声明式，通过注解控制，详见 xingtools





##  :memo:更新公告

[更新公告](./ReleaseNotes.md)
