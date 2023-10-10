package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Rook implements ChessPiece {
    ChessGame.TeamColor teamColor;
    public Rook(ChessGame.TeamColor teamColor){
        this.teamColor = teamColor;
    }

    @Override
    public ChessGame.TeamColor getTeamColor() {
        return teamColor;
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.ROOK;
    }

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Set<ChessMove> validMoves= new HashSet<>();
        int row = myPosition.getRow();
        int column = myPosition.getColumn();

        int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};

        for(int[] direction : directions){
            int newRow = row + direction[0];
            int newCol = column + direction[1];

            while(newRow > 0 && newRow < 9 && newCol > 0 && newCol < 9){
                ChessPosition newPosition = new Position(newRow,newCol);
                ChessPiece newPiece = board.getPiece(newPosition);

                if(newPiece == null || newPiece.getTeamColor() != this.teamColor){
                    validMoves.add(new Move(myPosition,newPosition));
                }

                if(newPiece != null){
                    break;
                }
                newRow += direction[0];
                newCol += direction[1];
            }
        }
        return validMoves;
    }
}
//
//package chess;
//
//        import java.util.Collection;
//        import java.util.HashSet;
//        import java.util.Set;
//
//public class Rook implements ChessPiece {
//    ChessGame.TeamColor teamColor;
//
//    public Rook(ChessGame.TeamColor teamColor) {
//        this.teamColor = teamColor;
//    }
//
//    @Override
//    public ChessGame.TeamColor getTeamColor() {
//        return teamColor;
//    }
//
//    @Override
//    public PieceType getPieceType() {
//        return PieceType.ROOK;
//    }
//
//    @Override
//    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
//        int row = myPosition.getRow();
//        int col = myPosition.getColumn();
//        int rowAdd = myPosition.getRow() + 1;
//        int rowSub = myPosition.getRow() - 1;
//        int colAdd = myPosition.getColumn() + 1;
//        int colSub = myPosition.getColumn() - 1;
//        Set<ChessMove> validMoves = new HashSet<>();
//        for(int i = 1; i < 9; i++) {
//            if (rowAdd < 9) {
//                ChessPosition newPosition = new Position(rowAdd, col);
//                if (board.getPiece(newPosition) == null) {
//                    ChessMove move = new Move(myPosition, newPosition, null);
//                    validMoves.add(move);
//                } else {
//                    if (board.getPiece(newPosition).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
//                        ChessMove move = new Move(myPosition, newPosition, null);
//                        validMoves.add(move);
//                        break;
//                    }else{
//                        break;
//                    }
//                }
//                rowAdd += 1;
//            }
//        }
//
//        for(int k = 1; k < 9; k++) {
//            if (rowSub > 0) {
//                ChessPosition newPosition2 = new Position(rowSub, col);
//                if (board.getPiece(newPosition2) == null) {
//                    ChessMove move = new Move(myPosition, newPosition2, null);
//                    validMoves.add(move);
//                } else {
//                    if (board.getPiece(newPosition2).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
//                        ChessMove move = new Move(myPosition, newPosition2, null);
//                        validMoves.add(move);
//                        break;
//                    }else {
//                        break;
//                    }
//                }
//                rowSub -= 1;
//            }
//        }
//        for(int i = 1; i < 9; i++) {
//            if (colAdd < 9) {
//                ChessPosition newPosition3 = new Position(row, colAdd);
//                if (board.getPiece(newPosition3) == null) {
//                    ChessMove move = new Move(myPosition, newPosition3, null);
//                    validMoves.add(move);
//                } else {
//                    if (board.getPiece(newPosition3).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
//                        ChessMove move = new Move(myPosition, newPosition3, null);
//                        validMoves.add(move);
//                        break;
//                    }else{
//                        break;
//                    }
//                }
//                colAdd += 1;
//            }
//        }
//        for(int i = 1; i < 9; i++){
//            if (colSub > 0) {
//                ChessPosition newPosition4 = new Position(row, colSub);
//                if (board.getPiece(newPosition4) == null) {
//                    ChessMove move = new Move(myPosition, newPosition4, null);
//                    validMoves.add(move);
//                } else {
//                    if (board.getPiece(newPosition4).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
//                        ChessMove move = new Move(myPosition, newPosition4, null);
//                        validMoves.add(move);
//                        break;
//                    }else{
//                        System.out.println();
//                        break;
//                    }
//                }
//                colSub -= 1;
//            }
//        }
//        return validMoves;
//    }
//}