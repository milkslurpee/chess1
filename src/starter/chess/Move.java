package chess;

public class Move implements ChessMove{

    ChessPosition startingPos;

    ChessPosition endingPos;

    ChessPiece.PieceType promotionPiece;

    public Move(ChessPosition startingPos , ChessPosition endingPos){
        this.startingPos = startingPos;
        this.endingPos = endingPos;
    }

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
        return promotionPiece;
    }
}
