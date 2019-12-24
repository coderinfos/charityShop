/**
 *
 *
 * https://shop.charityShop.org
 *
 * 版权所有，侵权必究！
 */

package org.greencode.common.annotation;

import java.lang.annotation.*;

/**
 * app登录效验
 *
 * @author
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Login {
}
