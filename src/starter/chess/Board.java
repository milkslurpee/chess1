package chess;

import chess.pieces.*;

public class Board implements ChessBoard{

    Piece[][] chessBoard = new Piece[8][8];
    @Override
    public void addPiece(ChessPosition position, ChessPiece piece) {
        chessBoard[position.getRow()][position.getColumn()] = (Piece) piece;
    }

    @Override
    public ChessPiece getPiece(ChessPosition position) {
        return chessBoard[position.getRow()][position.getColumn()];
    }

    @Override
    public void resetBoard() {
        chessBoard[7][0] = new Rook();
        chessBoard[7][1] = new Knight();
        chessBoard[7][2] = new Bishop();
        chessBoard[7][3] = new Queen();
        chessBoard[7][4] = new King();
        chessBoard[7][5] = new Bishop();
        chessBoard[7][6] = new Knight();
        chessBoard[7][7] = new Rook();

        for(int i = 0; i < 8; i++){
            chessBoard[6][i] = new Pawn();
        }

        for(int i = 5; i > 1; i--){
            for(int j = 0; j < 8; j++){
                chessBoard[i][j] = null;
            }
        }

        for(int i = 0; i < 8; i++){
            chessBoard[1][i] = new Pawn();
        }

        chessBoard[0][0] = new Rook();
        chessBoard[0][1] = new Knight();
        chessBoard[0][2] = new Bishop();
        chessBoard[0][3] = new Queen();
        chessBoard[0][4] = new King();
        chessBoard[0][5] = new Bishop();
        chessBoard[0][6] = new Knight();
        chessBoard[0][7] = new Rook();
    }
}
