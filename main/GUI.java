package main;

import main.ActiveSubclass.Debil;
import main.ActiveSubclass.Gimbus;
import main.ActiveSubclass.Licbus;
import main.ActiveSubclass.Patus;
import main.ActiveSubclass.Podbus;
import main.ActiveSubclass.Student;
import main.StaticSubclass.Egzamin;
import main.StaticSubclass.Gimbaza;
import main.StaticSubclass.Licbaza;
import main.StaticSubclass.Piwo;


public class GUI {
    
    //*Prints grid in console and put thred to sleep for given amount
    //Node[][] grid - double array with nodes used for simulation
    //int timeBetweenSteps - idicates how long should thred sleep after printing grid
    public static void PrintGrid(Node[][] gridMap, int timeBetweenSteps)
    {
        GUI.GridCreator(gridMap);
        try { Thread.sleep(timeBetweenSteps); } 
        catch (InterruptedException e) { e.printStackTrace(); }
    }
    
    //*Prints grid console
    //Node[][] grid - double array with nodes used for simulation
    private static void GridCreator(Node[][] grid)
    {
        for (int i = 0; i < grid[0].length; i++) {
            System.out.print("===");
        }
        System.out.print("\n");
        for (int i = grid.length-1; i >=0; i--) {
            for (int j = 0; j < grid[i].length; j++) {
                
                Entity temp = grid[i][j].GetOccupant();

                if(temp == null)
                {
                    System.out.print(" --");
                }
                else if(temp instanceof Debil)
                {
                    System.out.print(" de");
                }
                else if(temp instanceof Gimbus)
                {
                    System.out.print(" gi");
                }
                else if(temp instanceof Licbus)
                {
                    System.out.print(" li");
                }
                else if(temp instanceof Patus)
                {
                    System.out.print(" pa");
                }
                else if(temp instanceof Podbus)
                {
                    System.out.print(" po");
                }
                else if(temp instanceof Student)
                {
                    System.out.print(" st");
                }
                else if(temp instanceof Egzamin)
                {
                    System.out.print(" EG");
                }
                else if(temp instanceof Gimbaza)
                {
                    System.out.print(" GI");
                }
                else if(temp instanceof Licbaza)
                {
                    System.out.print(" LI");
                }
                else if(temp instanceof Piwo)
                {
                    System.out.print(" PI");
                }
                else
                {
                    System.out.print(" UC");
                }
            }
            System.out.print("\n");
        }

        for (int i = 0; i < grid[0].length; i++) {
            System.out.print("===");
        }
        System.out.print("\n\n");
    }
}
