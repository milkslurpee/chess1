package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Game implements ChessGame{

    private TeamColor teamTurn;

    ChessBoard board = getBoard();

    @Override
    public TeamColor getTeamTurn() {
        return teamTurn;
    }

    @Override
    public void setTeamTurn(TeamColor team) {
        if(team == TeamColor.WHITE)
            teamTurn = TeamColor.BLACK;
        else
            teamTurn = TeamColor.WHITE;
    }

    @Override
    public Collection<ChessMove> validMoves(ChessPosition startPosition) {
        TeamColor currentTeamColor = getTeamTurn();
        if (board.getPiece(startPosition) == null) {
            return null;
        }
        Set<ChessMove> moves;
        ChessPiece piece = board.getPiece(startPosition);
        Collection<ChessMove> potentialMoves = piece.pieceMoves(board, startPosition);
        Set<ChessMove> validMoves = new HashSet<>();

        for (ChessMove move : potentialMoves) {
            // Temporarily make the move
            ChessPiece originalPiece = board.getPiece(move.getEndPosition());
            board.addPiece(move.getEndPosition(), board.getPiece(move.getStartPosition()));
            board.addPiece(move.getStartPosition(), null);

            // Check if the king is not in check after the move
            if (!isInCheck(currentTeamColor)) {
                validMoves.add(move);
            }

            // Undo the move
            board.addPiece(move.getStartPosition(), board.getPiece(move.getEndPosition()));
            board.addPiece(move.getEndPosition(), originalPiece);
        }
        return validMoves;
    }

    @Override
    public void makeMove(ChessMove move) throws InvalidMoveException {
        // Check if the move is valid
        Collection<ChessMove> validMoves = validMoves(move.getStartPosition());

        if (!validMoves.contains(move)) {
            throw new InvalidMoveException("Invalid move");
        }

        // Check if it's the correct team's turn
        if (getTeamTurn() != board.getPiece(move.getStartPosition()).getTeamColor()) {
            throw new InvalidMoveException("Not the correct team's turn");
        }

        // Temporarily make the move
        ChessPiece originalPiece = board.getPiece(move.getEndPosition());
        board.addPiece(move.getEndPosition(), board.getPiece(move.getStartPosition()));
        board.addPiece(move.getStartPosition(), null);

        // Check if the move doesn't put the current team's king in check
        if (isInCheck(getTeamTurn())) {
            // Undo the move if it puts the king in check
            board.addPiece(move.getStartPosition(), board.getPiece(move.getEndPosition()));
            board.addPiece(move.getEndPosition(), originalPiece);
            throw new InvalidMoveException("Move puts the king in check");
        }

        // Update the team's turn
        setTeamTurn(getTeamTurn());

        // Check for pawn promotion
        if (move.getPromotionPiece() != null) {
            // Implement the logic for pawn promotion
            // You can check the destination row of the move, and if it's the promotion row, replace the piece with the promoted one.
        }
    }

    public Set<ChessPosition> enemyPieces() {
        Set<ChessPosition> enemyPieceLocations = new HashSet<>();
        TeamColor currentTeamColor = getTeamTurn();

        // Iterate through the entire board
        for (int row = 1; row <= 8; row++) {
            for (int col = 1; col <= 8; col++) {
                ChessPosition currentPosition = new Position(row, col);
                ChessPiece piece = board.getPiece(currentPosition);

                if (piece != null && piece.getTeamColor() != currentTeamColor) {
                    enemyPieceLocations.add(currentPosition);
                }
            }
        }

        return enemyPieceLocations;
    }

    public ChessPosition alliedKingPos() {
        TeamColor currentTeamColor = getTeamTurn();

        // Iterate through the entire board
        for (int row = 1; row <= 8; row++) {
            for (int col = 1; col <= 8; col++) {
                ChessPosition currentPosition = new Position(row, col);
                ChessPiece piece = board.getPiece(currentPosition);

                if (piece != null && piece.getTeamColor() == currentTeamColor
                        && piece.getPieceType() == ChessPiece.PieceType.KING) {
                    return currentPosition; // Found the allied King
                }
            }
        }

        return null; // Allied King not found (should not happen in a legal game)
    }

    public Set<ChessPosition> alliedPieces() {
        Set<ChessPosition> alliedPieceLocations = new HashSet<>();
        TeamColor currentTeamColor = getTeamTurn();

        // Iterate through the entire board
        for (int row = 1; row <= 8; row++) {
            for (int col = 1; col <= 8; col++) {
                ChessPosition currentPosition = new Position(row, col);
                ChessPiece piece = board.getPiece(currentPosition);

                if (piece != null && piece.getTeamColor() == currentTeamColor) {
                    alliedPieceLocations.add(currentPosition);
                }
            }
        }

        return alliedPieceLocations;
    }

    @Override
    public boolean isInCheck(TeamColor teamColor) {
        ChessPosition kingPosition = alliedKingPos();
        if (kingPosition == null) {
            return false; // King not found
        }

        TeamColor opponentTeam = (teamColor == TeamColor.WHITE) ? TeamColor.BLACK : TeamColor.WHITE;
        Collection<ChessPosition> enemyPieceLocations = enemyPieces();

        for (ChessPosition enemyPosition : enemyPieceLocations) {
            ChessPiece enemyPiece = getBoard().getPiece(enemyPosition);
            Collection<ChessMove> enemyMoves = enemyPiece.pieceMoves(getBoard(), enemyPosition);

            for (ChessMove enemyMove : enemyMoves) {
                if (enemyMove.getEndPosition().equals(kingPosition)) {
                    return true; // The King is in check
                }
            }
        }

        return false; // The King is not in check
    }

    @Override
    public boolean isInCheckmate(TeamColor teamColor) {
        // Find the position of the King of the specified team
        ChessPosition kingPosition = alliedKingPos();

        // If the King is not found, return false (not in checkmate)
        if (kingPosition == null) {
            return false;
        }

        // If the King is not in check, it's not in checkmate
        if (!isInCheck(teamColor)) {
            return false;
        }

        // Get all possible moves for allied pieces
        Collection<ChessPosition> alliedPieceLocations = alliedPieces();

        // Check each possible move to see if it removes the check
        for (ChessPosition fromPosition : alliedPieceLocations) {
            for (ChessMove move : validMoves(fromPosition)) {
                // Temporarily make the move
                ChessPiece originalPiece = board.getPiece(move.getEndPosition());
                board.addPiece(move.getEndPosition(), board.getPiece(fromPosition));
                board.addPiece(fromPosition, null);

                // Check if the King is still in check
                if (!isInCheck(teamColor)) {
                    // The King is no longer in check, so it's not checkmate
                    // Undo the move
                    board.addPiece(fromPosition, board.getPiece(move.getEndPosition()));
                    board.addPiece(move.getEndPosition(), originalPiece);
                    return false;
                }

                // Undo the move
                board.addPiece(fromPosition, board.getPiece(move.getEndPosition()));
                board.addPiece(move.getEndPosition(), originalPiece);
            }
        }

        // If no valid move can remove the check, it's checkmate
        return true;
    }
    @Override
    public boolean isInStalemate(TeamColor teamColor) {
        return false;
    }

    @Override
    public void setBoard(ChessBoard board) {
        board.resetBoard();
    }

    @Override
    public ChessBoard getBoard() {
        return board;
    }
}
