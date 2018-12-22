package iutfbleau.rubikscube.model.cube.cube;

import iutfbleau.rubikscube.model.cube.face.Face3D;

public class Cube3D extends Cube
{

    public Cube3D(int size) {
        super(size);

        this.faces[0] = new Face3D(size);
        ((Face3D)this.faces[0]).setPosition(-1.0f,1.0f,1.0f,1.0f,0.05f);

        this.faces[1] = new Face3D(size);
        ((Face3D)this.faces[1]).setPosition(-1.0f,1.0f,1.0f,1.0f,0.05f);

        this.faces[2] = new Face3D(size);
        ((Face3D)this.faces[2]).setPosition(-1.0f,1.0f,1.0f,1.0f,0.05f);

        this.faces[3] = new Face3D(size);
        ((Face3D)this.faces[3]).setPosition(-1.0f,1.0f,1.0f,1.0f,0.05f);

        this.faces[4] = new Face3D(size);
        ((Face3D)this.faces[4]).setPosition(-1.0f,1.0f,1.0f,1.0f,0.05f);

        this.faces[5] = new Face3D(size);
        ((Face3D)this.faces[5]).setPosition(-1.0f,1.0f,1.0f,1.0f,0.05f);
    }
}
