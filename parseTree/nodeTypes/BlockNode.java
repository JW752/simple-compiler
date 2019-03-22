package parseTree.nodeTypes;

import parseTree.ParseNode;
import parseTree.ParseNodeVisitor;
import tokens.Token;

public class BlockNode extends MainBlockNode {

	public BlockNode(Token token) {
		super(token);
	}
	public BlockNode(ParseNode node) {
		super(node);
	}
	public void accept(ParseNodeVisitor visitor) {
		visitor.visitEnter(this);
		visitChildren(visitor);
		visitor.visitLeave(this);
	}
}