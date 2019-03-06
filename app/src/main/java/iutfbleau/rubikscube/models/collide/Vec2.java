package iutfbleau.rubikscube.models.collide;


/**
 * Calculates the transform from screen coordinate
 * system to world coordinate system coordinates
 * for a specific point, given a camera position.
 *
 * @param touch Vec2 point of screen touch, the
actual position on physical screen (ej: 160, 240)
 * @param cam camera object with x,y,z of the
camera and screenWidth and screenHeight of
the device.
 * @return position in WCS.
 */
public Vec2 GetWorldCoords( Vec2 touch, Camera cam)
        {
        // Initialize auxiliary variables.
        Vec2 worldPos = new Vec2();

        // SCREEN height & width (ej: 320 x 480)
        float screenW = cam.GetScreenWidth();
        float screenH = cam.GetScreenHeight();

        // Auxiliary matrix and vectors
        // to deal with ogl.
        float[] invertedMatrix, transformMatrix,
        normalizedInPoint, outPoint;
        invertedMatrix = new float[16];
        transformMatrix = new float[16];
        normalizedInPoint = new float[4];
        outPoint = new float[4];

        // Invert y coordinate, as android uses
        // top-left, and ogl bottom-left.
        int oglTouchY = (int) (screenH - touch.Y());

       /* Transform the screen point to clip
       space in ogl (-1,1) */
        normalizedInPoint[0] =
        (float) ((touch.X()) * 2.0f / screenW - 1.0);
        normalizedInPoint[1] =
        (float) ((oglTouchY) * 2.0f / screenH - 1.0);
        normalizedInPoint[2] = - 1.0f;
        normalizedInPoint[3] = 1.0f;

       /* Obtain the transform matrix and
       then the inverse. */
        Print("Proj", getCurrentProjection(gl));
        Print("Model", getCurrentModelView(gl));
        Matrix.multiplyMM(
        transformMatrix, 0,
        getCurrentProjection(gl), 0,
        getCurrentModelView(gl), 0);
        Matrix.invertM(invertedMatrix, 0,
        transformMatrix, 0);

       /* Apply the inverse to the point
       in clip space */
        Matrix.multiplyMV(
        outPoint, 0,
        invertedMatrix, 0,
        normalizedInPoint, 0);

        if (outPoint[3] == 0.0)
        {
        // Avoid /0 error.
        Log.e("World coords", "ERROR!");
        return worldPos;
        }

        // Divide by the 3rd component to find
        // out the real position.
        worldPos.Set(
        outPoint[0] / outPoint[3],
        outPoint[1] / outPoint[3]);

        return worldPos;
        }