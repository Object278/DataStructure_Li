package com.imooc.stack;
import java.util.Stack;
public class ValidParentheses {
	public boolean isValid(String s) {
		Stack<Character> stack=new Stack<>();
		for(int i=0;i<s.length();i++) {
			char c=s.charAt(i);
			if(c=='(' || c=='[' || c=='{') {
				stack.push(c);
			}else {
				char topchar=stack.pop();
				if(c==')' && topchar!='(') {
					return false;
				}
				if(c==']' && topchar!='[') {
					return false;
				}
				if(c=='}' && topchar!='{') {
					return false;
				}
			}
		}
		return stack.isEmpty();
	}

	public static void main(String[] args) {
		 ValidParentheses vp=new ValidParentheses();
		System.out.println(vp.isValid("(){}{}[]{{}"));
		System.out.println(vp.isValid("(){}{}[]{}"));
	}

}
