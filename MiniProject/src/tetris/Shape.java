package tetris;

import java.util.Random;

public class Shape {
	enum Tetrominoes { NoShape, ZShape, SShape, LineShape, 
        TShape, SquareShape, LShape, MirroredLShape };
        
        private Tetrominoes pieceShape;
        private int coords[][];
        private int[][][] coordsTable;
        
        public Shape() {
            coords = new int[4][2];
		}
        
        public void setShape(Tetrominoes shape) { // 모형 
        	coordsTable = new int[][][] {
                { { 0, 0 },   { 0, 0 },   { 0, 0 },   { 0, 0 } }, 
                { { 0, -1 },  { 0, 0 },   { -1, 0 },  { -1, 1 } },// z
                { { 0, -1 },  { 0, 0 },   { 1, 0 },   { 1, 1 } },//z
                { { 0, -1 },  { 0, 0 },   { 0, 1 },   { 0, 2 } }, // 짝대기 
                { { -1, 0 },  { 0, 0 },   { 1, 0 },   { 0, 1 } }, // ㅗ
                { { 0, 0 },   { 1, 0 },   { 0, 1 },   { 1, 1 } }, // 사각
                { { -1, -1 }, { 0, -1 },  { 0, 0 },   { 0, 1 } },// 」 이 모양
                { { 1, -1 },  { 0, -1 },  { 0, 0 },   { 0, 1 } } // L 모양 
            };
            
            for (int i = 0; i < 4 ; i++) {
                for (int j = 0; j < 2; ++j) {
                    coords[i][j] = coordsTable[shape.ordinal()][i][j];
                }
            }
            pieceShape = shape;
        }
        
        private void setX(int index, int x) { coords[index][0] = x; }
        private void setY(int index, int y) { coords[index][1] = y; }
        public int x(int index) { return coords[index][0]; }
        public int y(int index) { return coords[index][1]; }
        public Tetrominoes getShape()  { return pieceShape; }
        
        public void setRandomShape()
        { // 랜덤으로 블록 나오게 
            Random r = new Random();
            int x = Math.abs(r.nextInt()) % 7 + 1;
            Tetrominoes[] values = Tetrominoes.values(); 
            setShape(values[x]);
        }

        public int minX()
        { 
          int m = coords[0][0];
          for (int i=0; i < 4; i++) {
              m = Math.min(m, coords[i][0]);
          }
          return m;
        }


        public int minY() 
        {
          int m = coords[0][1];
          for (int i=0; i < 4; i++) {
              m = Math.min(m, coords[i][1]);
          }
          return m;
        }
        
        public Shape rotateLeft()  // 좌로 돌리고
        {
            if (pieceShape == Tetrominoes.SquareShape)
                return this;

            Shape result = new Shape();
            result.pieceShape = pieceShape;

            for (int i = 0; i < 4; ++i) {
                result.setX(i, y(i));
                result.setY(i, -x(i));
            }
            return result;
        }

        public Shape rotateRight() // 우로 돌리고 
        {
            if (pieceShape == Tetrominoes.SquareShape)
                return this;

            Shape result = new Shape();
            result.pieceShape = pieceShape;

            for (int i = 0; i < 4; ++i) {
                result.setX(i, -y(i));
                result.setY(i, x(i));
            }
            return result;
        }
}
