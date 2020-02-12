/**
 *
 *
 * https://shop.charityShop.org
 *
 * 版权所有，侵权必究！
 */

package org.greencode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class CharityShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(CharityShopApplication.class, args);
	}

}