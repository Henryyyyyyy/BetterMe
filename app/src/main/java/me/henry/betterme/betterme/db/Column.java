package me.henry.betterme.betterme.db;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zj on 2017/4/18.
 * me.henry.betterme.betterme.db
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Column {
    boolean id() default false;
    String name()default "";
    boolean auto() default false;
    ColumnType type() default ColumnType.UNKNOWN;
    public enum  ColumnType{
        TONE,TMANY,SERIALIZABLE,UNKNOWN
    }
}
