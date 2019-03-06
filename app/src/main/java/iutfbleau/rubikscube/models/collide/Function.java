package iutfbleau.rubikscube.models.collide;

public class Function
{
    protected float a;
    protected float b;
    public Function(float a, float b)
    {
        this.a = a;
        this.b = b;
    }

    public float f(float x)
    {
        return this.a * x + this.b;
    }

    public boolean isOver(float x, float y)
    {
        double realY = this.f(x);
        if(realY < y)
            return true;
        return false;
    }

    public boolean isUnder(float x, float y)
    {
        double realY = this.f(x);
        if(realY >= y)
            return true;
        return false;
    }
}

