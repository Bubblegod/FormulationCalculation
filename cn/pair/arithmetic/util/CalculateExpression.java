/**
 * 
 */
package cn.pair.arithmetic.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import cn.pair.arithmetic.data_struct.BinaryTree;

/**
 * function：该类调用其他功能模块，完成表达式的规范化，并且计算出表达式的值。
 * 
 * @author liangjing yuanzhijie
 *
 */
public class CalculateExpression {
	// 该类主要用于进行一些分数或整数的格式化和计算或比较等
	private DataOperation dataOperation;
	// 该类用于构建二叉树，完成基本表达式的规范化及查重与计算答案等功能
	private ExpressionOperation expressionOperation;
	// 該類用於構建出基本的四則運算表達式
	private ExpressionGenerator generator;
	// 存放最終的四則運算表達式
	HashSet<String> resultExpressions = new HashSet<>();
	// 存放最終的四則運算表達式的正確答案
	List<String> answers = new ArrayList<String>();

	public CalculateExpression(DataOperation dataOperation,
			ExpressionOperation expressionOperation,
			ExpressionGenerator generator) {
		this.dataOperation = dataOperation;
		this.expressionOperation = expressionOperation;
		this.generator = generator;
	}

	public void caculate() {

		// 構建出基本的四則運算表達式
		List<String> basicExpressions = generator.formulationListGenerating();
		for (String expression : basicExpressions) {
			// 中綴表達式轉後綴表達式
			String suffixExpression = expressionOperation
					.changeExpression(expression);
			// 後綴表達式構建二叉樹
			BinaryTree tree = expressionOperation
					.changeToBinaryTree(suffixExpression);
			// 優化二叉樹，避免出現負數情況
			expressionOperation.adjustmentTree(tree);
			// 將二叉樹中序遍歷，得到統一按預定運算順序的四則運算表達式（查重）
			String result = expressionOperation.treeToExpression(tree);
			resultExpressions.add(result);
		}

		for (String resultExpression : resultExpressions) {
			String suffixExpression = expressionOperation
					.changeExpression(resultExpression);
			BinaryTree bt = expressionOperation
					.changeToBinaryTree(suffixExpression);
			expressionOperation.adjustmentTree(bt);
			// 得到二叉树的值(即表达式的答案)
			String answer = expressionOperation.getTreeValue(bt);
			// 規範化答案
			answers.add(dataOperation.FormatFraction(answer));
		}

		try {
			FileUtil.generate(resultExpressions);
			FileUtil.answer(answers);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
