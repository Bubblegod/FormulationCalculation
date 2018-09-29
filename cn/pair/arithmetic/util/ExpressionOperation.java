/**
 * 
 */
package cn.pair.arithmetic.util;

import java.util.Stack;

import cn.pair.arithmetic.data_struct.BinaryTree;

/**
 * function：该类用来处理初步拼合而成的四则运算表达式，使其满足作业要求，并将初步拼合而成的四则运算表达式按照优化策略统一规范化，便于之后的查重处理
 * 
 * @author liangjing yuanzhijie
 *
 */
public class ExpressionOperation {

	DataOperation dataOperation = new DataOperation();

	/**
	 * function：将中缀表达式转变为后缀表达式
	 * 
	 * @param expression
	 * @return
	 */
	public String changeExpression(String expression) {
		return StringToArithmetic.infixToSuffix(expression);

	}

	/**
	 * function：将后缀表达式字符串按要求构建出二叉树(表达式树的特点就是：树的树叶是操作数(常数或变量),而其它节点为操作符)
	 * 
	 * @param suffixExpression
	 * @return
	 */
	public BinaryTree changeToBinaryTree(String suffixExpression) {
		// 存储二叉树的栈
		Stack<BinaryTree> treeStack = new Stack<BinaryTree>();
		BinaryTree bt = new BinaryTree();
		// 首先需要将后缀表达式字符串转化为字符数组，接着作遍历操作
		char[] express = suffixExpression.toCharArray();
		// 暂存入栈信息
		String temp = "";
		for (int i = 0; i < express.length; i++) {
			// 当前该字符为操作数
			if (express[i] != '+' && express[i] != '-' && express[i] != '*' && express[i] != '÷' && express[i] != '#') {
				temp += express[i];
			} else if (express[i] == '#') {
				if (!temp.equals("")) {
					BinaryTree tree = new BinaryTree();
					tree.setRootData(temp);
					tree.setLeftTree(null);
					tree.setRightTree(null);
					treeStack.push(tree);
					bt = tree;
				}
				temp = "";
				// 当前该字符为操作符
			} else if (express[i] == '+' || express[i] == '-' || express[i] == '*' || express[i] == '÷') {
				// 若temp中数据不为空
				if (!temp.equals("")) {
					BinaryTree tree = new BinaryTree();
					tree.setRootData(temp);
					tree.setLeftTree(null);
					tree.setRightTree(null);
					treeStack.push(tree);
					bt = tree;
				}
				temp = "" + express[i];
				BinaryTree tree = new BinaryTree();
				tree.setRootData(temp);
				tree.setRightTree(treeStack.pop());
				tree.setLeftTree(treeStack.pop());
				treeStack.push(tree);
				temp = "";
				bt = tree;
			}
		}
		return bt;
	}

	/**
	 * function：优化策略一（按规则优化二叉树，使其计算过程中不会出现负数）
	 * 
	 * @param tree
	 * @return
	 */
	public BinaryTree adjustmentTree(BinaryTree tree) {
		if (tree != null) {
			String leftValue = getTreeValue(tree.getLeftTree());
			String rightValue = getTreeValue(tree.getRightTree());
			// 若右子树的值大于左子树，则交换子树
			if (rightValue.equals("") || leftValue.equals("")) {
				// 表示此时已经递归到叶子了，直接返回即可
				return tree;
			} else if (dataOperation.calculateData(rightValue, leftValue, "?").equals("1")) {
				tree = swapChildTree(tree);
			}
			tree.setLeftTree(adjustmentTree(tree.getLeftTree()));
			tree.setRightTree(adjustmentTree(tree.getRightTree()));
		}
		return tree;
	}

	/**
	 * function：优化策略二（中序遍历二叉树，转化为符合预定运算顺序的带括号的表达式）
	 * 
	 * @param tree
	 * @return
	 */
	public String treeToExpression(BinaryTree tree) {
		String left = "";
		String right = "";
		String expression = "";
		if (tree != null) {
			if (tree.getLeftTree() != null && tree.getRightTree() != null) {
				String leftRoot = tree.getLeftTree().getRootData();
				String rightRoot = tree.getRightTree().getRootData();
				if (leftRoot.equals("+") || leftRoot.equals("-") || leftRoot.equals("*") || leftRoot.equals("÷")) {
					left = "(" + treeToExpression(tree.getLeftTree()) + ")";
				} else {
					left = treeToExpression(tree.getLeftTree());
				}
				if (rightRoot.equals("+") || rightRoot.equals("-") || rightRoot.equals("*") || rightRoot.equals("÷")) {
					right = "(" + treeToExpression(tree.getRightTree()) + ")";
				} else {
					right = treeToExpression(tree.getRightTree());
				}
			}
			expression = left + tree.getRootData() + right;
		}
		return expression;
	}

	/**
	 * function：用于交换左右子树的函数
	 * 
	 * @param tree
	 * @return
	 */
	public BinaryTree swapChildTree(BinaryTree tree) {
		if (tree.getLeftTree() != null && tree.getRightTree() != null) {
			BinaryTree temp = tree.getLeftTree();
			tree.setLeftTree(tree.getRightTree());
			tree.setRightTree(temp);
		}
		return tree;
	}

	/**
	 * function：计算当前该二叉树所对应的表达式的值(递归计算)
	 * 
	 * @param bt
	 * @return
	 */
	public String getTreeValue(BinaryTree bt) {
		String treeValue = "";
		if (bt == null) {
			return "";
		} else {
			if (bt.getLeftTree() == null && bt.getRightTree() == null) {
				treeValue = bt.getRootData();
			} else {
				treeValue = dataOperation.calculateData(getTreeValue(bt.getLeftTree()), getTreeValue(bt.getRightTree()),
						bt.getRootData());
			}
		}
		return treeValue;
	}

}
