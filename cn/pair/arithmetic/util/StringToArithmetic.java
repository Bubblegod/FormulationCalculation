/**
 * 
 */
package cn.pair.arithmetic.util;

import java.util.Stack;

/**
 * function：将中缀表达式字符串转换为后缀表达式字符串
 * 
 * @author liangjing yuanzhijie
 *
 */
public class StringToArithmetic {

	/**
	 * function：将中缀表达式字符串转换为后缀表达式字符串
	 * 
	 * @param infixExpression
	 * @return
	 */
	public static String infixToSuffix(String infixExpression) {
		// 创建操作符堆栈
		Stack<Character> characterStack = new Stack<Character>();
		// 要输出的后缀表达式字符串
		String suffix = "";
		// 传入的中缀表达式的长度
		int length = infixExpression.length();
		for (int i = 0; i < length; i++) {
			// 临时字符变量
			char temp;
			// 获取该中缀表达式中的每一个字符并进行相对应的判断
			char ch = infixExpression.charAt(i);

			switch (ch) {
			// 忽略空格
			case ' ':
				break;

			// 如果是左括号直接压入栈中
			case '(':
				characterStack.push(ch);
				break;

			// 碰到'+' '-'，将栈中的所有运算符全部弹出去，直至碰到左括号为止，输出到队列中去
			case '+':
			case '-':
				while (characterStack.size() != 0) {
					temp = characterStack.pop();
					if (temp == '(') {
						// 如果推出来的那个字符刚好是左括号，那么就重新将左括号放回栈中，并终止循环
						characterStack.push('(');
						break;
					}
					suffix += temp;
				}
				// 没有进入循环说明当前为第一次进入或者前面运算都有括号等情况所导致
				// 将当前符号压入栈中
				characterStack.push(ch);
				suffix += "#";
				break;

			// 如果是乘号或者除号，则弹出栈中所有运算符，直到碰到加号、减号、左括号为止，最后将该运算符压入栈中
			case '*':
			case '÷':
				while (characterStack.size() != 0) {
					temp = characterStack.pop();
					// 只有比当前优先级高的或者相等的才会弹出到输出队列，遇到加减左括号，直接停止当前循环
					if (temp == '+' || temp == '-' || temp == '(') {
						characterStack.push(temp);
						break;
					} else {
						suffix += temp;
					}
				}
				// 没有进入循环说明当前为第一次进入或者前面运算都有括号等情况所导致
				// 将当前符号压入栈中
				characterStack.push(ch);
				suffix += "#";
				break;

			// 如果碰到的是右括号，则距离栈顶的第一个左括号上面的所有运算符将被弹出栈并抛弃左括号(既不需要将左括号输出到队列中也不需要将右括号压入栈中)
			case ')':
				while (!characterStack.isEmpty()) {
					temp = characterStack.pop();
					if (temp == '(') {
						break;
					} else {
						suffix += temp;
					}
				}
				break;

			// 默认情况，如果读取到的是操作数，则直接送至输出队列
			default:
				suffix += ch;
				break;
			}

		}

		// 最后如果栈中依然还有运算符的话，则把剩余运算符一次弹出，送至输出序列
		while (characterStack.size() != 0) {
			suffix += characterStack.pop();
		}

		return suffix;
	}

}
