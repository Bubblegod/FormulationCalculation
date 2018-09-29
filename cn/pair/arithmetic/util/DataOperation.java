/**
 * 
 */
package cn.pair.arithmetic.util;

/**
 * function：该类主要完成整数与假分数，带分数之间的运算和比较以及格式化问题
 * 
 * @author liangjing yuanzhijie
 *
 */
public class DataOperation {

	/**
	 * function：对计算结果进行规范化（分数的格式化，若为假分数，统一化为带分数或整数格式，否则化为最简真分数）
	 * 
	 * @param fraction
	 * @return
	 */
	public String FormatFraction(String fraction) {
		// 1、如果计算结果为带分数
		if (judgeOperType(fraction) == 1) {
			// 将带分数根据字符"`"来进行分割(比如2`4/5会被分割成2和4/5)
			String a[] = fraction.split("`");
			// 将带分数中的分数根据字符"/"来进行分割，得到分子以及分母(比如4/5被分割成4和5)
			String b[] = a[1].split("/");
			// 带分数中的整数值
			int intValue = new Integer(a[0]).intValue();
			// 分母
			int denominator = new Integer(b[1]).intValue();
			// 分子
			int molecule = new Integer(b[0]).intValue();
			// 若分子大于分母且分母不为0（假分数）
			if (molecule > denominator && denominator != 0) {
				// 作取余运算，若不等于0，则表示可将该假分数转化为最简真分数的形式
				if (molecule % denominator != 0) {
					intValue += molecule / denominator;
					molecule = molecule % denominator;
					String sf = SimpleFraction(molecule + "/" + denominator);
					fraction = intValue + "`" + sf;
				} else {
					// 作取余运算，若等于0，则表示可将该假分数转化为整数的形式
					intValue += molecule / denominator;
					fraction = "" + intValue;
				}
				// 若分子小于分母（真分数）
			} else if (molecule < denominator) {
				// 如果分子为0，那么计算结果的值就为0
				if (molecule == 0) {
					fraction = "0";
				} else {
					String sf = SimpleFraction(molecule + "/" + denominator);
					fraction = intValue + "`" + sf;
				}
				// 若分子等于分母
			} else if (molecule == denominator) {
				intValue += 1;
				fraction = intValue + "";
			}
			// 2、 如果计算结果为分数
		} else if (judgeOperType(fraction) == 2) {
			String a[] = fraction.split("/");
			int intValue = 0;
			int denominator = new Integer(a[1]).intValue();
			int molecule = new Integer(a[0]).intValue();
			// 分子大于分母且分母不为0（为假分数）
			if (molecule > denominator && denominator != 0) {
				// 作取余运算，若不等于0，则表示可将该假分数转化为最简真分数的形式，若等于0，则表示可将该假分数转化为整数的形式
				if (molecule % denominator != 0) {
					intValue += molecule / denominator;
					molecule = molecule % denominator;
					String sf = SimpleFraction(molecule + "/" + denominator);
					fraction = intValue + "`" + sf;
				} else {
					intValue += molecule / denominator;
					fraction = "" + intValue;
				}
			}
			// 分子小于分母（为真分数）
			else if (molecule < denominator) {
				if (molecule == 0) {
					fraction = "0";
				} else {
					String sf = SimpleFraction(molecule + "/" + denominator);
					fraction = sf;
				}
				// 分子等于分母
			} else if (molecule == denominator) {
				intValue += 1;
				fraction = intValue + "";
			}
		}

		return fraction;
	}

	/**
	 * function：将真分数化为最简形式
	 * 
	 * @param fraction
	 * @return
	 */
	public String SimpleFraction(String fraction) {
		String a[] = fraction.split("/");
		// 分母
		int denominator = new Integer(a[1]).intValue();
		// 分子
		int molecule = new Integer(a[0]).intValue();
		int j = gcf(denominator, molecule);
		denominator = denominator / j;
		molecule = molecule / j;
		return molecule + "/" + denominator;
	}

	/**
	 * function：求两个数的最大公因子
	 * 
	 * @param m
	 * @param n
	 * @return
	 */
	public int gcf(int m, int n) {
		if (n == 0) {
			return m;
		}
		int r = m % n;
		return gcf(n, r);
	}

	/**
	 * function：将带分数转化为假分数
	 * 
	 * @param fraction
	 * @return
	 */
	public String changeFraction(String fraction) {
		// 将带分数根据字符"`"来进行分割(比如2`4/5会被分割成2和4/5)
		String a[] = fraction.split("`");
		// 将带分数中的分数根据字符"/"来进行分割，得到分子以及分母(比如4/5被分割成4和5)
		String b[] = a[1].split("/");
		// 带分数中的整数值
		int intValue = new Integer(a[0]).intValue();
		// 分母
		int denominator = new Integer(b[1]).intValue();
		// 分子
		int molecule = new Integer(b[0]).intValue();
		// 进行计算得出带分数所对应的假分数
		molecule = intValue * denominator + molecule;
		return molecule + "/" + denominator;
	}

	/**
	 * function：分数的加法
	 * 
	 * @param leftData
	 * @param rightData
	 * @return
	 */
	public String addFraction(String leftData, String rightData) {
		// 将分数根据字符"/"来进行分割
		String a[] = leftData.split("/");
		// 分母
		int denominator1 = new Integer(a[1]).intValue();
		// 分子
		int molecule1 = new Integer(a[0]).intValue();
		String b[] = rightData.split("/");
		int denominator2 = new Integer(b[1]).intValue();
		int molecule2 = new Integer(b[0]).intValue();
		// 计算结果的分母
		int resultDeno = denominator1 * denominator2;
		// 计算结果的分子
		int resultMole = denominator1 * molecule2 + molecule1 * denominator2;
		// 将结果同样以分数的形式返回
		return resultMole + "/" + resultDeno;
	}

	/**
	 * function：分数的减法
	 * 
	 * @param leftData
	 * @param
	 * @return
	 */
	public String subFraction(String leftData, String rightData) {
		String a[] = leftData.split("/");
		int denominator1 = new Integer(a[1]).intValue();
		int molecule1 = new Integer(a[0]).intValue();
		String b[] = rightData.split("/");
		int denominator2 = new Integer(b[1]).intValue();
		int molecule2 = new Integer(b[0]).intValue();
		int resultDeno = denominator1 * denominator2;
		int resultMole = molecule1 * denominator2 - denominator1 * molecule2;
		return resultMole + "/" + resultDeno;
	}

	/**
	 * function：分数的乘法
	 * 
	 * @param leftData
	 * @param rightData
	 * @return
	 */
	public String mulFraction(String leftData, String rightData) {
		String a[] = leftData.split("/");
		int denominator1 = new Integer(a[1]).intValue();
		int molecule1 = new Integer(a[0]).intValue();
		String b[] = rightData.split("/");
		int denominator2 = new Integer(b[1]).intValue();
		int molecule2 = new Integer(b[0]).intValue();
		int resultDeno = denominator1 * denominator2;
		int resultMole = molecule1 * molecule2;
		return resultMole + "/" + resultDeno;
	}

	/**
	 * function：分数的除法
	 * 
	 * @param leftData
	 * @param rightData
	 * @return
	 */
	public String divFraction(String leftData, String rightData) {
		String a[] = leftData.split("/");
		int denominator1 = new Integer(a[1]).intValue();
		int molecule1 = new Integer(a[0]).intValue();
		String b[] = rightData.split("/");
		int denominator2 = new Integer(b[1]).intValue();
		int molecule2 = new Integer(b[0]).intValue();
		int resultDeno = denominator1 * molecule2;
		int resultMole = molecule1 * denominator2;
		return resultMole + "/" + resultDeno;
	}

	/**
	 * function：比较两个操作数的大小
	 * 
	 * @param leftData
	 * @param rightData
	 * @return
	 */
	public String compareData(String leftData, String rightData) {
		String a[] = leftData.split("/");
		int denominator1 = Integer.parseInt(a[1]);
		int molecule1 = Integer.parseInt(a[0]);
		String b[] = rightData.split("/");
		int denominator2 = Integer.parseInt(b[1]);
		int molecule2 = Integer.parseInt(b[0]);
		int resultMole = molecule1 * denominator2 - denominator1 * molecule2;
		if (resultMole > 0) {
			return "1";// 操作数1大于操作数2
		} else if (resultMole == 0) {
			return "0";// 操作数1等于操作数2
		} else
			return "-1";// 操作数1小于操作数2
	}

	/**
	 * function：判断操作数的类型
	 * 
	 * @param operand
	 * @return
	 */
	public int judgeOperType(String operand) {

		if (operand.indexOf("`") != -1) {
			return 1; // 说明该操作数是带分数
		} else if (operand.indexOf("/") != -1) {
			return 2; // 说明该操作数是分数
		} else {
			return 3; // 说明该操作数是整数
		}
	}

	/**
	 * function：计算当前该二叉树所对应的四则运算表达式的值（将操作数通通转化为分数的形式再进行相对应的计算，通过计算得到的结果同样以分数的形式返回，最后再利用FormatFraction方法对计算结果进行规范化）
	 * 
	 * @param letfData
	 *            操作数
	 * @param rightData
	 *            操作数
	 * @param operation
	 *            操作符
	 * @return
	 */
	public String calculateData(String letfData, String rightData, String operation) {
		if (judgeOperType(letfData) == 1) {
			letfData = changeFraction(letfData);
		} else if (judgeOperType(letfData) == 3) {
			letfData = letfData + "/1";
		}
		if (judgeOperType(rightData) == 1) {
			rightData = changeFraction(rightData);
		} else if (judgeOperType(rightData) == 3) {
			rightData = rightData + "/1";
		}
		switch (operation) {
		case "+":
			return addFraction(letfData, rightData);
		case "-":
			return subFraction(letfData, rightData);
		case "*":
			return mulFraction(letfData, rightData);
		case "?":
			return compareData(letfData, rightData);
		default:
			return divFraction(letfData, rightData);

		}

	}
}
