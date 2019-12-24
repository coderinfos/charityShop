/**
 * Copyright (c) 2018 慈善商店 All rights reserved.
 *
 * https://shop.charityShop.org
 *
 * 版权所有，侵权必究！
 */

package org.greencode.datasource.annotation;

import java.lang.annotation.*;

/**
 * 多数据源注解
 *
 * @author
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DataSource {
    String value() default "";
}
