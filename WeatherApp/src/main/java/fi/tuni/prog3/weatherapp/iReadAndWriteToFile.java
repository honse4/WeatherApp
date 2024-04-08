package fi.tuni.prog3.weatherapp;
import fi.tuni.prog3.weatherapp.preferencesgson.Preferences;

/**
 * Interface with methods to read from a file and write to a file.
 */
public interface iReadAndWriteToFile {

    /**
     * Reads JSON from the given file.
     * @param fileName name of the file to read from.
     * @return Preferences-object if the read was successful, otherwise null.
     * @throws Exception if the method e.g, cannot find the file.
     */
    public Preferences readFromFile(String fileName) throws Exception;

    /**
     * Write the student progress as JSON into the given file.
     * @param fileName name of the file to write to.
     * @return true if the write was successful, otherwise false.
     * @throws Exception if the method e.g., cannot write to a file.
     */
    public boolean writeToFile(String fileName) throws Exception;
}
