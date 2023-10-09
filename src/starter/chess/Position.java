package chess;

public class Position implements ChessPosition{

    int row;
    int column;

    public Position(Integer row, Integer col) {
        this.row = row;
        this.column = col;
    }
    @Override
    public int getRow() {
        return row;
    }

    @Override
    public int getColumn() {
        return column;
    }
}
