package ParticleSimulate;
import java.lang.Thread;
import java.util.Random;

public class Collide_sim {
    public static void main(String args[]){
        Ball particles[]=new Ball[60];
        int spaceWidth=1500;
        int spaceHeight=1250;
        particles=particleInitialize(particles,spaceWidth,spaceHeight);
        movement(particles);
    }
    static Ball[] particleInitialize(Ball particles[],int spaceWidth,int spaceHeight){
        Random rand=new Random();
        int xBound=1501;
        int yBound=1251;
        String colours[]={"BLUE","MAGENTA","GREEN","ORANGE","RED","YELLOW"};
        int diameters[]={20,25,30,30,35,35};

        GameArena activeSim=new GameArena(spaceWidth,spaceHeight);

        for (int i=0; i< particles.length; i++){
            int ballType= rand.nextInt(6);
            String randomColour=colours[ballType];
            int randomDiameter=diameters[ballType];
            double randomX=rand.nextInt(randomDiameter/2,xBound-(randomDiameter/2));
            double randomY=rand.nextInt(randomDiameter/2,yBound-(randomDiameter/2));
            particles[i]=new Ball(randomX,randomY,randomDiameter,randomColour,1);
            activeSim.addBall(particles[i]);
        }
        return particles;
    }
    static void movement(Ball particles[]){
        for(int i=0; i<particles.length; i++){
            Movement particle=new Movement(particles[i],particles);
            particle.start();
        }
//        for(int j=0; j<particles.length; j++){
//            CollisionHandle collisionCheck=new CollisionHandle(particles[j],particles);
//            collisionCheck.start();
//        }
    }
}
