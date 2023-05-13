package game.IO;

public class Commands{

/**
 * generate_map
 * addPipe
 * addPump
 * addCistern
 * addSource
 * spawnMechanic
 * spawnSaboteur
 * playerMove
 * playerAction
 * playerSetIO
 * stat
 *
 */

    public Commands(){

    }

    public void GenerateMap(String[] param){
        if (param.length < 1 || param.length > 1){
            System.out.println("Nem adott meg megfelelő elérési útvonalat!");
        }

    }
    public void AddPipe(String[] param){
        switch (param.length){
            case 0:
                break;
            case 1 || 2:
                break;
            default:
                break;
        }
    }

    public void AddPump(String[] param){
        switch (param.length){
            case 0:
                break;
            case 1 || 2:
                break;
            default:
                break;
        }
    }
    public void AddCistern(String[] param){
        switch (param.length){
            case 0:
                break;
            case 1 || 2:
                break;
            default:
                break;
        }
    }
    public void AddSouce(String[] param){
        switch (param.length){
            case 0:
                break;
            case 1 || 2:
                break;
            default:
                break;
        }
    }

    public void SpawnMechanic(String[] param){
        switch (param.length){
            case 2:
                break;
            default:
                break;
        }
    }
    public void SpawnSaboteur(String[] param){
        switch (param.length){
            case 2:
                break;
            default:
                break;
        }
    }

    public void playerMove(String[] param){
        switch (param.length){
            case 2:
                break;
            default:
                break;
        }
    }
    public void playerAction(String[] param){
        switch (param.length){
            case 2:
                break;
            default:
                break;
        }
    }
    public void playerSetIO(String[] param){
        switch (param.length){
            case 3:
                break;
            default:
                break;
        }
    }
    public void stat(String[] param){
        switch (param.length){
            case 1:
                break;
            default:
                break;
        }
    }
}