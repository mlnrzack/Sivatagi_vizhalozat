package game.interfaces;

/**
 * A körökben lezajló lépésekért felelős objektumok által megvalósított interfész.
 */
public interface ISteppable
{
	/**
	 * hatására léptetődik a víz a pályán.
	 * @return
	 */
	boolean Step();
}
