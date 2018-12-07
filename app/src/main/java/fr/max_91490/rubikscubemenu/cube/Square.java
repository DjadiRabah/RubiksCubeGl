package fr.max_91490.rubikscubemenu.cube;


import fr.max_91490.rubikscubemenu.rotation.*;

/**
 * Représente une des faces du Rubik's Cube
 *
 * @author Rabah DJADI.
 */
public class Square
{
    /**
     * le nombre de pièce sur une ligne/colonne de la face
     */
    protected int size;

    /**
     * les pièces constituants la face
     */
    protected Piece[][] pieces;

    /**
     * Construit une face du cube de taille size*size sans couleur
     *
     * @param size le nombre de pièces en ligne et en colonne de la face du cube
     */
    public Square(int size)
    {
        this.size = size;
        this.pieces = new Piece[this.size][this.size];
        for(int i = 0; i < this.pieces.length; i++)
        {
            for(int j = 0; j < this.pieces[i].length; j++)
            {
                this.pieces[i][j] = new Piece(Piece.NONE);
            }
        }
    }

    /**
     * Construit une face du cube de taille size*size et de couleur color
     *
     * @param size le nombre de pièces en ligne et en colonne de la face du cube
     * @param color la couleur de toutes les pièces de la face du cube
     */
    public Square(int size, int color)
    {
        this.size = size;
        this.pieces = new Piece[this.size][this.size];
        for(int i = 0; i < this.pieces.length; i++)
        {
            for(int j = 0; j < this.pieces[i].length; j++)
            {
                if((color < Piece.WHITE) || (color > Piece.YELLOW))
                {
                    this.pieces[i][j] = new Piece(Piece.NONE);
                }
                else
                {
                    this.pieces[i][j] = new Piece(color);
                }
            }
        }
    }

    /**
     * Construit une face du cube à partir d'une face déjà existante
     *
     * @param square une face du cube à copier
     */
    public Square(Square square)
    {
        this.size = square.getSize();
        this.pieces = new Piece[this.size][this.size];
        for(int i = 0; i < this.pieces.length; i++)
        {
            for(int j = 0; j < this.pieces[i].length; j++)
            {
                this.pieces[i][j] = square.pieces[i][j];
            }
        }
    }


    /**
     * Renvoie une matrice des pièces de la face du cube
     *
     * @return la face du cube sous la forme d'une matrice de Piece
     */
    public Piece[][] getPieces()
    {
        Piece[][] square = new Piece[this.size][this.size];
        for(int i = 0; i < this.size; i++)
        {
            for(int j = 0; j < this.size; j++)
            {
                square[i][j] = this.pieces[i][j];
            }
        }
        return square;
    }

    /**
     * Renvoie une matrice des couleurs des pièces de la face du cube
     *
     * @return une matrice d'entiers représentants aux couleurs des pièces de la face du cube
     */
    public int[][] getColors()
    {
        int[][] colors = new int[this.size][this.size];
        for(int i = 0; i < this.size; i++)
        {
            for(int j = 0; j < this.size; j++)
            {
                colors[i][j] = this.pieces[i][j].getColor();
            }
        }
        return colors;
    }

    /**
     * Renvoie le nombre de pièces sur une ligne/colonne de la face du cube
     *
     * @return un entier correspondant à la taille de la face du cube
     */
    public int getSize()
    {
        return this.size;
    }

    /**
     * Fixe la ligne d'indice row à partir d'un vecteur de Piece pieces
     *
     * @param row l'indice de la ligne fixée
     * @param pieces les nouvelles pièces de la ligne d'indice row
     */
    public void setRow(int row, Piece[] pieces)
    {
        for(int i = 0; i < this.size; i++)
        {
            this.pieces[row][i] = pieces[i];
        }
    }

    /**
     * Fixe la ligne line de pièces à partir d'un vecteur de Piece pieces en partant de la fin
     *
     * @param row l'indice de la ligne fixée
     * @param pieces les nouvelles pièces de la ligne d'indice row
     */
    public void setRowReverse(int row, Piece[] pieces)
    {
        for(int i = 0; i < this.size; i++)
        {
            this.pieces[row][i] = pieces[this.size - 1 - i];
        }
    }

    /**
     * Fixe la face du cube en entier à partir d'une matrice de Piece
     *
     * @param square la matrice de Piece à copier
     */
    public void setSquare(Piece[][] square)
    {
        for(int i = 0; i < this.size; i++)
        {
            for(int j = 0; j < this.size; j++)
            {
                this.pieces[i][j] = square[i][j];
            }
        }
    }

    /**
     * Renvoie la ligne d'indice row sous forme de vecteur de Piece
     */
    public Piece[] getRow(int row)
    {
        Piece[] pieces = new Piece[this.size];
        for(int currentPiece = 0; currentPiece < this.size; currentPiece++)
        {
            pieces[currentPiece] = this.pieces[row][currentPiece];
        }
        return pieces;
    }

    /**
     * Fixe la colonne d'indice col à partir d'un vecteur de Piece pieces
     *
     * @param col l'indice de la colonne fixée
     * @param pieces les nouvelles pièces de la colonne d'indice col
     */
    public void setCol(int col,Piece[] pieces)
    {
        for(int i = 0; i < this.size; i++)
        {
            this.pieces[i][col] = pieces[i];
        }
    }

    /**
     * Fixe la colonne d'indice col à partir d'un vecteur de Piece pieces en partant de la fin
     *
     * @param col l'indice de la colonne fixée
     * @param pieces les nouvelles pièces de la colonne d'indice col
     */
    public void setColReverse(int col,Piece[] pieces)
    {
        for(int i = 0; i < this.size; i++)
        {
            this.pieces[i][col] = pieces[this.size - 1 - i];
        }
    }

    /**
     * Renvoie la colonne d'indice col sous forme de vecteur de Piece
     *
     * @param col indice de la colonne de la face du cube
     * @return un vecteur de Piece correspondant à la colonne d'indice col de la face du cube
     */
    public Piece[] getCol(int col)
    {
        Piece[] pieces = new Piece[this.size];
        for(int i = 0; i < this.size; i++)
        {
            pieces[i] = this.pieces[i][col];
        }
        return pieces;
    }

    /**
     * Renvoie une chaine de caractères représentant les couleurs des pièces de la ligne d'indice row de la face du cube
     *
     * @param row indice de la ligne de la face du cube
     * @return  une chaine de caractères représentant les couleurs des pièces de la ligne d'indice row de la face du cube
     */
    public String toStringRow(int row)
    {
        String str = "";
        for(int i = 0; i < this.size; i++)
        {
            str = str + this.pieces[row][i] + " ";
        }
        return str;
    }

    /**
     * Renvoie une chaine de caractères sous forme de matrice représentant la couleur des pièces de la face du cube
     *
     * @param row indice de la ligne de la face du cube
     * @return  Renvoie une chaine de caractères sous forme de matrice représentant la couleur des pièces de la face du cube
     */
    public String toString()
    {
        String str = "";
        for(int i = 0; i < this.size; i++)
        {
            str = str + this.toStringRow(i) + '\n';
        }
        return str;
    }

    /**
     * Renvoie un booléen permettant de savoir si la face est résolue ou non
     *
     * @return un booléen valant true si la face est résolue et false sinon
     */
    public boolean isSolved()
    {
        int color = this.pieces[0][0].getColor();
        for(int i = 0; i < this.size; i++)
        {
            for(int j = 0; j < this.size; j++)
            {
                if(this.pieces[i][j].getColor() != color)
                {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Tourne la face dans le sens horaire ou anti-horaire
     *
     * @param direction doit valoir Rotation.CLOCKWISE ou Rotation.COUNTERCLOCKWISE
     */
    public void rotate(int direction)
    {
        if((direction == Rotation.CLOCKWISE) || (direction == Rotation.COUNTERCLOCKWISE))
        {
            new RotationSquare().rotate(this,direction);
        }
    }
}