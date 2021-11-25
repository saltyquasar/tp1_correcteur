package tp1_correcteur;

public interface Serializable
{
	private void writeObject(java.io.ObjectOutputStream out)
	throws IOException
	
	private void readObject(java.io.ObjectInputStream in)
	throws IOException, classNotFoundException
	
}