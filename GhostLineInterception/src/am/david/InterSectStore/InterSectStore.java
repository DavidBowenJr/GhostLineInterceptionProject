package am.david.InterSectStore;

import am.david.mathlib.Vector2;

public class InterSectStore {
    public Vector2 pA;
    public Vector2 pD;
    public double mA;
    public double mD;
    public Vector2 CartesianP;
    public Vector2 CartesianD;
    public Vector2 Origin;
    public Vector2 firstPoint;
    public Vector2 secondPoint;
    public Vector2 thirdPoint;
    public Vector2 pointB; // is the position where the line cross.
    public Vector2 pointBold; // justa a copy of the first instance of b

    // Note Normaly a triangle is labeled A,B,C
    // Note the first line segments  here will be  A to D (A..D)  Rather than A to C .. We will solve For B opposite and continue and also get the coefficient C who is almost always 90 degrees
    // Note with respect to the adjacent line with respect to the opposite.  thus if  B falls somewhere between  Adjacent Base line A to D
    // Note Then we will Not only calculate  the Apex but we will calculate where C falls.
    // Thus Pythagoren Theorem can be used to verify the results.
    //            A^2 + B^2 = C^2
    //            C^2 - A^2 = B^2
    //            C^2 - B^2 = A^2

    // Note The user will move the mouse position to direct a ray
    // Note When user has ray pointing in the direction just press a mouse button.
    // Note And again for the second ray direction...
    // Note
    // So A and D are our ray's that have actual origins on our screen
    public double magnitudeOfAToB;
    public double magnitudeOfDToB;

    public InterSectStore() {}
}
