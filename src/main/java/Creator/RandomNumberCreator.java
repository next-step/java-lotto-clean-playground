package Creator;

import static java.lang.Math.random;
public class RandomNumberCreator implements NumberCreator{
    public int returnNumber(){
        return (int)(random()*45) + 1;
    }
}
