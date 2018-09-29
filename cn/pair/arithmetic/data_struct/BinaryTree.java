/**
 * 
 */
package cn.pair.arithmetic.data_struct;

/**
 * function:二叉树数据结构
 * 
 * @author liangjing yuanzhijie
 *
 */
public class BinaryTree {

	// 节点数据
	private String rootData;
	// 左子树
	private BinaryTree leftTree;
	// 右子树
	private BinaryTree rightTree;
	// 该二叉树所对应的值
	private String treeValue;

	public String getRootData() {
		return rootData;
	}

	public void setRootData(String rootData) {
		this.rootData = rootData;
	}

	public BinaryTree getLeftTree() {
		return leftTree;
	}

	public void setLeftTree(BinaryTree leftTree) {
		this.leftTree = leftTree;
	}

	public BinaryTree getRightTree() {
		return rightTree;
	}

	public void setRightTree(BinaryTree rightTree) {
		this.rightTree = rightTree;
	}

	public String getTreeValue() {
		return treeValue;
	}

	public void setTreeValue(String treeValue) {
		this.treeValue = treeValue;
	}

	// 判断根运算符的优先级
	public int getTreePri() {
		if (this.getRootData().equals("+") || this.getRootData().equals("-"))
			return 1;
		else if (this.getRootData().equals("*") || this.getRootData().equals("÷"))
			return 2;
		else
			return -1;
	}
}
