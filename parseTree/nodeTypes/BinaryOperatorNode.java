package parseTree.nodeTypes;

import parseTree.ParseNode;
import parseTree.ParseNodeVisitor;
import lexicalAnalyzer.Lextant;
import lexicalAnalyzer.Punctuator;
import tokens.LextantToken;
import tokens.Token;
import semanticAnalyzer.signatures.FunctionSignature;

public class BinaryOperatorNode extends OperatorNode {
	
	private FunctionSignature signature = FunctionSignature.nullInstance();
	
	public BinaryOperatorNode(Token token) {
		super(token);
		assert(token instanceof LextantToken);
	}

	public BinaryOperatorNode(ParseNode node) {
		super(node);
	}
	
	
	////////////////////////////////////////////////////////////
	// attributes
	
	public Lextant getOperator() {
		return lextantToken().getLextant();
	}
	public LextantToken lextantToken() {
		return (LextantToken)token;
	}

	
	////////////////////////////////////////////////////////////
	// convenience factory
	
	public static BinaryOperatorNode withChildren(Token token, ParseNode left, ParseNode right) {
		BinaryOperatorNode node = new BinaryOperatorNode(token);
		node.appendChild(left);
		node.appendChild(right);
		return node;
	}
	
	public boolean isComparator() {
		Lextant operator = getOperator();
		return (operator == Punctuator.LESSEREQUAL || operator == Punctuator.LESSER ||
				operator == Punctuator.EQUAL || operator == Punctuator.NOT_EQUAL ||
				operator == Punctuator.GREATER || operator == Punctuator.GREATEREQUAL);
	}
	
	public boolean isBooleanOperator() {
		Lextant operator = getOperator();
		return (operator == Punctuator.AND || operator == Punctuator.OR);
	}
	
	///////////////////////////////////////////////////////////
	// boilerplate for visitors
			
	public void accept(ParseNodeVisitor visitor) {
		visitor.visitEnter(this);
		visitChildren(visitor);
		visitor.visitLeave(this);
	}
	
	public final FunctionSignature getSignature() {
		return signature;
	}
	public final void setSignature(FunctionSignature signature) {
		this.signature = signature;
	}
}
