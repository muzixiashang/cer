package com.liyunet.common.luckbag;

import java.util.Random;

import com.liyunet.util.PushRefinedCalculation;

public class LuckBagUtile {

	public static double getRandomMoney(double remainMoney, int remainSize) {
		// remainSize 剩余的红包数量
		// remainMoney 剩余的钱

		if (remainSize == 1) {
			remainSize--;
			return (double) Math.round(remainMoney * 10) / 10;
		}
		Random r = new Random();
		double min = 0.1; //
		
		
		double max = remainMoney / remainSize * 2;
		double money = r.nextDouble() * max;
		
		
		if (remainMoney <= 0.0) {
			money = 0.0;
		} else {
			money = money <= min ? 0.1 : money;
			money = Math.floor(money * 10) / 10;
			remainSize--;
			remainMoney -= money;
		}
		return money;
	}

	public static void main(String[] args) {

		double money = 0.2;
		int size = 10;
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < 10; i++) {
			double randomMoney = getRandomMoney(money, size);
			System.out.println("随机金额 " + randomMoney);
			buffer.append(randomMoney + "--");
			money = PushRefinedCalculation.sub(money, randomMoney);
			size = size - 1;
			System.out.println("剩余金额 " + money);
		}
		System.out.println(buffer);

	}

}
