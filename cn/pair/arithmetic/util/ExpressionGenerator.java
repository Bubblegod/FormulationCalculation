package cn.pair.arithmetic.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * function:用于构造出基本的表达式
 * 
 * @author liangjing yuanzhijie
 *
 */
public class ExpressionGenerator {
	int formulationNumber;
	int maximunValue;
	int randomNumber_Number, randomNumber_integer, randomNumber_fraction;
	List<String> integerList = new ArrayList<>();
	List<String> fractionList = new ArrayList<>();
	List<String> signList = new ArrayList<>();
	int[] position = new int[10];

	public ExpressionGenerator() {

	}

	public ExpressionGenerator(int formulationNumber, int maximunValue) {

		this.formulationNumber = formulationNumber;
		this.maximunValue = maximunValue;

		// formulationListGenerating();

	}

	void numberGenerating() {
		Random random1 = new Random();
		randomNumber_Number = random1.nextInt(3);
		randomNumber_Number += 2;

		Random random2 = new Random();
		randomNumber_integer = random2.nextInt(randomNumber_Number + 1);
		randomNumber_fraction = randomNumber_Number - randomNumber_integer;

	}

	void integerListGenerating() {
		int i;
		for (i = 0; i < randomNumber_integer; i++) {
			int temp;
			Random randomTemp = new Random();
			temp = randomTemp.nextInt(this.maximunValue);
			integerList.add(Integer.toString(temp));
		}
	}

	void fractionListGenerating() {
		int i;
		i = 0;
		while (i < randomNumber_fraction) {
			int temp1, temp2;

			Random randomTemp1 = new Random();
			Random randomTemp2 = new Random();

			temp1 = randomTemp1.nextInt(this.maximunValue - 1);
			temp1 += 1;
			temp2 = randomTemp2.nextInt(this.maximunValue - 2);
			temp2 += 2;

			if (!((temp1 % temp2 == 0) || (temp2 % temp1 == 0))) {
				temp1 = temp1 / getGCD(temp1, temp2);
				temp2 = temp2 / getGCD(temp1, temp2);
				if ((temp1 != temp2) && (temp1 != 0) && (temp2 != 0)) {
					if (temp1 > temp2) {
						fractionList.add(Integer.toString(temp1 / temp2) + "`"
								+ Integer.toString(temp1 % temp2) + "/"
								+ Integer.toString(temp2));

					} else {
						fractionList.add(Integer.toString(temp1) + "/"
								+ Integer.toString(temp2));
					}

					i++;
				}

			}

		}

		for (i = 0; i <= randomNumber_fraction; i++) {
			int temp;
			Random randomTemp = new Random();
			temp = randomTemp.nextInt(randomNumber_integer + 1);
			position[i] = temp;
		}

	}

	void signListGenerating() {
		int i;
		for (i = 0; i < randomNumber_Number - 1; i++) {
			int temp;
			Random randomTemp = new Random();
			temp = randomTemp.nextInt(4);
			if (temp == 0) {
				signList.add("+");

			} else if (temp == 1) {
				signList.add("-");

			} else if (temp == 2) {
				signList.add("*");

			} else {
				signList.add("÷");
			}
		}
	}

	String singleFormulationGenerating() {
		int i, j, signNumber, signIndex = 0;
		String formulation = "";

		integerList.clear();
		fractionList.clear();
		signList.clear();

		numberGenerating();

		integerListGenerating();

		fractionListGenerating();

		signListGenerating();

		signNumber = randomNumber_Number - 1;

		for (i = 0; i < randomNumber_fraction; i++) {
			if (position[i] == 0) {
				formulation += fractionList.get(i);
				if (signIndex < signNumber) {
					formulation += signList.get(signIndex);
					signIndex++;
				}
			}
		}
		for (i = 0; i < randomNumber_integer; i++) {
			for (j = 0; j < randomNumber_fraction; j++) {
				if (position[j] == i + 1) {
					formulation += fractionList.get(j);
					if (signIndex < signNumber) {
						formulation += signList.get(signIndex);
						signIndex++;
					}
				}
			}
			if((formulation.length()>0)&&(formulation.substring(formulation.length()-1).equals("÷"))&&(integerList.get(i).equals("0"))){
				Random random = new Random();
				int temp=random.nextInt(maximunValue-1);
				temp++;
				integerList.set(i, Integer.toString(temp));
			}
			formulation += integerList.get(i);
			if (signIndex < signNumber) {
				formulation += signList.get(signIndex);
				signIndex++;
			}
		}

		return formulation;
	}

	public List<String> formulationListGenerating() {
		int i;
		List<String> formulationList = new ArrayList<>();
		for (i = 0; i < formulationNumber; i++) {
			formulationList.add(singleFormulationGenerating());

		}

		int order = 1;
		try (PrintWriter w = new PrintWriter("D:\\formulation.txt")) {
			for (String line : formulationList) {
				line = order + "、" + line;
				w.println(line);
				order++;
			}
		} catch (IOException e) {

		}
		return formulationList;
	}

	int getGCD(int n1, int n2) {
		int gcd = 0;
		if (n1 < n2) {
			n1 = n1 + n2;
			n2 = n1 - n2;
			n1 = n1 - n2;
		}
		if (n1 % n2 == 0) {
			gcd = n2;
		}
		while (n1 % n2 > 0) {
			n1 = n1 % n2;
			if (n1 < n2) {
				n1 = n1 + n2;
				n2 = n1 - n2;
				n1 = n1 - n2;
			}
			if (n1 % n2 == 0) {
				gcd = n2;
			}
		}
		return gcd;

	}
}
