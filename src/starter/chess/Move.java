package chess;

public class Move implements ChessMove{

    Position startingPos = new Position();

    Position endingPos = new Position();

    @Override
    public ChessPosition getStartPosition() {
        return startingPos;
    }

    @Override
    public ChessPosition getEndPosition() {
        return endingPos;
    }

    @Override
    public ChessPiece.PieceType getPromotionPiece() {
        if(ChessPiece.PieceType == 5) {
            if (endingPos.getColumn() == 0 || endingPos.getColumn() == 7)
                ChessPiece.PieceType = 1;
        }
    }
}
