package com.szalmas.com.szalmas.maven.eclipse.LibraryHandler;

import java.util.Arrays;
import java.util.List;

import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

	public class myMethodVisitor extends VoidVisitorAdapter {
	
		@Override
		public void visit(MethodCallExpr methodCall,Object arg) {
			
			System.out.println("Method Call: "+ methodCall.getNameAsString() + "\n");
			List<Expression> args = methodCall.getArguments();
			if(args != null) {
				handleExpressions(args);
			}
		}

		private void handleExpressions(List<Expression> expressions) {
		
			for (Expression expr : expressions)
            {
                if (expr instanceof MethodCallExpr)
                    visit((MethodCallExpr) expr, null);
                else if (expr instanceof BinaryExpr)
                {
                    BinaryExpr binExpr = (BinaryExpr)expr;
                    handleExpressions(Arrays.asList(binExpr.getLeft(), binExpr.getRight()));
                }
            }
        }
    

			
		}

