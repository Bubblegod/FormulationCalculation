package cn.pair.airthmetic.main;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import cn.pair.arithmetic.data_struct.BinaryTree;
import cn.pair.arithmetic.util.CalculateExpression;
import cn.pair.arithmetic.util.DataOperation;
import cn.pair.arithmetic.util.ExpressionOperation;
import cn.pair.arithmetic.util.FileUtil;
import cn.pair.arithmetic.util.ExpressionGenerator;

public class Main {

	public static void main(String[] args) {

		DataOperation dataOperation = new DataOperation();
		ExpressionOperation expressionOperation = new ExpressionOperation();
		ExpressionGenerator generator = new ExpressionGenerator(100, 10);
		CalculateExpression calculateExpression = new CalculateExpression(
				dataOperation, expressionOperation, generator);
		calculateExpression.caculate();
		
		
		String formulation = "9¡Â(4/9*2/5)¡Â";
		
		if((formulation.length()>0)&&(formulation.substring(formulation.length()-1).equals("¡Â"))){
			Random random = new Random();
			int temp=random.nextInt(10-1);
			temp++;
			formulation+=Integer.toString(temp);
		}
		System.out.println(formulation);
	}

}
