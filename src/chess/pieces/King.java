package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

	public King(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "K";
	}
	
	@Override
	protected boolean canMove(Position position) {
		return (getBoard().positionExists(position) && 
				(!getBoard().thereIsAPiece(position)|| isThereOpponentPiece(position)));
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		Position p = new Position(0, 0);

		// Pra cima
		p.setValues(position.getRow() - 1, position.getColumn());
		if (canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		// Pra esquerda
		p.setValues(position.getRow(), position.getColumn() - 1);
		if (canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		// Pra direita
		p.setValues(position.getRow(), position.getColumn() + 1);
		if (canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		// Pra baixo
		p.setValues(position.getRow() + 1, position.getColumn());
		if (canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// Pra cima esquerda
		p.setValues(position.getRow() - 1, position.getColumn() - 1);
		if (canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		// Pra cima direita
		p.setValues(position.getRow() - 1, position.getColumn() + 1);
		if (canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		// Pra baixo esquerda
		p.setValues(position.getRow() + 1, position.getColumn() - 1);
		if (canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		// Pra baixo direta
		p.setValues(position.getRow() + 1, position.getColumn() + 1);
		if (canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		return mat;
	}
	

}
