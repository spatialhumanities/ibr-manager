package de.i3mainz.ibr.procedure;

import de.i3mainz.ibr.gui.MainWindow;
import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public abstract class Procedure {

    /**
     * Reads input file, performs conversion and writes output file.
     *
     * @param source
     * @param destination
     * @param param
     */
    abstract protected void perform(String source, String destination, int param);

    abstract public FileNameExtensionFilter getFileNameExtensionFilter();

    abstract protected String getOutputFileExtension();
    
    abstract public String getProcedureDescription();

    @Override
    public String toString() {
        return "Unnamed Procedure";
    }

    /**
     * Starts conversion for a {@link Procedure}.
     *
     * @param sources
     * @param destinations
     * @param param
     */
    private void execute(String[] sources, String[] destinations, int param) {
        MainWindow.progressPanel().init(sources.length);
        for (int i = 0; i < sources.length; i++) {
            if (sources[i].equalsIgnoreCase(destinations[i])) {
                JOptionPane.showMessageDialog(MainWindow.procedurePanel(),
                        "Can't overwrite File",
                        "Overwrite warning",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        for (int i = 0; i < sources.length; i++) {
            if (!MainWindow.progressPanel().nextPart(sources[i], destinations[i])) {
                break;
            }
            perform(sources[i], destinations[i], param);
        }
        MainWindow.progressPanel().finish();
    }

    /**
     * Sets file paths and initiates conversion for a {@link Procedure}.
     *
     * @param sourceString
     * @param destinationString
     * @param value
     */
    public void execute(String sourceString, String destinationString, String value) {
        int param;
        try {
            param = Integer.parseInt(value);
        } catch (Exception e) {
            param = 0;
        }
        ArrayList<String> sources = new ArrayList<String>();
        ArrayList<String> destinations = new ArrayList<String>();

        if (sourceString.contains("{") || sourceString.contains("}")) {
            String[] split = sourceString.split("[\\{\\}]");
            String patternString = sourceString.replaceAll("\\{[^\\}]*\\}", "(\\.\\*)").replace("\\", "\\\\");
            Pattern pattern = Pattern.compile(patternString);
            File folder = new File(split[0]);
            for (File file : folder.listFiles()) {
                Matcher matcher = pattern.matcher(file.getAbsolutePath());
                if (matcher.find()) {
                    String[] split2 = destinationString.split("[\\{\\}]");
                    for (int i = 1; 2 * i <= split.length; i++) {
                        String replacement = matcher.group(i);
                        for (int j = 1; j < split2.length; j += 2) {
                            if (split2[j].equals(split[2 * i - 1])) {
                                split2[j] = replacement;
                            }
                        }
                    }
                    String path = "";
                    for (int j = 0; j < split2.length; j++) {
                        path += split2[j];
                    }
                    sources.add(file.getAbsolutePath());
                    destinations.add(path);
                }
            }
        } else {
            String[] split = sourceString.split("\"");
            for (int i = 0; i < split.length; i++) {
                File file = new File(split[i]);
                if (file.exists()) {
                    sources.add(split[i]);
                    String filename = file.getName();
                    destinations.add(destinationString + File.separator + filename.substring(0, filename.lastIndexOf(".")) + "." + getOutputFileExtension());
                }
            }
        }
        execute(sources.toArray(new String[sources.size()]), destinations.toArray(new String[destinations.size()]), param);
    }

}
