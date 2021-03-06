package main;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.DataGathering.SimulationResult;

public class AppUnitTest {
    

    @Test
    public void PerformTest()
    {
        Simulation sim = new Simulation();

        Simulation.SetIsPrintingGrid(false);

        sim.SetGridSize(new Vector2(10, 10));
        sim.SetPodbusInitAmount(6);
        sim.SetGimbusInitAmount(5);
        sim.SetPatusInitAmount(4);
        sim.SetLicbusInitAmount(3);
        sim.SetStudentInitAmount(2);
        sim.SetDebilInitAmount(1);

        sim.InitSimulation();

        assertTrue(sim.GetPodbusInitAmount() == 6);
        assertTrue(sim.GetGimbusInitAmount() == 5);
        assertTrue(sim.GetPatusInitAmount() == 4);
        assertTrue(sim.GetLicbusInitAmount() == 3);
        assertTrue(sim.GetStudentInitAmount() == 2);
        assertTrue(sim.GetDebilInitAmount() == 1);

        //assertTrue(false);

        sim.RunSimulationWithoutNewThred();
        SimulationResult result = sim.GetResult();

        switch (result.numberOfEndCondition) {
            case 1:
            assertTrue(result.finNumberStudent > 0 && result.finNumberOfPiwo > 0);
            break;
        
            case 2:
            assertTrue(result.finNumberOfPiwo == 0 && result.finNumberOfGimbus < 2 && result.finNumberOfPodbus == 0 && result.finNumberOfPatus == 0);
            break;

            case 3:
            assertTrue(result.finNumberOfEgzamin ==0 && result.finNumberStudent ==0 && result.finNumberOfPodbus == 0 && result.finNumberOfLicbus == 0 && result.finNumberOfGimbus == 0 && result.finNumberOfDebil == 0);
            break;

            case 4:
            assertTrue(result.finNumberOfPatus == 0&&result.finNumberStudent ==0 && result.finNumberOfPodbus == 0 && result.finNumberOfLicbus == 0 && result.finNumberOfGimbus == 0 && result.finNumberOfDebil == 0);
            break;

            case 5:
            assertTrue(result.numberOfRounds == 600);
            break;
        }

    }
}
