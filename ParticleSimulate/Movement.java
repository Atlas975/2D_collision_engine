package ParticleSimulate;

import java.util.Random;

import static java.lang.Math.abs;

public class Movement extends Thread {
    private Ball particle;
    private Ball particles[];

    public Movement(Ball particle,Ball particles[]){
        this.particle=particle;
        this.particles=particles;
    }

    @Override
    public void run(){
        int baseSleep=3;
        double leftBoundry=0+(particle.getSize()/2);
        double topBoundry=0+(particle.getSize()/2);
        double rightBoundry=1501-(particle.getSize()/2);
        double bottomBoundry=1251-(particle.getSize()/2);
        int movementArray[]= new int[2];
        if(Math.random() < 0.5) {
            movementArray[0]=1;
        }
        else{
            movementArray[0]=-1;
        }
        if(Math.random() < 0.5) {
            movementArray[1]=1;
        }
        else{
            movementArray[1]=-1;
        }
        while(true) {
            particle.setXPosition(particle.getXPosition()+movementArray[0]);
            particle.setYPosition(particle.getYPosition()+movementArray[1]);
            Random rand=new Random();
            try {
                Thread.sleep(baseSleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            movementArray=checkCollide(particle,particles,movementArray);



            if(particle.getXPosition()<=leftBoundry || particle.getXPosition()>=rightBoundry){
                baseSleep=rand.nextInt(3,6);
                movementArray[0]=-movementArray[0];

            }
            if(particle.getYPosition()<=topBoundry || particle.getYPosition()>=bottomBoundry){
                baseSleep=rand.nextInt(3,6);
                movementArray[1]=-movementArray[1];
            }


        }
    }

    public int[] checkCollide(Ball particle,Ball particles[],int movementArray[]){
        Ball compareCollide[]=new Ball[particles.length-1];
        int count=0;
        for(int i=0; i<particles.length; i++){
            if(particles[i]!=particle){
                compareCollide[count]=particles[i];
                count++;
            }
        }

        for (int i = 0; i < compareCollide.length; i++) {
            double collide;
            if (particle.getSize() > compareCollide[i].getSize()) {
                collide = particle.getSize()/2;
            } else {
                collide = compareCollide[i].getSize()/2;
            }
            Boolean xCollide=abs(particle.getXPosition() - compareCollide[i].getXPosition()) <= collide;
            Boolean yCollide=abs(particle.getYPosition() - compareCollide[i].getYPosition()) <= collide;
            if (xCollide & yCollide) {
                if(xCollide){
                    movementArray[0]=-movementArray[0];
                }
                if(yCollide){
                    movementArray[1]=-movementArray[1];
                }
            }
        }
        return movementArray;
    }
}
