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

    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Set<ChessMove> validMoves = new HashSet<>();
        int row = myPosition.getRow();
        int column = myPosition.getColumn();

        int forwardDirection;

        if (teamColor == ChessGame.TeamColor.WHITE) {
            forwardDirection = 1;
        } else {
            forwardDirection = -1;
        }

        int newRow = row + forwardDirection;
        int newCol = column;

        if (newRow > 0 && newRow < 9 && newCol > 0 && newCol < 9) {
            ChessPosition newPosition = new Position(newRow, newCol);
            ChessPiece newPiece = board.getPiece(newPosition);

            if (newPiece == null) {
                validMoves.add(new Move(myPosition, newPosition));

                if ((row == 2 && teamColor == ChessGame.TeamColor.WHITE)
                        || (row == 7 && teamColor == ChessGame.TeamColor.BLACK)) {
                    int doubleMove = row + (forwardDirection * 2);
                    ChessPosition doublePosition = new Position(doubleMove, newCol);
                    ChessPiece doublePiece = board.getPiece(doublePosition);

                    if (doublePiece == null)
                        validMoves.add(new Move(myPosition, doublePosition));
                }
                if ((row == 1 && teamColor == ChessGame.TeamColor.BLACK)
                        || (row == 8 && teamColor == ChessGame.TeamColor.WHITE)) {
                    // Handle pawn promotion here
                    validMoves.add(new Move(myPosition, newPosition, PieceType.QUEEN)); // Promote to Queen by default
                    validMoves.add(new Move(myPosition, newPosition, PieceType.ROOK)); // Promote to Rook
                    validMoves.add(new Move(myPosition, newPosition, PieceType.BISHOP)); // Promote to Bishop
                    validMoves.add(new Move(myPosition, newPosition, PieceType.KNIGHT)); // Promote to Knight
                }
            }

            newRow = row + forwardDirection;
            newPosition = new Position(newRow, newCol);
            if (newCol == 1) {
                newPosition = new Position(newRow, newCol + 1);
                if (newPosition.getColumn() > 0 && newPosition.getColumn() < 9) {
                    newPiece = board.getPiece(newPosition);
                    if (newPiece.getTeamColor() != this.teamColor)
                        validMoves.add(new Move(myPosition, newPosition));
                }
            }
            if (newCol == 8) {
                newPosition = new Position(newRow, newCol - 1);
                if (newPosition.getColumn() > 0 && newPosition.getColumn() < 9) {
                    newPiece = board.getPiece(newPosition);
                    if (newPiece.getTeamColor() != this.teamColor)
                        validMoves.add(new Move(myPosition, newPosition));
                }
            } else {
                ChessPosition leftTakeover = new Position(newRow, newCol - 1);
                ChessPosition rightTakeover = new Position(newRow, newCol + 1);
                if (leftTakeover.getColumn() > 0 && leftTakeover.getColumn() < 9) {
                    ChessPiece leftPiece = board.getPiece(leftTakeover);
                    if (leftPiece != null && leftPiece.getTeamColor() != this.teamColor)
                        validMoves.add(new Move(myPosition, leftTakeover));
                }
                if (rightTakeover.getColumn() > 0 && rightTakeover.getColumn() < 9) {
                    ChessPiece rightPiece = board.getPiece(rightTakeover);
                    if (rightPiece != null && rightPiece.getTeamColor() != this.teamColor)
                        validMoves.add(new Move(myPosition, rightTakeover));
                }
            }
        }
        return validMoves;
    }
}
