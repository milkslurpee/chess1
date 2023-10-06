package chess;

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

    }
}
