import java.util.Random;
public class CarGame{

    public static void main (String[] args){

        Random rand = new Random();
        

        GameArena road= new GameArena(950, 950, true);
        //create instances
        //put them in an array
        Rectangle background = new Rectangle(0, 0, road.getArenaWidth(), road.getArenaHeight() , "White", 0);
        Rectangle enemy_body = new Rectangle(50, 50, 100, 67 , "Blue", 1);
        Rectangle enemy_wheel1 = new Rectangle(50, 117, 14, 14, "#000000", 1);
        Rectangle enemy_wheel2 = new Rectangle(136, 117, 14, 14, "Black", 1);
        Rectangle enemy_windshield = new Rectangle(57, 55, 86, 25, "White", 2);
        Rectangle enemy_person_body1 = new Rectangle(67, 68, 6, 12, "Pink", 3);
        Rectangle enemy_person_body2 = new Rectangle(124, 68, 6,12 , "Pink", 3);
        Rectangle enemy_person_head1 = new Rectangle(65, 58, 10, 10, "Pink", 3);
        Rectangle enemy_person_head2 = new Rectangle(122, 58, 10, 10, "Pink", 3);

        Rectangle user = new Rectangle(475, 750, 140, 140,  "Red",1);

        Rectangle enemy_shapes[] = {enemy_body, enemy_wheel1, enemy_wheel2, enemy_windshield, enemy_person_body1, enemy_person_body2, enemy_person_head1, enemy_person_head2};
        int size_enemy_shapes = enemy_shapes.length;

        for (int i = 0; i < size_enemy_shapes; i++){
            road.addRectangle(enemy_shapes[i]);
        }


        road.addRectangle(user);
        road.addRectangle(background);

        boolean running = true;
        double speed = 1;
        while (running){
            
            double next_x = rand.nextDouble(800);
            
            //next_x += enemy_body.getXPosition();
            
            
            road.pause();
            for (int i = 0; i < size_enemy_shapes; i++){
                enemy_shapes[i].move("Down", road, speed);
                if (enemy_body.getYPosition() > road.getArenaHeight()){//got to bottom
                    if (speed < 21){
                        speed += 1;
                    }//incrementing speed with a cap of 21
                    if (next_x + enemy_body.getXPosition() > 800){
                        for (int k = 0; k < size_enemy_shapes; k++){
                            enemy_shapes[k].setXPosition(enemy_shapes[k].getXPosition()-800);
                        }
                    }
                    for (int j = 0; j < size_enemy_shapes; j++){
                        enemy_shapes[j].move("Right", road, next_x);
                        enemy_shapes[j].move("Up", road, road.getArenaWidth());
                    }
                }
            }

            if ((enemy_wheel1.getYPosition() + enemy_wheel1.getHeight()) > user.getYPosition()){
                if ((enemy_body.getXPosition() + enemy_body.getWidth()) > user.getXPosition()){
                    if (enemy_body.getXPosition() < (user.getXPosition()+user.getWidth())){
                        running = false;
                    }

                }
            }

            if (road.rightPressed()){
                user.move("Right", road, 3);
            }
            if (road.leftPressed()){
                user.move("Left", road, 3);
            }
            if (user.getXPosition() < 0){
                user.setXPosition(5);
            }
            if (user.getXPosition()> (road.getArenaWidth() - user.getWidth())){
                user.setXPosition((road.getArenaWidth())- (5 + user.getWidth()));
            }


            if(road.escPressed()){
                road.exit();
            }

        }

    }   




}