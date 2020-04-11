package com.fortunetree.basic.support.commons.business.util;

import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.Collection;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Assert 扩展
 * 常见断言推荐使用
 * @see org.springframework.util.Assert
 * @author
 * @date
 */
public class AssertUtils {

    public static void notNull(Object object, String errorMsg) {
        if (object == null)
            throw new IllegalArgumentException(errorMsg);
        if (object instanceof String) {
            if (org.apache.commons.lang3.StringUtils.isBlank((String) object)) {
                throw new IllegalArgumentException(errorMsg);
            }
        }
    }

    public static void notNull(String errorMsg, Object... expression) {
        for (Object exp : expression) {
            notNull(exp, errorMsg);
        }
    }

    public static void isTrue(String errorMsg, boolean... expression) {
        for (boolean exp : expression) {
            Assert.isTrue(exp,errorMsg);
        }
    }

    public static void isFalse(String errorMsg, boolean... expression) {
        for (boolean exp : expression) {
            isFalse(exp,errorMsg);
        }
    }



    public static void isFalse(boolean expression, String errorMsg) {
        if (expression)
            throw new IllegalArgumentException(errorMsg);
    }

    public static void isFalse(boolean expression, Supplier<String> messageSupplier) {
        if (!expression) {
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }

    /** @deprecated */
    @Deprecated
    public static void isFalse(boolean expression) {
        isFalse(expression, "[Assertion failed] - this expression must be false");
    }

    public static void isEmpty(@Nullable Object[] array, String message) {
        if (!ObjectUtils.isEmpty(array)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void isEmpty(@Nullable Object[] array, Supplier<String> messageSupplier) {
        if (!ObjectUtils.isEmpty(array)) {
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }

    /** @deprecated */
    @Deprecated
    public static void isEmpty(@Nullable Object[] array) {
        isEmpty(array, "[Assertion failed] - this array must be empty: it must not contain at least 1 element");
    }

    /**
     * Collection
     */

    public static void isEmpty(@Nullable Collection<?> collection, String message) {
        if (!CollectionUtils.isEmpty(collection)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void isEmpty(@Nullable Collection<?> collection, Supplier<String> messageSupplier) {
        if (!CollectionUtils.isEmpty(collection)) {
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }

    /** @deprecated */
    @Deprecated
    public static void isEmpty(@Nullable Collection<?> collection) {
        isEmpty(collection, "[Assertion failed] - this collection must be empty: it must not contain at least 1 " +
                "element");
    }

    public static void isEmpty(@Nullable Map<?, ?> map, String message) {
        if (!CollectionUtils.isEmpty(map)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void isEmpty(@Nullable Map<?, ?> map, Supplier<String> messageSupplier) {
        if (!CollectionUtils.isEmpty(map)) {
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }

    /** @deprecated */
    @Deprecated
    public static void isEmpty(@Nullable Map<?, ?> map) {
        isEmpty(map, "[Assertion failed] - this map must not be empty; it must contain at least one entry");
    }



    public static void error(String errorMsg) {
        throw new IllegalArgumentException(errorMsg);
    }

    public static void result(boolean rs, String errorMsg) {
        if (!rs)
            throw new IllegalArgumentException(errorMsg);
    }

    @Nullable
    private static String nullSafeGet(@Nullable Supplier<String> messageSupplier) {
        return messageSupplier != null ? (String)messageSupplier.get() : null;
    }

}
