package iutfbleau.rubikscube.model.cube.cube;

import iutfbleau.rubikscube.model.cube.face.Face3D;
import iutfbleau.rubikscube.model.cube.piece.Piece;

public class Cube3D extends Cube
{
    public Cube3D(int size) {
        super(size);

        this.faces[0] = new Face3D(size, Piece.WHITE);
        ((Face3D)this.faces[0]).setPosition(-1.0f,1.0f,1.0f,1.0f,0.05f);
        ((Face3D)this.faces[0]).rotateX(Math.toRadians(-90.0));

        this.faces[1] = new Face3D(size, Piece.GREEN);
        ((Face3D)this.faces[1]).setPosition(-1.0f,1.0f,1.0f,1.0f,0.05f);
        ((Face3D)this.faces[1]).rotateY(Math.toRadians(-90.0));

        this.faces[2] = new Face3D(size, Piece.RED);
        ((Face3D)this.faces[2]).setPosition(-1.0f,1.0f,1.0f,1.0f,0.05f);

        this.faces[3] = new Face3D(size, Piece.BLUE);
        ((Face3D)this.faces[3]).setPosition(-1.0f,1.0f,1.0f,1.0f,0.05f);
        ((Face3D)this.faces[3]).rotateY(Math.toRadians(90.0));

        this.faces[4] = new Face3D(size, Piece.ORANGE);
        ((Face3D)this.faces[4]).setPosition(-1.0f,1.0f,1.0f,1.0f,0.05f);
        ((Face3D)this.faces[4]).rotateY(Math.toRadians(180.0));

        this.faces[5] = new Face3D(size, Piece.YELLOW);
        ((Face3D)this.faces[5]).setPosition(-1.0f,1.0f,1.0f,1.0f,0.05f);
        ((Face3D)this.faces[5]).rotateX(Math.toRadians(90.0));
    }
}
