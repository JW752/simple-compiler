package parseTree.nodeTypes;

import parseTree.ParseNode;
import parseTree.ParseNodeVisitor;
import lexicalAnalyzer.Lextant;
import lexicalAnalyzer.Punctuator;
import tokens.LextantToken;
import tokens.Token;

public class AssignmentNode extends OperatorNode {
	
	public AssignmentNode(Token token) {
		super(token);
		assert(token.isLextant(Punctuator.ASSIGN));
	}
	
	public AssignmentNode(ParseNode node) {
		super(node);
		initChildren();
	}
	
	public Lextant getDeclarationType() {
		return lextantToken().getLextant();
	}
	public LextantToken lextantToken() {
		return (LextantToken)token;
	}	
	
	public static AssignmentNode withChildren(Token token, ParseNode target, ParseNode expression) {
		AssignmentNode node = new AssignmentNode(token);
		node.appendChild(target);
		node.appendChild(expression);
		return node;
	}
	
	public void accept(ParseNodeVisitor visitor) {
		visitor.visitEnter(this);
		visitChildren(visitor);
		visitor.visitLeave(this);
	}
}