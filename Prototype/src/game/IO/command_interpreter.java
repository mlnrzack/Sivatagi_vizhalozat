package game.IO;

import java.util.Scanner;

/**
 * Ez a java osztály felel a konzolon bekért parancsok értelmezéséért
 * A parancsok listájáért érdemes átnézni a dokumantáció 7.1.2-es fejezetét, ahol a Bemeneti nyelvet részletezzük
 * A parancsok a Commands osztályban találhatóak, azok hívódnak meg, miután az interpreter feldolhozta a beolvasott parancsot
 * A parancsok:
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
 */
public class command_interpreter{

    Scanner sc = new Scanner(System.in);
    Commands cmd = new Commands();


}


