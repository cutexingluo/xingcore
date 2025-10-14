package top.cutexingluo.core.designtools.juc.thread.local;

/**
 * @author XingTian
 * @version 1.0.0
 * @date 2023/9/27 21:33
 */
public interface BaseThreadLocalHelper<T> {
    T getThreadLocalValue();

    BaseThreadLocalHelper<T> setThreadLocalValue(T name);

    ThreadLocal<T> getThreadLocal();

    void remove();

    BaseThreadLocalHelper<T> doTask(Runnable runnable);

    default void runTaskAndRemove(T value, Runnable runnable) {
        setThreadLocalValue(value);
        doTask(runnable);
        remove();
    }

}
