package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

	private ChessMatch chessMatch;
	
	public King(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
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

	private boolean testRookCastling(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p != null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0;
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
		
		// movimento especial Castling
		if (getMoveCount() == 0 && !chessMatch.getCheck()) {
			// Castling pequeno
			Position posR1 = new Position(position.getRow(), position.getColumn() + 3);
			if (testRookCastling(posR1)) {
				Position p1 = new Position(position.getRow(), position.getColumn() + 1);
				Position p2 = new Position(position.getRow(), position.getColumn() + 2);
				if (getBoard().piece(p1) == null && getBoard().piece(p2) == null) {
					mat[position.getRow()][position.getColumn() + 2] = true;
				}
			}
			
			// Castling grande
			Position posR2 = new Position(position.getRow(), position.getColumn() - 4);
			if (testRookCastling(posR2)) {
				Position p1 = new Position(position.getRow(), position.getColumn() - 1);
				Position p2 = new Position(position.getRow(), position.getColumn() - 2);
				Position p3 = new Position(position.getRow(), position.getColumn() - 3);
				if (getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null) {
					mat[position.getRow()][position.getColumn() - 2] = true;
				}
			}
		}

		return mat;
	}
	

}
