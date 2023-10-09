package chess;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Pawn implements ChessPiece {

    ChessGame.TeamColor teamColor;

    public Pawn(ChessGame.TeamColor teamColor) {
        this.teamColor = teamColor;
    }

    @Override
    public ChessGame.TeamColor getTeamColor() {
        return teamColor;
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.PAWN;
    }


    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Set<ChessMove> validMoves = new HashSet<>();
        int row = myPosition.getRow();
        int column = myPosition.getColumn();

        int forwardDirection;

        if (teamColor == ChessGame.TeamColor.WHITE) {
            forwardDirection = -1;
        } else {
            forwardDirection = 1;
        }

        int newRow = row + forwardDirection;
        int newCol = column;

        if (newRow > 0 && newRow < 9 && newCol > 0 && newCol < 9) {
            ChessPosition newPosition = new Position(newRow, newCol);
            ChessPiece newPiece = board.getPiece(newPosition);

            if (newPiece == null) {
                validMoves.add(new Move(myPosition, newPosition));

                if ((newPosition.getRow() == 2 && this.getTeamColor() == ChessGame.TeamColor.WHITE)
                        || (newPosition.getRow() == 7 && this.getTeamColor() == ChessGame.TeamColor.BLACK)) {
                    newRow = row + forwardDirection * 2;
                    newPosition = new Position(newRow, newCol);
                    newPiece = board.getPiece(newPosition);

                    if (newPiece == null)
                        validMoves.add(new Move(myPosition, newPosition));
                }
            }

            newRow = row + forwardDirection;
            newPosition = new Position(newRow, newCol);
            if (newCol == 1) {
                newPosition = new Position(newRow, newCol + 1);
                newPiece = board.getPiece(newPosition);
                if (newPiece.getTeamColor() != this.teamColor)
                    validMoves.add(new Move(myPosition, newPosition));
            }
            if (newCol == 8) {
                newPosition = new Position(newRow, newCol - 1);
                newPiece = board.getPiece(newPosition);
                if (newPiece.getTeamColor() != this.teamColor)
                    validMoves.add(new Move(myPosition, newPosition));
            } else {
                ChessPosition leftTakeover = new Position(newRow, newCol - 1);
                ChessPosition rightTakeover = new Position(newRow, newCol + 1);
                ChessPiece leftPiece = board.getPiece(leftTakeover);
                ChessPiece rightPiece = board.getPiece(rightTakeover);
                if (leftPiece.getTeamColor() != this.teamColor)
                    validMoves.add(new Move(myPosition, leftTakeover));
                if (rightPiece.getTeamColor() != this.teamColor)
                    validMoves.add(new Move(myPosition, rightTakeover));
            }
        }
        return validMoves;
    }
}
