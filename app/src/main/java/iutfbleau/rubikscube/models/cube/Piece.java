package iutfbleau.rubikscube.models.cube;

/**
 * Represente une piece d'une des faces du Rubik's Cube
 *  
 * @author Rabah DJADI.
 */
public class Piece 
{
	/**
     * couleur de la piece
     */
	protected int color;
	
	/**
     * les differentes valeurs que peut prendre color
     */
	public static final int WHITE  = 0;
	public static final int GREEN  = 1;
	public static final int RED    = 2;
	public static final int BLUE   = 3;
	public static final int ORANGE = 4;
	public static final int YELLOW = 5;
	public static final int NONE   = 6;
	
	/**
     * Construit une piece sans couleur
     */
	public Piece()
	{
		this.setColor(Piece.NONE);
	}

	public Piece(Piece piece)
	{
		this.color = piece.color;
	}
	
	/**
     * Construit une piece avec une certaine couleur
     * 
     *  @param color la couleur de la piece
     */
	public Piece(int color)
	{
		this.setColor(color);
	}
	
	/**
     * Fixe la couleur de la piece
     * 
     *  @param color la couleur de la piece
     *  si color ne correspond a aucune des couleurs alors la piece est sans couleur
     */
	public void setColor(int color)
	{
		if ((color < 0) || (color > 5))
			this.color = Piece.NONE;
		else
			this.color = color;
	}
	
	/**
     * Renvoie la couleur de la piece sous forme d'entier
     * l'entier renvoye est un entier compris entre 0 et 6 inclus
     * 
     * @return la couleur de la piece
     */
	public int getColor()
	{
		return this.color;
	}
	
	/**
     * Renvoie la couleur de la piece sous forme de chaine de carateres
     * 
     * @return la couleur de la piece sous forme de String
     * @see Object
     */
	@Override
	public String toString()
	{
		return this.color + "";
	}
}
