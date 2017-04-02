package com.github.burningchrome.seqeng;

import java.io.*;

/**
 *
 */
public class FileManager implements GenerateEventListener {

    private final File _file;

    public FileManager(final File file) {
        _file = file;
    }

    /**
     * Loads the File passed to the constructor and returns the current value.
     *
     * @return
     * @throws Exception
     */
    public long load() throws Exception{

        long currentValue = -1;

        if (!_file.exists()) {

            final boolean success = _file.createNewFile();
            if (!success) {
                throw new Exception("could not create file [" + _file.getAbsolutePath() + "]");
            }

            currentValue = 0;

            writeFile(currentValue);

            return currentValue;

        }

        if (!_file.canRead()) {
            throw new Exception("can not read file [" + _file.getAbsolutePath() + "]");
        }

        if (!_file.canWrite()) {
            throw new Exception("can not write file [" + _file.getAbsolutePath() + "]");
        }

        FileInputStream is = null;
        BufferedReader reader = null;
        InputStreamReader streamReader = null;

        try {
            is = new FileInputStream(_file);

            streamReader = new InputStreamReader(is);
            reader = new BufferedReader(streamReader);

            // assuming just one line at this time
            final String line = reader.readLine();

            currentValue = Long.valueOf(line);

        } finally {
            if (is != null) {
                is.close();
            }
        }

        if (currentValue == -1) {
            throw new Exception("Could not load value from file");
        }

        return currentValue;

    }

    private void writeFile(final long currentValue) throws Exception {

        OutputStream outputStream = null;
        PrintWriter printWriter = null;
        OutputStreamWriter outputStreamWriter = null;

        try {
            outputStream = new FileOutputStream(_file);
            outputStreamWriter = new OutputStreamWriter(outputStream);
            printWriter = new PrintWriter(outputStreamWriter);
            printWriter.println(currentValue);
        } finally {

            if (printWriter != null) {

                try {
                    printWriter.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    printWriter.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }

    }

    @Override
    public void allocation(final GenerateEvent event) throws Exception {

        final long value = event.getValue();
        writeFile(value);

    }

}
