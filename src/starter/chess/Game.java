package chess;

import java.util.Collection;

public class Game implements ChessGame{

    ChessBoard board = getBoard();

    @Override
    public TeamColor getTeamTurn() {
        return null;
    }

    @Override
    public void setTeamTurn(TeamColor team) {

    }

    @Override
    public Collection<ChessMove> validMoves(ChessPosition startPosition) {
        if(startPosition == null)
            return null;

        ChessPiece pieceType = getBoard().getPiece(startPosition);
        return pieceType.pieceMoves(board,startPosition);
    }

    @Override
    public void makeMove(ChessMove move) throws InvalidMoveException {

    }

    @Override
    public boolean isInCheck(TeamColor teamColor) {
        return false;
    }

    @Override
    public boolean isInCheckmate(TeamColor teamColor) {
        return false;
    }

    @Override
    public boolean isInStalemate(TeamColor teamColor) {
        return false;
    }

    @Override
    public void setBoard(ChessBoard board) {

    }

    @Override
    public ChessBoard getBoard() {
        return null;
    }
}
