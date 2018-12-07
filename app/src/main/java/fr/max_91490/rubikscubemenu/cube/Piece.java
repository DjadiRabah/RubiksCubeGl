package fr.max_91490.rubikscubemenu.cube;

/**
 * Représente une pièce d'une des faces du Rubik's Cube
 *
 * @author Rabah DJADI.
 */
public class Piece
{
    /**
     * couleur de la pièce
     */
    protected int color;

    /**
     * les différentes valeurs que peut prendre color
     */
    public static final int WHITE  = 0;
    public static final int GREEN  = 1;
    public static final int RED    = 2;
    public static final int BLUE   = 3;
    public static final int ORANGE = 4;
    public static final int YELLOW = 5;
    public static final int NONE   = 6;

    /**
     * Construit une pièce sans couleur
     */
    public Piece()
    {
        this.setColor(Piece.NONE);
    }

    /**
     * Construit une pièce avec une certaine couleur
     *
     *  @param color la couleur de la pièce
     */
    public Piece(int color)
    {
        this.setColor(color);
    }

    /**
     * Fixe la couleur de la pièce
     *
     *  @param color la couleur de la pièce
     *  si color ne correspond à aucune des couleurs alors la pièce est sans couleur
     */
    public void setColor(int color)
    {
        if ((color < 0) || (color > 5))
            this.color = Piece.NONE;
        else
            this.color = color;
    }

    /**
     * Renvoie la couleur de la pièce sous forme d'entier
     * l'entier renvoyé est un entier compris entre 0 et 6 inclus
     *
     * @return la couleur de la pièce
     */
    public int getColor()
    {
        return this.color;
    }

    /**
     * Renvoie la couleur de la pièce sous forme de chaine de caratères
     *
     * @return la couleur de la pièce sous forme de String
     * @see Object
     */
    @Override
    public String toString()
    {
        return this.color + "";
    }
}