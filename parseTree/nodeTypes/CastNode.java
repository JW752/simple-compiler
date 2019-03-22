package parseTree.nodeTypes;

import parseTree.ParseNode;
import parseTree.ParseNodeVisitor;
import lexicalAnalyzer.Lextant;
import lexicalAnalyzer.Punctuator;
import tokens.LextantToken;
import tokens.Token;
import semanticAnalyzer.signatures.FunctionSignature;
import semanticAnalyzer.types.Type;

public class CastNode extends ParseNode {
	protected Type castType;
	protected FunctionSignature signature;
	
	public CastNode(Token token) {
		super(token);
		assert(token.isLextant(Punctuator.PIPE));
	}

	public CastNode(ParseNode node) {
		super(node);
	}
	
	public Lextant getOperator() {
		return lextantToken().getLextant();
	}
	public LextantToken lextantToken() {
		return (LextantToken)token;
	}

	public static CastNode withChildren(Token token, ParseNode left, Type right) {
		CastNode node = new CastNode(token);
		node.appendChild(left);
		node.castType = right;
		return node;
	}

	public Type getExpressionType() {
		return this.child(0).getType();
	}
	public Type getCastType() {
		return this.castType;
	}
		
	public void accept(ParseNodeVisitor visitor) {
		visitor.visitEnter(this);
		visitChildren(visitor);
		visitor.visitLeave(this);
	}
	
	public void setSignature(FunctionSignature signature) {
		this.signature = signature;
	}
	public FunctionSignature getSignature() {
		return signature;
	}
}