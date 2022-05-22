package main;

import main.ActiveSubclass.*;
import main.StaticSubclass.*;

public class Simulation {
    
    //VALUES
    private GridMap gridMap;
    
    private Thread updateThread;
    private int timeBetweenSteps = 1000;

    private boolean isRunning = false;

    //======================= INIT VALUES ===================================================
    private Vector2 gridSize = new Vector2(2, 2);

    private Vector2 Debil_speedANDvision = new Vector2(1, 1);
    private Vector2 Gimbus_speedANDvision = new Vector2(1, 1);
    private Vector2 Licbus_speedANDvision = new Vector2(1, 1);
    private Vector2 Patus_speedANDvision = new Vector2(1, 1);
    private Vector2 Podbus_speedANDvision = new Vector2(1, 1);
    private Vector2 Student_speedANDvision = new Vector2(1, 1);

    private int DebilInitAmount = 0;
    private int GimbusInitAmount = 0;
    private int LicbusInitAmount = 0;
    private int PatusInitAmount = 0;
    private int PodbusInitAmount = 1;
    private int StudentInitAmount = 0;

    private int GimbazaInitAmount = 0;
    private int LicbazaInitAmount = 0;
    private int PodbazaInitAmount = 0;
    private int UczelniaInitAmount = 0;
    //=========================================================================================

    //SETTERS && GETTERS
    public void SetGridSize(Vector2 size) { gridSize = size; }

    public void SetDebil_speedANDvision(Vector2 speedANDvision) { Debil_speedANDvision = speedANDvision; }
    public void SetGimbus_speedANDvision(Vector2 speedANDvision) { Gimbus_speedANDvision = speedANDvision; }
    public void SetLicbus_speedANDvision(Vector2 speedANDvision) { Licbus_speedANDvision = speedANDvision; }
    public void SetPatus_speedANDvision(Vector2 speedANDvision) { Patus_speedANDvision = speedANDvision; }
    public void SetPodbus_speedANDvision(Vector2 speedANDvision) { Podbus_speedANDvision = speedANDvision; }
    public void SetStudent_speedANDvision(Vector2 speedANDvision) { Student_speedANDvision = speedANDvision; }

    public void SetDebilInitAmount(int amount) { DebilInitAmount = amount; }
    public void SetGimbusInitAmount(int amount) { GimbusInitAmount = amount; }
    public void SetLicbusInitAmount(int amount) { LicbusInitAmount = amount; }
    public void SetPatusInitAmount(int amount) { PatusInitAmount = amount; }
    public void SetPodbusInitAmount(int amount) { PodbusInitAmount = amount; }
    public void SetStudentInitAmount(int amount) { StudentInitAmount = amount; }

    public void SetGimbazaInitAmount(int amount) { GimbazaInitAmount = amount; }
    public void SetLicbazaInitAmount(int amount) { LicbazaInitAmount = amount; }
    public void SetPodbazaInitAmount(int amount) { PodbazaInitAmount = amount; }
    public void SetUczelniaInitAmount(int amount) { UczelniaInitAmount = amount; }

    public int GetAllUnitsInitAmount()
    {
        return DebilInitAmount + GimbusInitAmount + LicbazaInitAmount + PatusInitAmount + PodbusInitAmount + StudentInitAmount + GimbazaInitAmount + LicbazaInitAmount + PodbazaInitAmount + UczelniaInitAmount;
    }

    public boolean IsRunning() { return isRunning; }



    //CTOR
    public Simulation() {
        gridMap = new GridMap(gridSize);

        UpdateThread u = new UpdateThread();
        updateThread = new Thread(u);
    }



    //Methods
    public void RunSimulation()
    {
        if(GetAllUnitsInitAmount() > gridSize.x*gridSize.y)
        {
            System.err.println("Unites summarized amount can not be larger than amount of nodes in map!!");
            return;
        }

        if(isRunning)
        {
            isRunning = false;
            updateThread.stop();
        }

        //Set nodes in grid
        gridMap.InitGrid(gridSize);

        //Put units on map
        for (int i = 0; i < DebilInitAmount; i++) {
            gridMap.PlaceUnitOnMap(GridMap.GetEmptyPositionInMap(), new Debil(null,Debil_speedANDvision,null));
        }
        for (int i = 0; i < GimbusInitAmount; i++) {
            gridMap.PlaceUnitOnMap(GridMap.GetEmptyPositionInMap(), new Gimbus(null,Gimbus_speedANDvision,null));
        }
        for (int i = 0; i < LicbusInitAmount; i++) {
            gridMap.PlaceUnitOnMap(GridMap.GetEmptyPositionInMap(), new Licbus(null,Licbus_speedANDvision,null));
        }
        for (int i = 0; i < PatusInitAmount; i++) {
            gridMap.PlaceUnitOnMap(GridMap.GetEmptyPositionInMap(), new Patus(null,Patus_speedANDvision,null));
        }
        for (int i = 0; i < PodbusInitAmount; i++) {
            gridMap.PlaceUnitOnMap(GridMap.GetEmptyPositionInMap(), new Podbus(null,Podbus_speedANDvision,null));
        }
        for (int i = 0; i < StudentInitAmount; i++) {
            gridMap.PlaceUnitOnMap(GridMap.GetEmptyPositionInMap(), new Student(null,Student_speedANDvision,null));
        }

        for (int i = 0; i < GimbazaInitAmount; i++) {
            gridMap.PlaceUnitOnMap(GridMap.GetEmptyPositionInMap(), new Gimbaza(null));
        }
        for (int i = 0; i < LicbazaInitAmount; i++) {
            gridMap.PlaceUnitOnMap(GridMap.GetEmptyPositionInMap(), new Licbaza(null));
        }
        for (int i = 0; i < PodbazaInitAmount; i++) {
            gridMap.PlaceUnitOnMap(GridMap.GetEmptyPositionInMap(), new Podbaza(null));
        }
        for (int i = 0; i < UczelniaInitAmount; i++) {
            gridMap.PlaceUnitOnMap(GridMap.GetEmptyPositionInMap(), new Uczelnia(null));
        }
        //

        //SETUP NEIGHB OURS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        gridMap.SetupNeighbours();


        //print fin grid
        GUI.PrintGrid(gridMap.GetGrid());


        isRunning = true;
        updateThread.start();
    }



    //Class for handling multithreading
    class UpdateThread implements Runnable
    {
        @Override
        public void run() {
            Update();
        }
        
        private void Update()
        {
            int tempRoundCount = 1;

            while(/*isRunning*/tempRoundCount >0)
            {
                tempRoundCount--;

                for (int i = 0; i < gridMap.GetGrid().length; i++) 
                {
                    for (int j = 0; j < gridMap.GetGrid()[i].length; j++) 
                    {
                        Entity current = gridMap.GetGrid()[i][j].GetOccupant(); //get entity

                        if(current == null) continue; //if none cont

                        if(current instanceof Gimbaza) //if static 
                        {
                            Gimbaza gim = (Gimbaza)current;
                            gim.DoMove(gridMap.GetGrid());;
                        }


                        if(current instanceof Active_Entity) //if active
                        {
                            Active_Entity active = (Active_Entity)current; //casting
                            
                            if(active.DoMove(gridMap.GetGrid())) //do move
                            {
                                isRunning = false; //end if cond met
                            }
                        }


                        //GUI UPDATE
                        if(current.HasMoved())GUI.PrintGrid(gridMap.GetGrid());


                        try { Thread.sleep(timeBetweenSteps); } 
                        catch (InterruptedException e) { e.printStackTrace(); }
                    }
                }


                //Set all active entities as open for next round
                for (Node[] nodes : gridMap.GetGrid()) {
                    for (Node node : nodes) {
                        if(node.GetOccupant() != null)
                        {
                            node.GetOccupant().SetToOpen();
                        }
                    }
                }
            }
        }
    }


}
